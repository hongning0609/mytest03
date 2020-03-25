package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {

    /**
     * @author 栗子
     * @Description 商品上/下架
     * @Date 15:55 2020/2/21
     * @param id
     * @param isMarketable
     * @return void
     **/
    void isShow(Long id, String isMarketable);

    /**
     * @author 栗子
     * @Description 商品审核
     * @Date 15:49 2020/2/21
     * @param id
     * @param status 审核状态  0：未审核  1：审核通过  2：审核未通过
     * @return void
     **/
    void audit(Long id, String status);

    /**
     * @author 栗子
     * @Description 商品保存
     * @Date 14:53 2020/2/21
     * @param goods
     * @return void
     **/
    void saveOrUpdate(Goods goods);

    /**
     * @author 栗子
     * @Description 回显商品
     * @Date 15:32 2020/2/21
     * @param spuId
     * @return com.changgou.goods.pojo.Goods
     **/
    Goods findGoodsById(long spuId);

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();


}
