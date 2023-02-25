package com.example.todolistziro.architecture.di

import com.example.todolistziro.architecture.repositories.LoginRepositoryImpl
import com.example.todolistziro.data.NoteRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface ApiComponent {

    fun inject(loginApi: LoginRepositoryImpl)
    fun inject(noteRepository: NoteRepository)

}