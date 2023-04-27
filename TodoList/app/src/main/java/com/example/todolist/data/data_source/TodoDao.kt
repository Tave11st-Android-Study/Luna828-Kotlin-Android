package com.example.todolist.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

//package data_source는 데이터를 직접적으로 조작하는 파일들 모음
//database를 접근하는 핵심적인 코드들 작성

@Dao
interface TodoDao {
    //투두 객체를 활용한 Data Access Object Interface입니다

    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getAllTodos(): Flow<List<Todo>>

    //비동기로 오래걸리는 작업할 때
    @Insert(onConflict = OnConflictStrategy.REPLACE) //동일한 id인 것을 추가하면 덮어씌우겠다. -> 수정에 용이
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}