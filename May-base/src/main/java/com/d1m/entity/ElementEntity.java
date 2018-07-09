package com.d1m.entity;

import com.d1m.utils.CellMapping;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:46
 * @Description: ElementEntity.xls表的实体类
 */
public class ElementEntity {

    @CellMapping(cellName = "ID")
    public String id;

    @CellMapping(cellName = "LocationType")
    public String locationType;

    @CellMapping(cellName = "Path")
    public String path;

    @CellMapping(cellName = "Comment")
    public String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
