package com.d1m.entity;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:46
 * @Description:  ElementEntity.xls与DataEntity.xls的整合，将量表之间关联的参数整合到一张表
 */
public class ElementDetailsEntity {

    private String moduleName;

    private String id;

    private String locationType;

    private String path;

    private String dataValue;

    private String comment;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

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

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
