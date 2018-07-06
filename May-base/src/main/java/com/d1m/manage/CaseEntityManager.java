package com.d1m.manage;

import com.d1m.entity.CaseEntity;
import com.d1m.utils.FastExcel;
import com.d1m.utils.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CaseEntityManager {
    /**
     *
     * 功能描述: 获取caseEntity的对象并存储到List中
     *
     * @param:  * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/5/31 14:35
     */
    public static List<CaseEntity> getCaseEntityList(String caseEntityPath){
        List<CaseEntity> caseEntityList = null;
        InputStream is = Test.class.getClassLoader().getResourceAsStream(caseEntityPath);
        try {
            FastExcel fastExcel = new FastExcel(is);
            caseEntityList = fastExcel.praseExcel(CaseEntity.class);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caseEntityList;
    }

}
