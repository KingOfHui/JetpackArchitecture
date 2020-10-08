package com.whdx.data.data.wallet

import java.math.BigDecimal

data class USDTBalance(
    val balance: BigDecimal,
    val btw_price: BigDecimal,
    val deposit_address: String,
    val invest_amount: BigDecimal,
    val member_bonus: BigDecimal,
    val yesterday_member_bonus: BigDecimal
)