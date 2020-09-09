package com.javent.bncc_remote_data_source.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.javent.bncc_remote_data_source.model.LookUpData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_look_up.view.ivImage
import kotlinx.android.synthetic.main.item_look_up.view.tvLookUpPhone
import kotlinx.android.synthetic.main.item_look_up.view.tvLookUpProvinceName

class LookUpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: LookUpData) {
        itemView.tvLookUpProvinceName.text = data.name
        itemView.tvLookUpPhone.text = data.phone

        if (data.imgIcon.isNotBlank()) {
            Picasso.get().load(data.imgIcon).into(itemView.ivImage)
        }
    }
}
