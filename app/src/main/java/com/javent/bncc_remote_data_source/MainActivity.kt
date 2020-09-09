package com.javent.bncc_remote_data_source

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.javent.bncc_remote_data_source.adapter.LookUpAdapter
import com.javent.bncc_remote_data_source.model.LookUpData
import com.javent.bncc_remote_data_source.network.HotlineService
import kotlinx.android.synthetic.main.activity_main.rvLookUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bncc-corona-versus.firebaseio.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val mockLookUpList = mutableListOf(
        LookUpData(imgIcon = "", name = "Loading...", phone = "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lookUpAdapter = LookUpAdapter(mockLookUpList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val lookUpData = retrofit.create(HotlineService::class.java).getHotlines()
                withContext(Dispatchers.Main) {
                    lookUpAdapter.updateData(lookUpData)
                }
            } catch (e : Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    lookUpAdapter.updateData(listOf())
                }
            }
        }
    }
}
