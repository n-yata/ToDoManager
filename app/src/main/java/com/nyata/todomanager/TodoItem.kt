package com.nyata.todomanager

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class TodoItem: RealmObject(){
    @PrimaryKey
    var id: Long = 0
    var dateTime: Date = Date()
    var todo: String = ""
}