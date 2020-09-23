package com.whdx.base.net.exception


class ResultException(var errCode: String?, var msg: String?) : Exception(msg)
