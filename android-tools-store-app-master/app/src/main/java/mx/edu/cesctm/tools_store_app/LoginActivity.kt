package mx.edu.cesctm.tools_store_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import mx.edu.cesctm.tools_store_app.api.ApiClient
import mx.edu.cesctm.tools_store_app.api.ApiToolsBookStoreApp
import mx.edu.cesctm.tools_store_app.models.LoginCredentials
import mx.edu.cesctm.tools_store_app.models.User
import mx.edu.cesctm.tools_store_app.utils.NetworkUtils
import mx.edu.cesctm.tools_store_app.utils.UiUtils
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener {
            attemptLogin()
        }
    }


    private fun attemptLogin(): Unit{
        // Primero revisamos si la red está disposible
        if(NetworkUtils.isNetworkAvailable(this@LoginActivity)){

            // Obtenemos los datos
            val mUsername: String = login_et_username.text.toString()
            val mPassword: String = login_et_password.text.toString()

            // Verificamos que los campos no esten vacíos
            if(!mUsername.isEmpty() && !mPassword.isEmpty()){

                // Ocultamos el boton de iniciar sesión y mostramos un progress bar con spinner
                hideLoginButton(true)

                // Construimos una instancia de retrofit
                val apiBook: ApiToolsBookStoreApp = ApiClient.getClient(this@LoginActivity).create(ApiToolsBookStoreApp::class.java)

                // Creamos una instancia para enviar el username y password
                val credentials: LoginCredentials = LoginCredentials(mUsername, mPassword)

                // Ejecutamos el metodo auth(login) que recibe un objeto de tipo LoginCredentials
                apiBook.auth(credentials)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    if(result.isSuccessful){
                                        // Evaluamos que el body no sea nulo
                                        if(result.body() != null){
                                            // Obtenemos el objeto user que viene en el body
                                            val user: User? = result.body()!!.user
                                            // Iniciamos Home Activity
                                            startDashboardActivity(user)
                                        }
                                    }else{
                                        try {
                                            // Obtenemos el mensaje que envía la api cuando el status es diferente de 200
                                            var message: String = JSONObject(result.errorBody()!!.string())
                                                    .getString("message")
                                            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                                            UiUtils.showToast(this@LoginActivity, message)
                                            // Ocultamos el progress bar con spinner y mostramos el botón de iniciar sesión
                                            hideLoginButton(false)

                                        }catch (ex: Exception){
                                            ex.printStackTrace()
                                        }
                                    }
                                },
                                {error ->
                                    UiUtils.showToast(this@LoginActivity, getString(R.string.general_error))
                                    // Ocultamos el progress bar con spinner y mostramos el botón de iniciar sesión
                                    hideLoginButton(false)
                                }
                        )

            }else{
                UiUtils.showToast(this@LoginActivity, getString(R.string.empty_credentials))
            }
        }else{
            UiUtils.showToast(this@LoginActivity, getString(R.string.no_internet_connection))
        }
    }

    /**
     * Helper method para ocultar o mostrar el botón de login y el progress bar spinner
     * @param hide true para ocultar y false para mostrar
     */
    private fun hideLoginButton(hide: Boolean): Unit{
        if(hide){
            login_btn_login.visibility = View.INVISIBLE
            login_pb_logging.visibility = View.VISIBLE
        }else{
            login_btn_login.visibility = View.VISIBLE
            login_pb_logging.visibility = View.INVISIBLE
        }
    }

    /**
     * Método para iniciar el DashboardActivity
     * @param user que es emitida por la respuesta éxitosa de la API
     */
    private fun startDashboardActivity(user: User?): Unit{
        val intent: Intent = Intent(this@LoginActivity, DashboardActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
        finish()
    }
}
