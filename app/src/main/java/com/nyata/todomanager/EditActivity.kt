package com.nyata.todomanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        realm = Realm.getDefaultInstance()

        val todoId = intent.getLongExtra("id", 0L)
        if(todoId > 0){
            val todoItem = realm.where<TodoItem>()
                    .equalTo("id", todoId).findFirst()
            todoEdit.setText(todoItem?.todo.toString())
            deleteBtn.visibility = View.VISIBLE
        }else{
            deleteBtn.visibility = View.INVISIBLE
        }

        saveBtn.setOnClickListener{
            var todo: String = ""

            if(!todoEdit.text.isNullOrEmpty()){
                todo = todoEdit.text.toString()
            }

            when(todoId){
                0L -> {
                    realm.executeTransaction{
                        val maxId = realm.where<TodoItem>().max("id")
                        val nextId = (maxId?.toLong() ?: 0L) + 1L
                        val todoItem = realm.createObject<TodoItem>(nextId)
                        todoItem.dateTime = Date()
                        todoItem.todo = todo
                    }
                }
                else -> {
                    realm.executeTransaction {
                        val todoItem = realm.where<TodoItem>()
                                .equalTo("id", todoId).findFirst()
                        todoItem?.todo = todo
                    }
                }
            }
            Toast.makeText(applicationContext, "保存しました", Toast.LENGTH_SHORT).show()
            finish()
        }

        deleteBtn.setOnClickListener{
            realm.executeTransaction{
                val todoItem = realm.where<TodoItem>()
                        .equalTo("id", todoId)
                        ?.findFirst()
                        ?.deleteFromRealm()
            }
            Toast.makeText(applicationContext, "削除しました", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}