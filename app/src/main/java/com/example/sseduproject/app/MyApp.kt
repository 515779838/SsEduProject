package com.example.sseduproject.app

import android.app.Activity
import android.app.Application


class MyApp : Application() {

    companion object {
        private var activities: ArrayList<Activity>? = null
        fun getActivies() = activities!!
    }

    override fun onCreate() {
        super.onCreate()
        // 初始化activity数组
        activities = ArrayList()

    }

}
