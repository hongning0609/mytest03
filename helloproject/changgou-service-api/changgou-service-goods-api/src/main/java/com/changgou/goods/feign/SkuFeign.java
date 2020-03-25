package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName SkuFeign
 * @Description 获取库存数据的feign
 * @Author 传智播客
 * @Date 14:20 2020/2/24
 * @Version 2.1
 **/
@FeignClient(name = "goods")
@RequestMapping("/sku")
public interface SkuFeign {

    @GetMapping("/findSkusByStatus/{status}")
    Result<List<Sku>> findSkusByStatus(@PathVariable(name = "status") String status);

}
