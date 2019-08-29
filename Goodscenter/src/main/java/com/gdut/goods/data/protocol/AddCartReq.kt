package com.gdut.goods.data.protocol

/**
 * @author  Li Xuyang
 * date  2019/8/27 13:47
 */
data class AddCartReq(val goodsId: Int, //商品ID
                 val goodsDesc: String, //商品描述
                 val goodsIcon: String, //商品图标
                 val goodsPrice: Long, //商品价格
                 val goodsCount: Int, //商品数量
                 val goodsSku: String //商品SKU
)
