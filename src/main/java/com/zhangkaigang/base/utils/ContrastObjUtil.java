package com.zhangkaigang.base.utils;


import com.zhangkaigang.base.log.FieldMeta;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description:比较对象的工具类
 * @Author:zhang.kaigang
 * @Date:2019/6/25
 * @Version:1.0
 */

@Component
public class ContrastObjUtil {

    private static Logger logger = LoggerFactory.getLogger(ContrastObjUtil.class);


    /**
     * 获取Obj对象的fieldName属性的值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object fieldValue = null;
        if (null == obj) {
            return null;
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (!methodName.startsWith("get")) {
                continue;
            }
            if (methodName.startsWith("get") && methodName.substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                try {
                    fieldValue = method.invoke(obj, new Object[] {});
                }
                catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    continue;
                }
            }
        }
        return fieldValue;
    }

    /**
     * 普通的两个对象比较，得到修改的内容
     * @param newSource 新的对象
     * @param oldSource 旧对象
     * @return 返回差异内容
     */
    public static String changeContent(Map<String, Object> paramsMap, Object newSource, Object oldSource) {
        // 变更信息message
        StringBuilder changeMessage = new StringBuilder();
        if (null == newSource || null == oldSource) {
            return null;
        }
        // 取出新的class类
        Class<?> newSourceClass = newSource.getClass();
        // 类的所有声明的字段
        Field[] newSourceFields = newSourceClass.getDeclaredFields();
        if (MapUtils.isNotEmpty(paramsMap)) {
            // 有传递表单，则比较表单中的字段差异， 否则比较整个对象
            for (Map.Entry<String, Object> map : paramsMap.entrySet()) {
                for (Field newField : newSourceFields) {
                    if (map.getKey().equals(newField.getName())) {
                        // 处理字段比较
                        handleFieldCompare(newSource, oldSource, changeMessage, newField);
                    } else {
                        continue;
                    }
                }
            }
        } else {
            for (Field newField : newSourceFields) {
                // 如果是引用对象，则退出本次循环。继续下次
                String typeStr = String.valueOf(newField.getType());
                if (typeStr.contains("com") || typeStr.contains("List")) {
                    continue;
                }
                // 处理字段比较
                handleFieldCompare(newSource, oldSource, changeMessage, newField);
            }
        }
        return changeMessage.toString();

    }

    /**
     * 字段的差异比较
     * @param newSource
     * @param oldSource
     * @param changeMessage
     * @param newField
     */
    private static void handleFieldCompare(Object newSource, Object oldSource, StringBuilder changeMessage, Field newField) {

        FieldMeta fieldMeta = newField.getAnnotation(FieldMeta.class);
        if (fieldMeta == null) {
            // 没有需要比较的字段，直接返回
            return;
        }
        String fieldName = newField.getName();
        // 获取新的Field值
        String newValue = getFieldValue(newSource, fieldName) == null ? "" : getFieldValue(newSource, fieldName).toString();
        // 获取对应的旧的targetField值
        String oldValue = getFieldValue(oldSource, fieldName) == null ? "" : getFieldValue(oldSource, fieldName).toString();
        if (StringUtils.isEmpty(newValue) && StringUtils.isEmpty(oldValue)) {
            // 字段新旧没有差异，直接返回
            return;
        }
        if (!newValue.equals(oldValue) && StringUtils.isNotEmpty(fieldMeta.name())) {
            String dictKey = fieldMeta.dictKey();
            if (StringUtils.isNotEmpty(dictKey)) {
                // 有枚举值处理
//                SysDictCacheManager sysDictCacheManager = (SysDictCacheManager)SpringUtil.getBeanByClass(SysDictCacheManager.class);
//                List<SysDict> sysDictList = sysDictCacheManager.getChildDictsByKey(dictKey);
//                for (SysDict sysDict : sysDictList) {
//                    if (oldValue.equals(sysDict.getDictValue())) {
//                        oldValue = sysDict.getDictName();
//                        continue;
//                    }
//                    if (newValue.equals(sysDict.getDictValue())) {
//                        newValue = sysDict.getDictName();
//                        continue;
//                    }
//                }
            }
            // 其他
            changeMessage.append(fieldMeta.name())
                    .append("：由“")
                    .append(oldValue)
                    .append("”")
                    .append("修改成“")
                    .append(newValue)
                    .append("”，");

        }
    }


}
