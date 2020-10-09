package com.whdx.data.data.wallet

data class InvestBonus(
    val items: List<InvestBonusItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class InvestBonusItem(
    val bonus: String,
    val bonus_date: String,
    val create_time: String,
    val id: Int,
    val invest_amount: Int,
    val invest_id: Int,
    val symbol: String,
    val user_id: Int
)