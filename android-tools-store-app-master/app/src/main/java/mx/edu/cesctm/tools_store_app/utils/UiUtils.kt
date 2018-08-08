package mx.edu.cesctm.tools_store_app.utils

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

object UiUtils{
    fun showToast(context: Context, message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message, length).show()
    }

    fun startActivityWithoutExtras(from: Context?, to: Context?): Unit{
        val intent: Intent = Intent(from, to!!::class.java)
        from!!.startActivity(intent)
    }

}