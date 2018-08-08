package mx.edu.cesctm.tools_store_app.api

import io.reactivex.Single
import mx.edu.cesctm.tools_store_app.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiToolsBookStoreApp{
    /* Log In */
    @POST("login")
    fun auth(@Body credentials: LoginCredentials): Single<Response<LoginResult>>

}