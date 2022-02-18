package com.example.alamsyah.crudmysql.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alamsyah.crudmysql.R
import com.example.alamsyah.crudmysql.model.DataItem2
import kotlinx.android.synthetic.main.item_data2.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DataAdapter2(val data: List<DataItem2>?, private val click: onClickItem) : RecyclerView.Adapter<DataAdapter2.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data2,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }

    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: DataItem2?) {
            itemView.tvSoal.text = get?.staffName

        }
    }

    interface onClickItem{
        fun clicked (item: DataItem2?)


    }
}