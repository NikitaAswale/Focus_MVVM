package com.example.focus_mvvm

class Repository {

    private val apiService = RetrofitInstance.api

    suspend fun getTodos(): List<Todos>{
        return try {
            apiService.getTodos()
        }catch (e: Exception){
            emptyList()
        }
    }
}