package com.d1m.entity;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2018/6/1 09:54
 * @Description:
 */
public class ModuleEntity {

    private String moduleName;

    private List<CaseDetailsEntity> caseDetailsEntityList;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<CaseDetailsEntity> getCaseDetailsEntityList() {
        return caseDetailsEntityList;
    }

    public void setCaseDetailsEntityList(List<CaseDetailsEntity> caseDetailsEntityList) {
        this.caseDetailsEntityList = caseDetailsEntityList;
    }
}
