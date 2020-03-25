package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuInfoMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuInfoService;
import entity.Result;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuInfoServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 14:37 2020/2/24
 * @Version 2.1
 **/
@Service
public class SkuInfoServiceImpl implements SkuInfoService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired(required = false)
    private SkuFeign skuFeign;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;    // 操作es的客户端对象

    /**
     * @author 栗子
     * @Description 前台系统根据关键字检索
     * @Date 15:24 2020/2/24
     * @param searchMap 检索条件
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        // 1、封装检索条件
        NativeSearchQueryBuilder builder = buildBasicQuery(searchMap);
        // 2、根据条件查询  查询商品、分类、品牌、规格、列表数据
//        elasticsearchTemplate.queryForPage()
        // 查询商品列表数据
        Map<String, Object> resultMap = searchForPage(builder);
        // 统计商品分类列表数据
        List<String> categoryList = searchCategoryList(builder);
        resultMap.put("categoryList", categoryList);
        // 3、返回结果
        return resultMap;
    }

    // 统计商品分类列表数据---分组查询
    private List<String> searchCategoryList(NativeSearchQueryBuilder builder) {
        // es类似分组查询：聚合查询
        // 添加分组条件  skuCategory：取别名  categoryName：根据该字段进行分组
        builder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName"));
        AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);
        Aggregations aggregations = page.getAggregations();
        // 处理分组结果集
        StringTerms stringTerms = aggregations.get("skuCategory");
        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
        List<String> list = new ArrayList<>();
        for (StringTerms.Bucket bucket : buckets) {
            String categoryName = bucket.getKeyAsString();
            list.add(categoryName);
        }
        return list;
    }

    // 根据条件查询商品列表数据
    private Map<String, Object> searchForPage(NativeSearchQueryBuilder builder) {
        // 根据条件查询
        NativeSearchQuery nativeSearchQuery = builder.build();
        AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(nativeSearchQuery, SkuInfo.class);
        // 处理结果集
        List<SkuInfo> rows = page.getContent();         // 商品列表数据
        long totalElements = page.getTotalElements();   // 总条数
        int totalPages = page.getTotalPages();          // 总页数
        Map<String, Object> map = new HashMap<>();
        map.put("rows", rows);
        map.put("totalElements", totalElements);
        map.put("totalPages", totalPages);
        return map;
    }

    // 封装检索条件
    private NativeSearchQueryBuilder buildBasicQuery(Map<String, String> searchMap) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 开始封装检索条件
        if (searchMap != null){
            // 1、根据关键字检索
            String keywords = searchMap.get("keywords");
            if (!StringUtils.isEmpty(keywords)){
                builder.withQuery(QueryBuilders.matchQuery("name", keywords));
            }
            // TODO 2、根据商品分类查询、根据商品品牌查询...
        }
        return builder;
    }

    /**
     * @author 栗子
     * @Description 将数据库数据导入到索引库中
     * @Date 14:36 2020/2/24
     * @param
     * @return void
     **/
    @Override
    public void importSkuInfo2Es() {
        // 通过feign调用商品微服务提供的接口数据（sku）
        Result<List<Sku>> result = skuFeign.findSkusByStatus("1");
        List<Sku> skus = result.getData();
        // 将sku数据转成SkuInfo
        String text = JSON.toJSONString(skus);
        List<SkuInfo> skuInfos = JSON.parseArray(text, SkuInfo.class);
        // sku spec
        // 栗子：{"手机屏幕尺寸":"5寸","网络":"联通3G+移动4G","颜色":"红","测试":"s11","机身内存":"16G",
        // "存储":"64G","像素":"800万像素"}
        // 处理规格数据
        for (SkuInfo skuInfo : skuInfos) {
            String spec = skuInfo.getSpec();
            Map<String,Object> specMap = JSON.parseObject(spec, Map.class);
            skuInfo.setSpecMap(specMap);
        }
        // 将SkuInfo保存到es中
        skuInfoMapper.saveAll(skuInfos);
    }
}
