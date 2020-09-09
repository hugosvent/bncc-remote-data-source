package com.javent.bncc_remote_data_source

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.javent.bncc_remote_data_source.adapter.LookUpAdapter
import com.javent.bncc_remote_data_source.model.LookUpData
import kotlinx.android.synthetic.main.activity_main.rvLookUp
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val okHttpClient = OkHttpClient()

    private val mockLookUpList = mutableListOf(
        LookUpData(imgIcon = "", name = "Loading...", phone = "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lookUpAdapter = LookUpAdapter(mockLookUpList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter

        val request = Request.Builder()
            .url("https://bncc-corona-versus.firebaseio.com/v1/hotlines.json")
            .build()

        okHttpClient
            .newCall(request)
            .enqueue(
                getCallback(lookUpAdapter)
            )
    }

    private fun getCallback(lookUpAdapter: LookUpAdapter): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                this@MainActivity.runOnUiThread {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    lookUpAdapter.updateData(listOf())
                }
            }

            override fun onResponse(call: Call, response: Response) {

                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)

                    val lookUpDataListFromNetwork = mutableListOf<LookUpData>()

                    for (i in 0 until jsonArray.length()) {
                        lookUpDataListFromNetwork.add(
                            LookUpData(
                                imgIcon = jsonArray.getJSONObject(i).getString("img_icon"),
                                name = jsonArray.getJSONObject(i).getString("name"),
                                phone = jsonArray.getJSONObject(i).getString("phone")
                            )
                        )
                    }

                    this@MainActivity.runOnUiThread {
                        lookUpAdapter.updateData(lookUpDataListFromNetwork)
                    }
                } catch (e: Exception) {
                    this@MainActivity.runOnUiThread {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
