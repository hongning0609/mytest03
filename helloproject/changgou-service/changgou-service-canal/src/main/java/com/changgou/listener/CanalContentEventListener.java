package com.changgou.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.changgou.content.feign.ContentFeign;
import com.changgou.content.pojo.Content;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @ClassName CanalContentEventListener
 * @Description 监听广告数据是否发生改变
 * @Author 传智播客
 * @Date 16:08 2020/2/23
 * @Version 2.1
 **/
@CanalEventListener
public class CanalContentEventListener {

    @Autowired(required = false)
    private ContentFeign contentFeign;

    // RedisTemplate：对数据进行序列化
    // StringRedisTemplate：不会
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @author 栗子
     * @Description
     * @Date 16:15 2020/2/23
     * @param
     * @return void
     **/
    // 属性：destination与canal中配置一致，不改  schema：监听的库  table：监听的表 eventType：监听的事件类型
    @ListenPoint(destination = "example", schema = {"changgou_content"}, table = {"tb_content"},
            eventType = {CanalEntry.EventType.INSERT, CanalEntry.EventType.UPDATE})
    public void onEventContent(CanalEntry.RowData rowData){
        // 通过行数据获取到分类id
        String categoryId = getColumnValueByCategoryId(rowData, "category_id");
        // 通过分类id查询到广告列表数据
        Result<List<Content>> result = contentFeign.findListByCategoryId(Long.parseLong(categoryId));
        List<Content> list = result.getData();
        // 将数据保存到redis中 key-value
        stringRedisTemplate.boundValueOps("content_" + categoryId).set(JSON.toJSONString(list));

    }

    private String getColumnValueByCategoryId(CanalEntry.RowData rowData, String category_id) {
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            if (category_id.equals(column.getName())){
                return column.getValue();
            }
        }
        return null;
    }
}
