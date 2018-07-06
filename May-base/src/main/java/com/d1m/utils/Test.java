package com.d1m.utils;


import com.d1m.manage.ActionEntityManager;

import java.io.InputStream;

public class Test {

    @org.testng.annotations.Test
    public void test(){
        String path = "dior";
        InputStream is = ActionEntityManager.class.getClassLoader().getResourceAsStream(path+"/CaseEntity.xls");
        System.out.println(is);
    }
}
