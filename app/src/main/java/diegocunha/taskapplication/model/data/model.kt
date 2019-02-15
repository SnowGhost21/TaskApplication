package diegocunha.taskapplication.model.data

import com.google.gson.annotations.SerializedName

data class Task(
        val id: String,
        @SerializedName("cidade") val city: String,
        @SerializedName("bairro") val neighborhood: String,
        @SerializedName("urlFoto") val photo: String,
        @SerializedName("urlLogo") val logo: String,
        @SerializedName("telefone") val phone: String,
        @SerializedName("titulo") val title: String,
        @SerializedName("texto") val description: String,
        @SerializedName("endereco") val address: String,
        @SerializedName("latitude") val latitude: Double,
        @SerializedName("longitude") val longitude: Double,
        @SerializedName("comentarios") val comments: List<TaskComment>
)

data class ListTask(@SerializedName("lista") val tasksIds: List<String>)

data class TaskComment(
        @SerializedName("urlPhoto") val photo: String,
        @SerializedName("nome") val username: String,
        @SerializedName("titulo") val title: String,
        @SerializedName("comentario") val comment: String,
        @SerializedName("nota") val note: Double
)

