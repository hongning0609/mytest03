package com.changgou.search.controller;

import com.changgou.search.service.SkuInfoService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName SkuInfoController
 * @Description
 * @Author 传智播客
 * @Date 14:45 2020/2/24
 * @Version 2.1
 **/
@RestController
@RequestMapping("/search")
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * @author 栗子
     * @Description 关键字检索
     * @Date 15:45 2020/2/24
     * @param searchMap
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @GetMapping
    public Map<String, Object> search(@RequestParam(required = false) Map<String, String> searchMap){
        Map<String, Object> resultMap = skuInfoService.search(searchMap);
        return resultMap;
    }


    /**
     * @author 栗子
     * @Description 数据导入
     * @Date 14:47 2020/2/24
     * @param
     * @return entity.Result
     **/
    @GetMapping("/importData")
    public Result importData(){
        skuInfoService.importSkuInfo2Es();
        return new Result(true, StatusCode.OK, "数据导入成功");
    }
}
