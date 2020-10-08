package com.whdx.data.data.product

data class InvestProduct(
    val items: List<InvestProductItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class InvestProductItem(
    val amount: Int,
    val bonus: Int,
    val create_time: String,
    val finish_time: String,
    val id: Int,
    val prod_amount: Int,
    val prod_id: Int,
    val prod_name: Int,
    val prod_rate: Int,
    val prod_release_multiple: Int,
    val prod_storage: Int,
    val prod_symbol: String,
    val quantity: Int,
    val release_amount: Int,
    val state: Int,
    val update_time: String,
    val user_id: Int
)