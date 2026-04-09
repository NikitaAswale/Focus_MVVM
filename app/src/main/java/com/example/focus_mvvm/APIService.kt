package com.example.focus_mvvm

import retrofit2.http.GET

interface APIService {

@GET("todos")

suspend fun getTodos(): List<Todos>
}