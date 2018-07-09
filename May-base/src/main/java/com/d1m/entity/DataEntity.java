package com.d1m.entity;

import com.d1m.utils.CellMapping;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:46
 * @Description:  DataEntity.xls表的实体类
 */
public class DataEntity {

    @CellMapping(cellName = "ID")
    public String id;

    @CellMapping(cellName = "Data")
    public String data;

    @CellMapping(cellName = "Comment")
    public String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
