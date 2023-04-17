package com.example.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "todo")
data class Todo(
    val title: String,
    val date: Long = Calendar.getInstance().timeInMillis, //현재 시간을 millsc으로 저장
    val isDone: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true) //id는 동적으로 지정되어야함
    var uid: Int = -1
}