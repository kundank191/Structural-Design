package com.example.android.sd.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sd.R
import com.example.android.sd.ui.steel.SteelDesignActivity
import kotlinx.android.synthetic.main.design_type_row_item.view.*


/**
 * Created by Kundan on 16-09-2018.
 */
class DesignTypeAdapter(private val items: List<MainActivity.DesignType>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.design_type_row_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ivImageType.setImageResource(items.get(position).imageID)
        holder.tvDesignType.text = items.get(position).designType
        holder.itemView.setOnClickListener {
            if (it.tv_design_type.text == context.getString(R.string.steel_design_text)) {
                context.startActivity(Intent(context, SteelDesignActivity::class.java))
            } else if (it.tv_design_type.text == context.getString(R.string.rcc_design_text)) {
                context.startActivity(Intent(context, SteelDesignActivity::class.java))
            }
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val ivImageType = view.imageView!!
    val tvDesignType = view.tv_design_type!!
}