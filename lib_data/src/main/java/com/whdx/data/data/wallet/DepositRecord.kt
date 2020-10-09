package com.whdx.data.data.wallet

data class DepositRecord(
    val items: List<DepositRecordItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class DepositRecordItem(
    val address: String,
    val admin_id: Int,
    val amount: Int,
    val audit_at: String,
    val audit_status: String,
    val chain: String,
    val create_time: String,
    val height: Int,
    val id: Int,
    val state: Int,
    val symbol: String,
    val time: String,
    val trade_no: String,
    val txid: String,
    val update_time: String,
    val user_id: Int
)