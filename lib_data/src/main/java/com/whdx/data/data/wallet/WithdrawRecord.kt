package com.whdx.data.data.wallet


/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 8:02
 */
data class WithdrawRecord(
    val items: List<WithdrawRecordItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class WithdrawRecordItem(
    val address: String,
    val admin_id: Int,
    val amount: Int,
    val arrived_amount: Int,
    val audit_at: String,
    val audit_status: String,
    val btw_price: Int,
    val chain: String,
    val create_time: String,
    val deal_time: String,
    val fee: Int,
    val height: Int,
    val id: Int,
    val memo: String,
    val pay_status: String,
    val remark: String,
    val state: Int,
    val symbol: String,
    val trade_no: String,
    val txid: String,
    val update_time: String,
    val usdt_cost: Int,
    val user_id: Int
)