package com.d1m.entity;

import com.d1m.utils.CellMapping;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:46
 * @Description:  CaseEntity.xls表的实体类
 */
public class CaseEntity {

    @CellMapping(cellName = "ModuleName")
    public String moduleName;

    @CellMapping(cellName = "ID")
    public String id;

    @CellMapping(cellName = "PreModule")
    public String preModule;

    @CellMapping(cellName = "ElementId")
    public String elementId;

    @CellMapping(cellName = "ActionId")
    public String actionId;

    @CellMapping(cellName = "Comment")
    public String comment;

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

    public String getPreModule() {
        return preModule;
    }

    public void setPreModule(String preModule) {
        this.preModule = preModule;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
