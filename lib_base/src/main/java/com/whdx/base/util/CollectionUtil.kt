package com.whdx.base.util

import java.util.*

class CollectionUtil {
    companion object{

        fun shuffle(list: List<String?>) {
            val size = list.size
            val random = Random()
            for (i in 0 until size) {
                // 获取随机位置
                val randomPos = random.nextInt(size)
                // 当前元素与随机元素交换
                Collections.swap(list, i, randomPos)
            }
        }
    }
}