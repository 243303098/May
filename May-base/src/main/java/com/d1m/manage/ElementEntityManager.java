package com.d1m.manage;

import com.d1m.entity.ElementEntity;
import com.d1m.utils.FastExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 14:42
 * @Description:
 */
public class ElementEntityManager {
    /**
     *
     * 功能描述: 获取ElementEntity的对象并存储到List中
     *
     * @param:  * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 14:46
     */
    public static List<ElementEntity> getDataEntityList(String elementEntityPath){
        List<ElementEntity> elementEntityList = null;
        InputStream is = ElementEntityManager.class.getClassLoader().getResourceAsStream(elementEntityPath);
        try {
            FastExcel fastExcel = new FastExcel(is);
            elementEntityList = fastExcel.praseExcel(ElementEntity.class);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementEntityList;
    }

    /**
     *
     * 功能描述: 根据传入的id获取对应的List<ElementEntity>中的ElementEntity
     *
     * @param:  * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 15:33
     */
    public static ElementEntity getElementEntityByID(List<ElementEntity> elementEntityList, String id){
        ElementEntity elementEntity = null;

        for (int i = 0; i < elementEntityList.size(); i++) {
            if (elementEntityList.get(i).getId().trim().equals(id)){
                elementEntity = elementEntityList.get(i);
                break;
            }
        }
        return elementEntity ;
    }

}
