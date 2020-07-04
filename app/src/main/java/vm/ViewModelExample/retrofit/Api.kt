package vm.ViewModelExample.retrofit

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import vm.ViewModelExample.model.WeatherReportResponse

interface Api {

    @GET("URL END POINT")
    fun getWeatherResponse(): Call<WeatherReportResponse>
}