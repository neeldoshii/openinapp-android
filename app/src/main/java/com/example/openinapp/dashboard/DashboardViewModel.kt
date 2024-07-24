package com.example.openinapp.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openinapp.model.ApiResponse
import com.example.openinapp.networking.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DashboardViewModel : ViewModel() {

//    private val _apiResponse = MutableLiveData<List<ApiResponse>>()
//    val apiResponse: LiveData<List<ApiResponse>> get() = _apiResponse

    private val _linkCreated = MutableLiveData<Int>()
    val linkCreated: LiveData<Int> get() = _linkCreated

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    private val _sources = MutableLiveData<String>()
    val sources: LiveData<String> get() = _sources


    fun getApiResponse(){
        APIClient.getRetrofitInstance().getDashboard().enqueue(object : Callback<ApiResponse>{
            override fun onResponse(callback: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){
                    Timber.tag("Response").d(response.toString())
                    _linkCreated.postValue(response.body()?.linksCreatedToday ?: 0)
                    _location.postValue(response.body()?.topLocation)
                    _sources.postValue(response.body()?.topSource)
                    Timber.d(response.body()?.linksCreatedToday.toString())
                }else{
                    Timber.e("Response Failed"+response.errorBody())
                }
            }

            override fun onFailure(callback: Call<ApiResponse>, e: Throwable) {
                Timber.e(e.message)
            }

        })
    }



}
