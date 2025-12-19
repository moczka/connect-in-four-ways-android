package com.example.connectfourways

import android.app.Application

class GameApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        GameRepository.initialize(this)
    }
}