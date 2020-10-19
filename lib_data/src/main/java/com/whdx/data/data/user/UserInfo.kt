package com.whdx.data.data.user

import android.text.TextUtils

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 18:06
 */
data class UserInfo(
    val address: String,
    val avatar: String,
    val create_time: String,
    val email: String,
    val id: Int,
    val identity_state: Int,
    val invite_code: String,
    val jy_password_state: String,
    val last_login_at: String,
    val last_login_ip: String,
    val name: String,
    val nation_id: String,
    val nickname: String,
    val password_update_at: String,
    val qr_image: String,
    var referer_id: String?=null,
    val register_ip: String,
    val signature: String,
    val state: Int,
    val update_time: String,
    val vip_degree: Int
){
    fun isOpenStatus():Boolean{
        return !TextUtils.isEmpty(referer_id)
    }
}