package com.d1m.manage;

import com.d1m.entity.CaseDetailsEntity;
import com.d1m.entity.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2018/6/1 10:07
 * @Description:
 */
public class ModuleEntityManager {

    CaseDetailsEntityManager caseDetailsEntityManager;

    ModuleEntity moduleEntity;

    /**
     * 功能描述: 将CaseDetailsEntity按照模块进行划分
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/1 13:33
     */
    public List<ModuleEntity> createModuleEntity(String caseEntityPath, String actionEntityPath, String elementEntityPath, String dataEntityPath) {
        List<ModuleEntity> moduleEntityList = new ArrayList<>();
        //ModuleEntity中的caseDetailsEntityList对象
        List<CaseDetailsEntity> singleCaseList = new ArrayList<>();
        caseDetailsEntityManager = new CaseDetailsEntityManager();
        List<CaseDetailsEntity> caseDetailsEntityList = caseDetailsEntityManager.createCaseDetailsEntityList(caseEntityPath, actionEntityPath, elementEntityPath, dataEntityPath);
        //设置初始的模块名称，默认选择第一行作为默认的名称
        String moduleName = caseDetailsEntityList.get(0).getModuleName();
        //遍历caseDetailsEntityList，将其按模块拆分
        for (int i = 0; i < caseDetailsEntityList.size(); ) {
            //将相同名称的模块进行整合
            if (caseDetailsEntityList.get(i).getModuleName().equals(moduleName)) {
                moduleEntity = new ModuleEntity();
                //将CaseDetailsEntity添加到moduleList中
                if (singleCaseList == null) {
                    singleCaseList = new ArrayList<>();
                }
                singleCaseList.add(caseDetailsEntityList.get(i));
                //如果为最后一个则直接添加到moduleEntity中
                if (i == caseDetailsEntityList.size() - 1) {
                    moduleEntity.setModuleName(moduleName);
                    moduleEntity.setCaseDetailsEntityList(singleCaseList);
                    moduleEntityList.add(moduleEntity);
                }
                i++;
            } else {
                //当模块名不一样时，将之前模块的数据set到moduleEntity并加入到moduleEntityList中
                moduleEntity.setModuleName(moduleName);
                moduleEntity.setCaseDetailsEntityList(singleCaseList);
                moduleEntityList.add(moduleEntity);
                //将List赋值为空，等待再次添加
                singleCaseList = null;
                //如果遇到不相等的模块名称，则替换原有的名称，此时不对i加减操作，下一步依然取当前的值
                moduleName = caseDetailsEntityList.get(i).getModuleName();
            }
        }
        return moduleEntityList;
    }

}
