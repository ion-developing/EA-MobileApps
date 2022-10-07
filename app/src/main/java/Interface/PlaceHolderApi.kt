package Interface

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceHolderApi {

    @GET("search")
    fun getResponse(@Query("query")item:String,
                    @Query("apiKey")apiKey:String="08899667f4e8465fb9f24d6cb055165d",
                    @Query("number")number:Int=1):Call<JsonObject>

    @GET("search?number=1&apiKey=08899667f4e8465fb9f24d6cb055165d&query=leche")
    fun getResponse2():Call<JsonObject>
}