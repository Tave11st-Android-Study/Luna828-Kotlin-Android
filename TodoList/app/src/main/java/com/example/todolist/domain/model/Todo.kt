package com.example.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

//기본적인 model이 들어와야하고 (Data Layer과 Model과의 차이점은 Room, Retrofit 등과 연관되지 않는 순수 data class이며 실제 필요한 데이터만 가지고 있음.
//repository는 interface를 정의해서 -> data.repository에서 구체화를 진행할 예정

//직접적인 데이터 접근은 data package에서 하고
// 좀더 추상적인 내용은 domain package에서 함

//순수 data class
@Entity(tableName = "todo")
data class Todo(
    val title: String,
    val date: Long = Calendar.getInstance().timeInMillis, //현재 시간을 millsc으로 저장
    val isDone: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true) //id는 동적으로 지정되어야함
    var uid: Int = 0
}