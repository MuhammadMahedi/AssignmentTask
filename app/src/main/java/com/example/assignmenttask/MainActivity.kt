package com.example.assignmenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.assignmenttask.adapters.MembersAdapter
import com.example.assignmenttask.databinding.ActivityMainBinding
import com.example.assignmenttask.models.All
import com.example.assignmenttask.models.ResponseApi
import com.example.assignmenttask.models.Top3
import com.example.assignmenttask.services.ApiService
import com.example.assignmenttask.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        const val BASE_URL="https://raw.githubusercontent.com/mhasancse17/JsonFile/main/"
        const val ENDPOINT = "leaderboard.json"
    }
    lateinit var binding: ActivityMainBinding
    val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        getLeaderBoard()
    }

    private fun getLeaderBoard(){

            viewModel.getCallResponse()


        viewModel.callResponse.observe(this@MainActivity){
            it.enqueue(object : Callback<ResponseApi>{
                override fun onResponse(
                    call: Call<ResponseApi>,
                    response: Response<ResponseApi>
                ) {
                    if(response.isSuccessful){
                        val result : ResponseApi? = response.body()
                        Log.d("ShowResponse", result.toString())

                        val allMembers= result?.data?.hostDaily?.all
                        Log.d("ShowResponse", allMembers.toString())

                        val topThree = result?.data?.hostDaily?.top3

                        setAdapter(allMembers)

                        setTopThree(topThree)
                    }
                }

                override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


    }

    fun setAdapter(list: List<All>?){
        if(list!=null){
            val adapter = MembersAdapter(this,list)
            binding.rvMemberList.adapter =adapter
        }

    }

    fun setTopThree(list: List<Top3>?){
        if(list?.size==3){
            Glide
                .with(this)
                .load(list[0].profilePic)
                .centerCrop()
                .placeholder(R.color.black)
                .dontAnimate()
                .into(binding.ivTopOne)
            binding.tvTopper.text = list[0].firstName
            binding.tvToopersCoin.text =list[0].giftcoin.toString()

            Glide
                .with(this)
                .load(list[1].profilePic)
                .centerCrop()
                .placeholder(R.color.black)
                .dontAnimate()
                .into(binding.ivTopTwo)
            binding.tvSecond.text = list[1].firstName
            binding.tvSecondsCoin.text =list[1].giftcoin.toString()


            Glide
                .with(this)
                .load(list[2].profilePic)
                .centerCrop()
                .placeholder(R.color.black)
                .dontAnimate()
                .into(binding.ivTopThree)
            binding.tvThird.text = list[2].firstName
            binding.tvThirdsCoin.text =list[2].giftcoin.toString()
        }


    }

}