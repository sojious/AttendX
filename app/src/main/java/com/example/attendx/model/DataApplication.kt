package com.example.attendx.model

import android.app.Application

class DataApplication: Application() {

    val database by lazy {AppDatabase.getInstance(this)}
    val repository by lazy {AppRepository(database.appRecordDao())}
}