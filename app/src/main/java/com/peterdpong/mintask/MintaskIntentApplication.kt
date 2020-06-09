package com.peterdpong.mintask

import android.app.Application

class MintaskIntentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }

}