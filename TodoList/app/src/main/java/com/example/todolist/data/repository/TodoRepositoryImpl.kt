package com.example.todolist.data.repository

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.data_source.TodoDatabase
import com.example.todolist.domain.model.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(application: Application) : TodoRepository {
    //TodoRepository를 구현하고 있는 구현체를 만들것
    private val db = Room.databaseBuilder(
        application,
        TodoDatabase::class.java,
        "todo-db",
    ).build()

    //db를 경유해서 기능들을 구현해줌
    override fun observeTodos(): Flow<List<Todo>> {
        return db.todoDao().getAllTodos()
    }

    override suspend fun addTodo(todo: Todo) {
        return db.todoDao().insert(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        return db.todoDao().update(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        return db.todoDao().delete(todo)
    }

}