package com.nyata.todomanager

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.one_result.view.*

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var dateText: TextView? = null
    var todoText: TextView? = null
    init{
        dateText = itemView.dateText
        todoText = itemView.todoText
    }
}