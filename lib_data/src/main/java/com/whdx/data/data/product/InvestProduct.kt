package com.whdx.data.data.product

import android.os.Parcelable
import java.io.Serializable

data class InvestProduct(
    val items: List<InvestProductItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class InvestProductItem(
    val amount: String,
    val bonus: String,
    val create_time: String,
    val finish_time: String,
    val id: Int,
    val prod_amount: String,
    val prod_id: Int,
    val prod_name: String,
    val prod_rate: String,
    val prod_release_multiple: String,
    val prod_storage: String,
    val prod_symbol: String,
    val quantity: Int,
    val release_amount: String,
    val state: String,
    val update_time: String,
    val user_id: String
):Serializable