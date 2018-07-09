package com.d1m.manage;

import com.alibaba.fastjson.JSONObject;
import com.d1m.entity.DataEntity;
import com.d1m.utils.FastExcel;
import com.d1m.utils.StringTools;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2018/5/31 14:38
 * @Description:
 */
public class DataEntityManager {
    /**
     *
     * 功能描述: 获取DataEntity的对象并存储到List中
     *
     * @param:  * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 14:40
     */
    public static List<DataEntity> getDataEntityList(String dataEntityPath){
        List<DataEntity> dataEntityList = null;
        InputStream is = DataEntityManager.class.getClassLoader().getResourceAsStream(dataEntityPath);
        try {
            FastExcel fastExcel = new FastExcel(is);
            dataEntityList = fastExcel.praseExcel(DataEntity.class);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataEntityList;
    }

    /**
     *
     * 功能描述: 根据dataId 获取DataEntity的数据
     *
     * @param:  * @param dataEntityList， dataKey
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 16:30
     */
    public static DataEntity getDataEntityByDataId(List<DataEntity> dataEntityList, String dataId) {
        DataEntity dataEntity = null;
        for (int i = 0; i < dataEntityList.size(); i++) {
            if (dataEntityList.get(i).getId().equals(dataId)){
                dataEntity = dataEntityList.get(i);
                break;
            }
        }
        return dataEntity;
    }

}
