package com.d1m.manage;

import com.d1m.entity.DataEntity;
import com.d1m.entity.ElementDetailsEntity;
import com.d1m.entity.ElementEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 15:59
 * @Description:
 */
public class ElementDetailsEntityManager {

    private List<ElementEntity> elementEntityList;

    private List<DataEntity> dataEntityList;

    ElementDetailsEntity elementDetailsEntity;

    /**
     * 功能描述: 创建elementDetailsEntity对象，将与之关联的dataEntity合并
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 16:51
     */
    public List<ElementDetailsEntity> createElementDetailsEntity(String elementEntityPath, String dataEntityPath) {
        List<ElementDetailsEntity> elementDetailsEntityList = new ArrayList<>();
        elementEntityList = ElementEntityManager.getDataEntityList(elementEntityPath);
        dataEntityList = DataEntityManager.getDataEntityList(dataEntityPath);
        for (int i = 0; i < elementEntityList.size(); i++) {
            elementDetailsEntity = new ElementDetailsEntity();
            String keyData = DataEntityManager.getDataByDataKey(dataEntityList, elementEntityList.get(i).getDataKey());
            elementDetailsEntity.setId(elementEntityList.get(i).getId());
            elementDetailsEntity.setModuleName(elementEntityList.get(i).getModuleName());
            elementDetailsEntity.setLocationType(elementEntityList.get(i).getLocationType());
            elementDetailsEntity.setPath(elementEntityList.get(i).getPath());
            elementDetailsEntity.setDataValue(keyData);
            elementDetailsEntity.setComment(elementEntityList.get(i).getComment());
            elementDetailsEntityList.add(elementDetailsEntity);
        }
        return elementDetailsEntityList;
    }

    /**
     * 功能描述: 根据传入的id获取对应的List<ElementDetailsEntity>中的ElementDetailsEntity
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 17:00
     */
    public static ElementDetailsEntity getElementDetailsEntityByID(List<ElementDetailsEntity> elementDetailsEntityList, String id) {
        ElementDetailsEntity elementDetailsEntity = null;

        for (int i = 0; i < elementDetailsEntityList.size(); i++) {
            if (elementDetailsEntityList.get(i).getId().trim().equals(id)) {
                elementDetailsEntity = elementDetailsEntityList.get(i);
                break;
            }
        }
        return elementDetailsEntity;
    }

}
