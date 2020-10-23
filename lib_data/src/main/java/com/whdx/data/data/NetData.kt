package com.whdx.data.data

import java.math.BigDecimal

data class NetData(
    val btw_price: String,
    val count_invest: String,
    val count_level1: Int,
    val count_level2: Int,
    val count_level3: Int,
    val count_level4: Int,
    val count_level5: Int,
    val max_node_bonus: String,
    val mine_performance: String,
    val mine_performance_node: String,
    val net_all_bonus: String,
    val net_all_storage: BigDecimal
)