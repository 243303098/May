package com.d1m.manage;

import com.d1m.entity.ActionEntity;
import com.d1m.utils.FastExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 14:57
 * @Description:
 */
public class ActionEntityManager {

    /**
     *
     * 功能描述: 获取ActionEntity的对象并存储到List中
     *
     * @param:  * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 14:57
     */
    public static List<ActionEntity> getActionEntityList(String actionEntityPath){
        List<ActionEntity> actionEntityList = null;
        InputStream is = ActionEntityManager.class.getClassLoader().getResourceAsStream(actionEntityPath);
        try {
            FastExcel fastExcel = new FastExcel(is);
            actionEntityList = fastExcel.praseExcel(ActionEntity.class);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actionEntityList;
    }

    /**
     *
     * 功能描述: 根据传入的id获取对应的List<ActionEntity>中的ActionEntity
     *
     * @param:  * @param actionEntityList，id
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 17:07
     */
    public static ActionEntity getActionEntityByID(List<ActionEntity> actionEntityList, String id) {
        ActionEntity actionEntity = null;

        for (int i = 0; i < actionEntityList.size(); i++) {
            if (actionEntityList.get(i).getId().trim().equals(id)) {
                actionEntity = actionEntityList.get(i);
                break;
            }
        }
        return actionEntity;
    }

}
