package com.example.parcial

import Beans.Post
import Interface.PlaceHolderApi
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial.AppDataBase
import com.example.parcial.MainActivity2
import com.example.parcial.R
import com.example.parcial.tablePost
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class responseFormat{
    var type: String = ""
    var products: List<Post> = listOf()
}

class MainActivity : AppCompatActivity() {

    lateinit var service:PlaceHolderApi
    var postsList:List<Post> = listOf()
    lateinit var pos:Post
    lateinit var appDB: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDB= AppDataBase.getDatabase(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/food/products/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service=retrofit.create<PlaceHolderApi>(PlaceHolderApi::class.java)

        val btnFind:Button=findViewById(R.id.btnFind)

        val txtId:EditText=findViewById(R.id.txtId)

        val txtRes:TextView=findViewById(R.id.txtRes)

        btnFind.setOnClickListener(){
            getPostId(txtId.text.toString())
//            test()
        }

        val btnSave:Button=findViewById(R.id.btnSave)


        btnSave.setOnClickListener(){

            val regPost= tablePost(pos.id,pos.name,pos.img)

            GlobalScope.launch( Dispatchers.IO) {
                appDB.postDao().insert(regPost)
            }

            val txtRes:TextView=findViewById(R.id.txtRes)
            txtRes.text="Saved successfully"

        }

        val btnList:Button=findViewById(R.id.btnList)


        btnList.setOnClickListener(){

            val intent=Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        }

    fun getPostId(item:String){
        service.getResponse(item = item).enqueue(
            object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val txtRes:TextView=findViewById(R.id.txtRes)
                    if (response.isSuccessful) {

                        val res = response?.body()!! as JsonObject
                        val list = res.getAsJsonArray("products")
                        val currentpost = list.get(0) as JsonObject
                        val id = currentpost.get("id").toString().toInt()
                        val name = currentpost.get("title").toString()
                        val img = currentpost.get("image").toString()

                        pos = Post(
                            id,
                            name,
                            img
                        )
                        txtRes.text = "ID: ${pos.id} \nName: ${pos.name} \nImage: ${pos.img}"
                    }
                    else {
                        txtRes.text = "Error: ${response.errorBody()}"
                    }


                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    t?.printStackTrace()
                }

            })



    }

    fun test(){
        service.getResponse2().enqueue(
            object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val txtRes:TextView=findViewById(R.id.txtRes)
                    val res = response?.body()!! as JsonObject
                    val list = res.getAsJsonArray("products")
                    val currentpost = list.get(0) as JsonObject
                    val id = currentpost.get("id").toString().toInt()
                    val name = currentpost.get("title").toString()
                    val img = currentpost.get("image").toString()

                    txtRes.text = currentpost.toString()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    t?.printStackTrace()
                }
            }
        )
    }

    }

