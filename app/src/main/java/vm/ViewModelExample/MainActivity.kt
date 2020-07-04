package vm.ViewModelExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var number : LiveData<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Activity","inside")
//        val mainActivityViewModel = MainActivityViewModel()
        Log.e("Activity","AfterViewModel")
        val userViewModel = ViewModelProviders.of(this).get(MainActivityViewModelClass::class.java)
        number = userViewModel.getData(this)
        Log.e("GeneratedNumber",number.toString())
        number?.observe(this, Observer {
            randomNumberTxt!!.text = it
        })
        numberChangeBtn?.setOnClickListener(View.OnClickListener {
            userViewModel.getDataUsingRetrofit(this)
        })
    }


}
