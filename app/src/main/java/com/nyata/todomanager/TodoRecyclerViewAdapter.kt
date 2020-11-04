package com.nyata.todomanager

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import android.text.format.DateFormat

class TodoRecyclerViewAdapter(realmResults: RealmResults<TodoItem>): RecyclerView.Adapter<ViewHolder>() {
    private val results: RealmResults<TodoItem> = realmResults

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_result, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = results[position]
        holder.dateText?.text = DateFormat.format("yyyy/MM/dd kk:mm", todoItem?.dateTime)
        holder.todoText?.text = todoItem?.todo.toString()

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, EditActivity::class.java)
            intent.putExtra("id", todoItem?.id)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
}