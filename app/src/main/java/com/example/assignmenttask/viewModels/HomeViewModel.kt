package com.example.assignmenttask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmenttask.models.ResponseApi
import com.example.assignmenttask.repositories.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {

    private var _callResponse = MutableLiveData<Call<ResponseApi>>()
    val callResponse: LiveData<Call<ResponseApi>> = _callResponse

    fun getCallResponse() {

        val call = mainRepo.getCallResponse()
        _callResponse.postValue(call)


    }


}