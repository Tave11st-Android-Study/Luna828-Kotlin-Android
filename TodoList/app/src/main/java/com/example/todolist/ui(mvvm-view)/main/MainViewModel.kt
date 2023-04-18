package com.example.todolist.`ui(mvvm-view)`.main

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val todoRepository: TodoRepository)
    : AndroidViewModel(application) {

    private val _items = mutableStateOf(emptyList<Todo>())
    val items: State<List<Todo>> = _items

    //지운 객체를 담아둘 함수가 필요
    private var recentlyDeleteTodo: Todo? = null

    fun addTodo(text: String){
        viewModelScope.launch{
            todoRepository.addTodo(Todo(title = text)) //addTodo가 suspend 함수이기 때문에 coroutine 에서 써줘야함.
        }
    }

    //update
    fun toggle(uid: Int){
        val todo = _items.value.find { todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.updateTodo(it.copy(isDone = !it.isDone).apply {
                    this.uid = it.uid
                })
            }
        }
    }

    fun delete(uid: Int){
        val todo = _items.value.find { todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.deleteTodo(it)
                recentlyDeleteTodo = it
            }
        }
    }

    //복원
    fun restoreTodo() {
        viewModelScope.launch {
            todoRepository.addTodo(recentlyDeleteTodo ?: return@launch)
            // recentlyDeleteTodo가 null이면 return@launch로 취소시킴
            //return@launch는 viewModelScope가 실행되던게 취소가 됨
            recentlyDeleteTodo = null 
        }
    }
}