package com.d1m.entity;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:46
 * @Description: ActionEntity.xls，CaseEntity.xls，ElementDetailsEntity的整合，
 * 将四张表中相关联的数据整合到一张表中，作为测试基类
 */
public class CaseDetailsEntity {

    private String moduleName;

    private String id;

    private String preModule;

    private String elementLocationType;

    private String elementPath;

    private String elementData;

    private String elementComment;

    private String actionType;

    private String actionKey;

    private String actionComment;

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

    public String getElementLocationType() {
        return elementLocationType;
    }

    public void setElementLocationType(String elementLocationType) {
        this.elementLocationType = elementLocationType;
    }

    public String getElementPath() {
        return elementPath;
    }

    public void setElementPath(String elementPath) {
        this.elementPath = elementPath;
    }

    public String getElementData() {
        return elementData;
    }

    public void setElementData(String elementData) {
        this.elementData = elementData;
    }

    public String getElementComment() {
        return elementComment;
    }

    public void setElementComment(String elementComment) {
        this.elementComment = elementComment;
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

    public String getActionComment() {
        return actionComment;
    }

    public void setActionComment(String actionComment) {
        this.actionComment = actionComment;
    }

}
