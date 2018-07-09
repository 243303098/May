package com.d1m.manage;

import com.d1m.entity.*;

import java.util.ArrayList;
import java.util.List;

public class CaseDetailsEntityManager {

    private List<CaseEntity> caseEntityList ;

    private List<ActionEntity> actionEntityList ;

    private List<ElementEntity> elementEntityList;

    private List<DataEntity> dataEntityList ;

    CaseDetailsEntity caseDetailsEntity ;

    ElementEntity elementEntity ;

    DataEntity dataEntity;

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
        //ElementDetailsEntityManager elementDetailsEntityManager = new ElementDetailsEntityManager();
        List<CaseDetailsEntity> caseDetailsEntityList = new ArrayList<>();
        //获取CaseEntity表中的数据
        caseEntityList = CaseEntityManager.getCaseEntityList(caseEntityPath);
        //获取ActionEntity表中的数据
        actionEntityList = ActionEntityManager.getActionEntityList(actionEntityPath);
        //获取elementEntityList
        elementEntityList = ElementEntityManager.getDataEntityList(elementEntityPath);
        //获取dataEntityList
        dataEntityList = DataEntityManager.getDataEntityList(dataEntityPath);
        //遍历CaseEntity表中的数据将调用的其他表中的数据全部转换为可操作的数据
        for (int i = 0; i < caseEntityList.size(); i++) {
            caseDetailsEntity = new CaseDetailsEntity();
            //获取关联的ElementEntity表中的数据
            elementEntity = ElementEntityManager.getElementEntityByID(elementEntityList, caseEntityList.get(i).getElementId());
            //获取关联的ActionEntity表中的数据
            actionEntity = ActionEntityManager.getActionEntityByID(actionEntityList, caseEntityList.get(i).getActionId());
            //获取关联的DataEntity表中的数据
            dataEntity = DataEntityManager.getDataEntityByDataId(dataEntityList, caseEntityList.get(i).getDataId());

            caseDetailsEntity.setId(caseEntityList.get(i).getId());
            caseDetailsEntity.setModuleName(caseEntityList.get(i).getModuleName());
            caseDetailsEntity.setPreModule(caseEntityList.get(i).getPreModule());
            if (elementEntity == null){
                caseDetailsEntity.setElementLocationType(null);
                caseDetailsEntity.setElementPath(null);
                caseDetailsEntity.setElementComment(null);
            }else {
                caseDetailsEntity.setElementLocationType(elementEntity.getLocationType());
                caseDetailsEntity.setElementPath(elementEntity.getPath());
                caseDetailsEntity.setElementComment(elementEntity.getComment());
            }
            if (dataEntity == null){
                caseDetailsEntity.setElementData(null);
            }else {
                caseDetailsEntity.setElementData(dataEntity.getData());
            }
            caseDetailsEntity.setActionType(actionEntity.getActionType());
            caseDetailsEntity.setActionKey(actionEntity.getActionKey());
            caseDetailsEntity.setActionComment(actionEntity.getComment());
            caseDetailsEntityList.add(caseDetailsEntity);
        }
        return caseDetailsEntityList;
    }

}
