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
     * 功能描述: 根据dataKey获取DataEntity的数据，dataKey由dataID和具体的参数值组成
     *
     * @param:  * @param dataEntityList， dataKey
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 16:30
     */
    public static String getDataByDataKey(List<DataEntity> dataEntityList, String dataKey){
        DataEntity dataEntity ;
        String id = null ;
        String key = null ;
        //将dataKey按“.”切分，前部分为DataEntity表中的ID，后半部分为具体的值的Key
        if (!StringTools.isNullOrEmpty(dataKey)){
            String[] data = dataKey.split("\\.");
            if (data.length > 1){
                id = data[0];
                key = data[1];
            }
            for (int i = 0; i < dataEntityList.size(); i++) {
                //判断值是否与输入的相等，相等则取出并跳出循环
                if (dataEntityList.get(i).getId().trim().equals(id)){
                    dataEntity = dataEntityList.get(i);
                    JSONObject keyDataJson = JSONObject.parseObject(dataEntity.getKeyData());
                    //dataEntity.setKeyData(keyDataJson.getString(key));
                    return keyDataJson.getString(key);
                }
            }
        }
        return null;
    }

}
