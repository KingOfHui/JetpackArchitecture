package com.whdx.data.data.user

data class InviteListResponse(
    val items: List<InviteListItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class InviteListItem(
    val address: String,
    val bonus_all: Int,
    val create_time: String,
    val user_id: Int,
    val vip_degree: Int
)