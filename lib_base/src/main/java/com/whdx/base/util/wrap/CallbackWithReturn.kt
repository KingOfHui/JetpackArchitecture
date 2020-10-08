package com.whdx.base.util.wrap

interface CallbackWithReturn<P, R> {
    fun callback(params: P): R
}

interface CallbackNoReturn<P>{
    fun callback(p: P)
}
