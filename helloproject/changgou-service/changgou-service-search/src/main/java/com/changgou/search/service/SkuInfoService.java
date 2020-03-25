package com.changgou.search.service;

import java.util.Map;

/**
 * @ClassName SkuInfoService
 * @Description
 * @Author 传智播客
 * @Date 14:36 2020/2/24
 * @Version 2.1
 **/
public interface SkuInfoService {

    /**
     * @author 栗子
     * @Description 前台系统根据关键字检索
     * @Date 15:24 2020/2/24
     * @param searchMap
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> search(Map<String, String> searchMap);

    /**
     * @author 栗子
     * @Description 将数据库数据导入到索引库中
     * @Date 14:36 2020/2/24
     * @param
     * @return void
     **/
    void importSkuInfo2Es();
}
