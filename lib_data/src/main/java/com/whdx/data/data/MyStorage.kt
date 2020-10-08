package com.whdx.data.data

import java.math.BigDecimal

class MyStorage(
    val balance: BigDecimal,
    val btw_price: BigDecimal,
    val deposit_address: String,
    val invest_amount: BigDecimal,
    val member_bonus: BigDecimal
)