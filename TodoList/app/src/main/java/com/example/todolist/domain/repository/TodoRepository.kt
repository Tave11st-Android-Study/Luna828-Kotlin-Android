package com.example.todolist.domain.repository

import com.example.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    //ViewModel이 데이터를 조작할 때, TodoRepository를 통해

    fun observeTodos(): Flow<List<Todo>> //관찰이 되게 만들 것

    suspend fun addTodo(todo: Todo)

    suspend fun updateTodo(todo:Todo)

    suspend fun deleteTodo(todo: Todo)
}