package com.yashkasera.musicwiki

import android.app.Application

class MusicWikiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppObjectController.init(this)
    }
}