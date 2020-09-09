package com.javent.bncc_remote_data_source

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javent.bncc_remote_data_source.adapter.LookUpAdapter
import com.javent.bncc_remote_data_source.model.LookUpData
import kotlinx.android.synthetic.main.activity_main.rvLookUp

class MainActivity : AppCompatActivity() {

    private val mockLookUpList = mutableListOf(
        LookUpData(imgIcon = "", name = "DKI Jakarta", phone = ""),
        LookUpData(imgIcon = "", name = "Sumatera", phone = ""),
        LookUpData(imgIcon = "", name = "Papua", phone = ""),
        LookUpData(imgIcon = "", name = "Kalimantan", phone = "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lookUpAdapter = LookUpAdapter(mockLookUpList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter
    }
}
