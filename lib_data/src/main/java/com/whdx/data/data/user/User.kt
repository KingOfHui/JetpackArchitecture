package com.whdx.data.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Int = 0,
    @ColumnInfo(name = "user_icon")
    var icon: String? = null,
    @ColumnInfo(name = "user_public_name")
    var publicName: String? = null,
    @ColumnInfo(name = "user_name")
    var username: String? = null
) : Serializable