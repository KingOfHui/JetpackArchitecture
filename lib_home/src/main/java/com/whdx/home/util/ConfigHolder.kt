package com.whdx.home.util

import com.whdx.data.respository.base.LocalDataSource
import kotlinx.coroutines.*

object ConfigHolder {


    val passwrod: String by lazy {
        var s = "BitTalk is the best~~"
        val localDataSource = LocalDataSource()
        runBlocking {
            withContext(Dispatchers.IO) {
                val current = localDataSource.walletDao.getCurrent()
                if (!current.isNullOrEmpty()) {
                    s = current[0].password ?: ""
                }
            }
        }
        return@lazy s
    }

    fun isCorrectPassword(pwd: String): Boolean {
        return passwrod == pwd
    }
}