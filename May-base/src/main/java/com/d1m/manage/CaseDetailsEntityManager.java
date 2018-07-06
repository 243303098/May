package com.d1m.manage;

import com.d1m.entity.ActionEntity;
import com.d1m.entity.CaseDetailsEntity;
import com.d1m.entity.CaseEntity;
import com.d1m.entity.ElementDetailsEntity;

import java.util.ArrayList;
import java.util.List;

public class CaseDetailsEntityManager {

    private List<CaseEntity> caseEntityList ;

    private List<ActionEntity> actionEntityList ;

    private List<ElementDetailsEntity> elementDetailsEntityList;

    CaseDetailsEntity caseDetailsEntity ;

    ElementDetailsEntity elementDetailsEntity ;

    ActionEntity actionEntity ;

    /**
     *
     * 功能描述: 构建caseDetailsEntityList,将case中所用到的数据集合到一个List中
     *
     * @param:  * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 15:03
     */
    public List<CaseDetailsEntity> createCaseDetailsEntityList(String caseEntityPath, String actionEntityPath, String elementEntityPath, String dataEntityPath){
        ElementDetailsEntityManager elementDetailsEntityManager = new ElementDetailsEntityManager();
        List<CaseDetailsEntity> caseDetailsEntityList = new ArrayList<>();
        //获取CaseEntity表中的数据
        caseEntityList = CaseEntityManager.getCaseEntityList(caseEntityPath);
        //获取ActionEntity表中的数据
        actionEntityList = ActionEntityManager.getActionEntityList(actionEntityPath);
        //获取elementDetailsEntityList
        elementDetailsEntityList = elementDetailsEntityManager.createElementDetailsEntity(elementEntityPath, dataEntityPath);
        //遍历CaseEntity表中的数据将调用的其他表中的数据全部转换为可操作的数据
        for (int i = 0; i < caseEntityList.size(); i++) {
            caseDetailsEntity = new CaseDetailsEntity();
            //获取关联的ElementEntity表中的数据
            elementDetailsEntity = ElementDetailsEntityManager.getElementDetailsEntityByID(elementDetailsEntityList, caseEntityList.get(i).getElementId());
            //获取关联的ActionEntity表中的数据
            actionEntity = ActionEntityManager.getActionEntityByID(actionEntityList, caseEntityList.get(i).getActionId());
            caseDetailsEntity.setId(caseEntityList.get(i).getId());
            caseDetailsEntity.setModuleName(caseEntityList.get(i).getModuleName());
            caseDetailsEntity.setPreModule(caseEntityList.get(i).getPreModule());
            if (elementDetailsEntity == null){
                caseDetailsEntity.setElementLocationType(null);
                caseDetailsEntity.setElementPath(null);
                caseDetailsEntity.setElementData(null);
                caseDetailsEntity.setElementComment(null);
            }else {
                caseDetailsEntity.setElementLocationType(elementDetailsEntity.getLocationType());
                caseDetailsEntity.setElementPath(elementDetailsEntity.getPath());
                caseDetailsEntity.setElementData(elementDetailsEntity.getDataValue());
                caseDetailsEntity.setElementComment(elementDetailsEntity.getComment());
            }
            caseDetailsEntity.setActionType(actionEntity.getActionType());
            caseDetailsEntity.setActionKey(actionEntity.getActionKey());
            caseDetailsEntity.setActionComment(actionEntity.getComment());
            caseDetailsEntityList.add(caseDetailsEntity);
        }
        return caseDetailsEntityList;
    }

}
