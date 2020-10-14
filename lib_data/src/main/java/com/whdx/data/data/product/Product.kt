package com.whdx.data.data.product

import java.math.BigDecimal

data class Product(
    val items: List<ProductItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class ProductItem(
    val amount: BigDecimal,
    val create_time: String,
    val id: String,
    val name: String,
    val name_en: String?,
    val rate: BigDecimal,
    val release_multiple: BigDecimal,
    val state: Int,
    val storage: BigDecimal,
    val symbol: String
)