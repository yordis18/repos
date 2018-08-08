package mx.edu.cesctm.tools_store_app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LoginResult(
        @SerializedName("success") @Expose val success: Boolean?,
        @SerializedName("user") @Expose val user: User?
)