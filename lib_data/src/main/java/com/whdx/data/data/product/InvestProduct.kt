package com.whdx.data.data.product

import android.graphics.Color
import android.os.Parcelable
import android.text.Html
import com.whdx.base.util.SpannableStringUtil
import com.whdx.data.R
import java.io.Serializable
import java.math.BigDecimal

data class InvestProduct(
    val items: List<InvestProductItem>,
    val limit: Int,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class InvestProductItem(
    val amount: BigDecimal,
    val bonus: BigDecimal,
    val create_time: String,
    val finish_time: String,
    val id: Int,
    val prod_amount: BigDecimal,
    val prod_id: Int,
    val prod_name: String,
    val prod_rate: String,
    val prod_release_multiple: String,
    val prod_storage: BigDecimal,
    val prod_symbol: String,
    val quantity: Int,
    val release_amount: BigDecimal,
    val state: String,
    val update_time: String,
    val user_id: String
) : Serializable {
    fun format(s1: String, s2: String): String {
//        return SpannableStringUtil.getBuilder("").setForegroundColor(Color.parseColor("#94A0C1"))
//        return SpannableStringUtil.getBuilder("").setForegroundColor(Color.WHITE)
//            .append(s1).setForegroundColor(Color.parseColor("#ffffff")).append(s2).create().toString()
        return Html.fromHtml("<font color='#94A0C1'>$s1</font><font color='#ffffff'>$s2</font>").toString()
    }
}