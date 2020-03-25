package com.changgou.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.DeleteListenPoint;
import com.xpand.starter.canal.annotation.InsertListenPoint;
import com.xpand.starter.canal.annotation.UpdateListenPoint;

import java.util.List;

/**
 * @ClassName CanalDataEventListener
 * @Description 监控数据库变化
 * @Author 传智播客
 * @Date 15:28 2020/2/23
 * @Version 2.1
 **/
@CanalEventListener
public class CanalDataEventListener {

    /**
     * @author 栗子
     * @Description 监控数据库新增操作
     * @Date 15:31 2020/2/23
     * @param entryType 操作数据库的事件类型
     * @param rowData   操作的行数据
     * @return void
     **/
    @InsertListenPoint
    public void onEventInsert(CanalEntry.EntryType entryType, CanalEntry.RowData rowData){
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();
            String columnValue = column.getValue();
            System.out.println("列名：" + columnName + "&列值：" + columnValue);
        }
    }

    /**
     * @author 栗子
     * @Description 监控数据库更新操作
     * @Date 15:31 2020/2/23
     * @param entryType 操作数据库的事件类型
     * @param rowData   操作的行数据
     * @return void
     **/
    @UpdateListenPoint
    public void onEventUpdate(CanalEntry.EntryType entryType, CanalEntry.RowData rowData){
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            String columnName = column.getName();
            String columnValue = column.getValue();
            System.out.println("列名：" + columnName + "&列值：" + columnValue);
        }
        System.out.println("============更新前后的数据============");
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();
            String columnValue = column.getValue();
            System.out.println("列名：" + columnName + "&列值：" + columnValue);
        }
    }

    /**
     * @author 栗子
     * @Description 监控数据库删除操作
     * @Date 15:31 2020/2/23
     * @param entryType 操作数据库的事件类型
     * @param rowData   操作的行数据
     * @return void
     **/
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EntryType entryType, CanalEntry.RowData rowData){
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            String columnName = column.getName();
            String columnValue = column.getValue();
            System.out.println("列名：" + columnName + "&列值：" + columnValue);
        }
    }
}
