package com.example.todolist.domain.repository

import com.example.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    //ViewModel이 데이터를 조작할 때, TodoRepository를 통해
    // 실제 구현체는 Data Layer에 있으며 Domain Layer는 모름.

    fun observeTodos(): Flow<List<Todo>> //관찰이 되게 만들 것

    suspend fun addTodo(todo: Todo)

    su
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)