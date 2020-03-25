package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName SkuInfoMapper
 * @Description
 * @Author 传智播客
 * @Date 14:35 2020/2/24
 * @Version 2.1
 **/
public interface SkuInfoMapper extends ElasticsearchRepository<SkuInfo, Integer>{

}
