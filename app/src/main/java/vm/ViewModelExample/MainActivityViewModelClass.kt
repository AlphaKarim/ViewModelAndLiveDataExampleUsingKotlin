package vm.ViewModelExample

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vm.ViewModelExample.model.WeatherReportResponse
import vm.ViewModelExample.retrofit.RetrofitClient

class MainActivityViewModelClass : ViewModel(){

     var randomeNumber : MutableLiveData<String>? = null

    fun getData(applicationContext: Context):MutableLiveData<String>?{
        if(randomeNumber == null){
            randomeNumber = MutableLiveData()
            Log.e("GeneratedNumber","Inside")
            getDataUsingRetrofit(applicationContext)
        }
        return randomeNumber
    }

    fun getDataUsingRetrofit(applicationContext: Context){
//        randomeNumber!!.value = (0..10).random().toString()
        Log.e("GeneratedNumber",randomeNumber.toString())
        val progressDialog = ProgressDialog(applicationContext)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Fetching the data, please wait")
        progressDialog.show()

        RetrofitClient.instance.getWeatherResponse().enqueue(object: Callback<WeatherReportResponse>{
            override fun onFailure(call: Call<WeatherReportResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext,"Sory ty again later",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<WeatherReportResponse>,
                response: Response<WeatherReportResponse>
            ) {
                progressDialog.dismiss()
                randomeNumber!!.value = "Temp : " + response.body()!!.main.temp.toString()
                Log.e("GeneratedNumber",response.body()!!.main.temp.toString())

            }
        })
    }
}