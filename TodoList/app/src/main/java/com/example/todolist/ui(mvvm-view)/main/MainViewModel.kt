package com.example.todolist.`ui(mvvm-view)`.main

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val todoRepository: TodoRepository
) : AndroidViewModel(application) { //데이터 사용을 쓰기 위해 AndroidViewModel을 받아옴

    private val _items = mutableStateOf(emptyList<Todo>())
    val items: State<List<Todo>> = _items

    private var recentlyDeleteTodo: Todo? = null

    //기능 만들기
    fun addTodo(text: String) {
        viewModelScope.launch {
            todoRepository.addTodo(Todo(title = text))
        }
    }

    fun toggle(uid: Int) {
        val todo = _items.value.find { todo -> todo.uid == uid }
        //let Scope 함수는 매개변수화된 타입 확장 함수, 객체의 상태를 변경할 수 있음
        todo?.let {
            viewModelScope.launch {
                todoRepository.updateTodo(it.copy(isDone = !it.isDone).apply {
                    this.uid = it.uid
                })
            }
        }
    }

    fun deleteTodo(uid: Int){
        val todo = _items.value.find { todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.deleteTodo(it)
                recentlyDeleteTodo = it
            }
        }
    }

    //복원 기능
    fun restoreTodo(){
        viewModelScope.launch {
            todoRepository.addTodo(recentlyDeleteTodo ?: return@launch) //null이라면 취소하기
            recentlyDeleteTodo = null
        }
    }
}