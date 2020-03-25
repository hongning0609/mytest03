package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Spec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Para业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface ParaService {

    /***
     * Para多条件分页查询
     * @param para
     * @param page
     * @param size
     * @return
     */
    PageInfo<Para> findPage(Para para, int page, int size);

    /***
     * Para分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Para> findPage(int page, int size);

    /***
     * Para多条件搜索方法
     * @param para
     * @return
     */
    List<Para> findList(Para para);

    /***
     * 删除Para
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Para数据
     * @param para
     */
    void update(Para para);

    /***
     * 新增Para
     * @param para
     */
    void add(Para para);

    /**
     * 根据ID查询Para
     * @param id
     * @return
     */
     Para findById(Integer id);

    /***
     * 查询所有Para
     * @return
     */
    List<Para> findAll();

    /**
     * @author 栗子
     * @Description 加载商品的参数数据
     * @Date 11:58 2020/2/21
     * @param categoryId
     * @return java.util.List<com.changgou.goods.pojo.Para>
     **/
    List<Para> findParasByCategoryId(Integer categoryId);
}
