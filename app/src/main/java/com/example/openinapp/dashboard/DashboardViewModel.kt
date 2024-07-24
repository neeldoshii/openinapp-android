package com.example.openinapp.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openinapp.model.ApiResponse
import com.example.openinapp.model.Links
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

    private val _toplinks = MutableLiveData<List<Links>>()
    val toplinks: LiveData<List<Links>> get() = _toplinks

    private val _recentLinks = MutableLiveData<List<Links>>()
    val recentLinks: LiveData<List<Links>> get() = _recentLinks

    /**
     * Retrieves the links statistics including the number of links created today, top source, and top location.
     */
    fun getApiResponse(){
        APIClient.getRetrofitInstance().getDashboard().enqueue(object : Callback<ApiResponse>{
            override fun onResponse(callback: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){
                    Timber.tag("Response").d(response.toString())
                    _linkCreated.postValue(response.body()?.linksCreatedToday ?: 0)
                    _location.postValue(response.body()?.topLocation)
                    _sources.postValue(response.body()?.topSource)
                    val topLinks = response.body()?.data?.topLinks
                    val linksList = mutableListOf<Links>()

                    topLinks?.forEach { it->
                        val weblinks = it.webLink
                        val image = it.originalImage
                        val createdAt = it.createdAt
                        val totalClicks = it.totalClicks
                        val linkName = it.title
                        linksList.add(Links(icon = image, linkName = linkName, creationDate = createdAt, linkUrl = weblinks,totalClicks = totalClicks))
                    }
                    _toplinks.postValue(linksList)

                    Timber.d(response.body()?.linksCreatedToday.toString())
                    Timber.d(response.body()?.data?.recentLinks.toString())
                }else{
                    Timber.e("Response Failed"+response.errorBody())
                }
            }

            override fun onFailure(callback: Call<ApiResponse>, e: Throwable) {
                Timber.e(e.message)
            }

        })
    }

    /**
     * Retrieves either the top links or recent links based on the given parameter.
     *
     * @param topLinks A boolean flag indicating which set of links to retrieve.
     *                 - If `true`, retrieves the top links.
     *                 - If `false`, retrieves the recent links.
     */
    fun getApiResponse(topLinks: Boolean){
        APIClient.getRetrofitInstance().getDashboard().enqueue(object : Callback<ApiResponse>{
            override fun onResponse(callback: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){
                    Timber.tag("Response").d(response.toString())
                    val linksList = mutableListOf<Links>()
                    if (topLinks){
                        val topLinks = response.body()?.data?.topLinks
                        topLinks?.forEach { it->
                            val weblinks = it.webLink
                            val image = it.originalImage
                            val createdAt = it.createdAt
                            val totalClicks = it.totalClicks
                            val linkName = it.title
                            linksList.add(Links(icon = image, linkName = linkName, creationDate = createdAt, linkUrl = weblinks,totalClicks = totalClicks))
                        }
                        _toplinks.postValue(linksList)
                    }
                    if (!topLinks){
                        val recentLink = response.body()?.data?.recentLinks
                        recentLink?.forEach { it->
                            val weblinks = it.webLink
                            val image = it.originalImage
                            val createdAt = it.createdAt
                            val totalClicks = it.totalClicks
                            val linkName = it.title
                            linksList.add(Links(icon = image, linkName = linkName, creationDate = createdAt, linkUrl = weblinks,totalClicks = totalClicks))
                        }
                        _recentLinks.postValue(linksList)
                    }
                    Timber.d(response.body().toString())
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
