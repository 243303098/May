package com.d1m.entity;

import com.d1m.utils.CellMapping;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:46
 * @Description:  ActionEntity.xls表的实体类
 */
public class ActionEntity {

    @CellMapping(cellName = "ID")
    public String id;

    @CellMapping(cellName = "ActionType")
    public String actionType;

    @CellMapping(cellName = "ActionKey")
    public String actionKey;

    @CellMapping(cellName = "Comment")
    public String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
