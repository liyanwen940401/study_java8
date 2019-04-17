package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.domin.Apple;
import com.domin.FaceAuthenGroup;
import com.domin.Trader;
import com.domin.Trader1;
import com.interFace.Consumer;
import com.interFace.Function;
import com.interFace.Predicate;
import com.sun.deploy.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class test_faceAuthen {


    @Test
    public void test_faceAuthen()throws Exception{
       /* FaceAuthenGroup faceGroup = new FaceAuthenGroup();
        faceGroup.setGroup_id("12");
        faceGroup.setGroup_name("保洁-d");
        faceGroup.setGroup_remark("测试新增保洁组");
        faceGroup.setApp_id("124ar3rfw342");
        faceGroup.setCmd("cm.service.face");
        faceGroup.setSig_method("HmacMD5");
        String str = String.valueOf(new Date().getTime()/1000);
        faceGroup.setTimestamp(str);
        String str1 = getFaceAuthenData(faceGroup.getClass(),faceGroup);*/

        String str = "{\"code\":0,\"msg\":\"ok\",\"data\":{\"result\":true,\"group_id\":\"ae4d4062-4a18-11e9-8895-525400a39bef\",\"group_name\":\"\u642c\u5bb6-d\"}}";
        JSONObject result = JSON.parseObject(str);
        Object o1 = result.get("code");
        Object o2 = result.get("data");
        JSONObject result2 = JSON.parseObject(o2.toString());
        result2.get("group_id");
    }
    public String getFaceAuthenData(Class< ? > c,Object o)  throws Exception{
        List<String> fieldSub = Arrays.asList(c.getDeclaredFields()).stream()
                .map(Field :: getName)
                .collect(Collectors.toList());
        List<String> fieldSup = Arrays.asList(c.getSuperclass().getDeclaredFields()).stream()
                .map(Field :: getName)
                .collect(Collectors.toList());
        fieldSub.addAll(fieldSup);
        List<String> fields = fieldSub.stream().sorted().collect(Collectors.toList());
        StringBuffer str = new StringBuffer();
        for(String s:fields){
            Method m = (Method) c.getMethod(
                    "get" + getMethodName(s));
            String val = (String) m.invoke(o);// 调用getter方法获取属性值
            if(val != null && !val.equals("")){
                str=str.append("&"+s+"="+val);
            }
        }
        return str.toString();
    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}