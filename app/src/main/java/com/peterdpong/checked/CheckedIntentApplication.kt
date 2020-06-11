package com.peterdpong.checked

import android.app.Application

class CheckedIntentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }

}