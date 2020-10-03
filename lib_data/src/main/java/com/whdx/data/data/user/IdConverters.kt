package com.whdx.data.data.user

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IdConverters {
    @TypeConverter
    fun stringToIdList(value:String):List<String>{
        val list_type= object :TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, list_type)
    }

    @TypeConverter
    fun idListToString(list:List<String>):String{
        return Gson().toJson(list)
    }
}