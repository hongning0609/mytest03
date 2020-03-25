package com.changgou.goods.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Goods
 * @Description
 * @Author 传智播客
 * @Date 14:47 2020/2/21
 * @Version 2.1
 **/
// Serializable: 网络传输  使用内存
public class Goods implements Serializable {

    private Spu spu;                // 商品基本信息
    private List<Sku> skuList;      // 商品的库存信息

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
