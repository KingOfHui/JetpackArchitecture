package com.whdx.data.data.wallet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WalletModel(
    @PrimaryKey
    var id: Int? = 0,
    var address: String? = null,
    var name: String? = null,
    var password: String? = null,
    var keystorePath: String? = null,
    var mnemonic: String? = null,
    var isCurrent: Boolean? = false,
    var privateKey: String? = null,
    var publicKey: String? = null,
    var importType: Int? = 0,    //0新创建，1助记词导入，2keystore导入，3密钥导入
    var keyStoreVal: String? = null,     //存储
    var currentSelect: Int? = 0     //1表示当前选中，0表示未选中
) {
}