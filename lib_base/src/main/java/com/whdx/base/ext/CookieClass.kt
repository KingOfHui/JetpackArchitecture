package com.whdx.base.ext

import com.whdx.base.app.BaseApplication
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.whdx.base.app.BaseApplication.Companion.CONTEXT

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:22
 */
object CookieClass {
    /**Cookie*/
    val cookiePersistor = SharedPrefsCookiePersistor(CONTEXT)
    val cookieJar = PersistentCookieJar(SetCookieCache(), cookiePersistor)
    /**清除Cookie*/
    fun clearCookie() =cookieJar.clear()

    /**是否有Cookie*/
    fun hasCookie() =cookiePersistor.loadAll().isNotEmpty()
}