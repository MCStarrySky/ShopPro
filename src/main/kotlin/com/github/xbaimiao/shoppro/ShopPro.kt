package com.github.xbaimiao.shoppro

import com.github.xbaimiao.shoppro.core.database.Database
import com.github.xbaimiao.shoppro.core.database.MysqlDatabase
import com.github.xbaimiao.shoppro.core.database.SQLiteDatabase
import com.github.xbaimiao.shoppro.core.item.*
import com.github.xbaimiao.shoppro.core.item.impl.HeadShopItem
import com.github.xbaimiao.shoppro.core.item.impl.ItemImpl
import com.github.xbaimiao.shoppro.core.item.impl.ItemsAdderShopItem
import com.github.xbaimiao.shoppro.core.item.impl.VanillaShopItem
import com.github.xbaimiao.shoppro.core.shop.ShopManager
import com.github.xbaimiao.shoppro.core.vault.DiyCurrency
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object ShopPro : Plugin() {

    @Config(value = "config.yml")
    lateinit var config: Configuration
        private set

    lateinit var database: Database

    val itemLoader = ArrayList<ItemLoader>()

    override fun onEnable() {
        ShopManager.load()
        DiyCurrency.load()
        database = if (config.getBoolean("mysql.enable")) MysqlDatabase(config) else SQLiteDatabase()
        itemLoader.add(VanillaShopItem)
        itemLoader.add(ItemImpl)
        itemLoader.add(ItemsAdderShopItem)
        itemLoader.add(HeadShopItem)
    }

    fun reload() {
        config.reload()
        ShopManager.shops.clear()
        ShopManager.load()
        DiyCurrency.load()
        database = if (config.getBoolean("mysql.enable")) MysqlDatabase(config) else SQLiteDatabase()
    }

}