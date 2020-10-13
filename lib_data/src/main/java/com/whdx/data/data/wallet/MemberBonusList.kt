package com.whdx.data.data.wallet

import java.math.BigDecimal

data class MemberBonusList(
    val items: List<MemberBonusItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class MemberBonusItem(
    val bonus_all: BigDecimal,
    val bonus_all_pre: BigDecimal,
    val bonus_date: String,
    val bonus_invest: BigDecimal,
    val bonus_refer: BigDecimal,
    val bonus_v1: BigDecimal,
    val bonus_v2: BigDecimal,
    val bonus_v3: BigDecimal,
    val bonus_v4: BigDecimal,
    val bonus_v5: BigDecimal,
    val create_time: String,
    val id: Int,
    val invest_amount: BigDecimal,
    val performance: BigDecimal,
    val performance_node: BigDecimal,
    val symbol: String,
    val update_time: String,
    val user_id: Int,
    val vip_degree: Int
)