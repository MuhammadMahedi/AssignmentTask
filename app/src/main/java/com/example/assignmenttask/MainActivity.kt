package com.example.assignmenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.assignmenttask.adapters.MembersAdapter
import com.example.assignmenttask.databinding.ActivityMainBinding
import com.example.assignmenttask.models.All
import com.example.assignmenttask.models.ResponseApi
import com.example.assignmenttask.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object{
        const val BASE_URL="https://raw.githubusercontent.com/mhasancse17/JsonFile/main/"
        const val ENDPOINT = "leaderboard.json"
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.whiteShape.setOnClickListener{
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        getLeaderBoard()
    }

    private fun getLeaderBoard(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val leaderboardApi = retrofit.create(ApiService::class.java)

        val call = leaderboardApi.getLeaderboardData()
        call.enqueue(object : Callback<ResponseApi>{
            override fun onResponse(
                call: Call<ResponseApi>,
                response: Response<ResponseApi>
            ) {
                if(response.isSuccessful){
                    val result : ResponseApi? = response.body()
                    Log.d("ShowResponse", result.toString())

                    val allMembers= result?.data?.hostDaily?.all
                    Log.d("ShowResponse", allMembers.toString())

                    setAdapter(allMembers)


                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun setAdapter(list: List<All>?){
        if(list!=null){
            val adapter = MembersAdapter(this,list)
            binding.rvMemberList.adapter =adapter
        }


    }

}