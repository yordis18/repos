package mx.edu.cesctm.tools_store_app.models

import com.google.gson.annotations.SerializedName


data class LoginCredentials(
        @SerializedName("email") val username: String?,
        @SerializedName("password") val password: String?

)