package com.zhangkaigang.base.utils;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:POJO类转换器，DTO、VO、PO类相互转换
 * @Author:zhang.kaigang
 * @Date:20120/4/21 19:18
 * @Version:1.0
 */
public class PoJoConverterUtil {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 普通对象转换 比如: ADO -> AVO
     * @param: [source 源对象, destinationClass 目标对象class]
     * @return: T
     */
    public static <T> T objectConverter(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * List转换 比如: List<ADO> -> List<AVO>
     * @param: [sourceList 源对象List, destinationClass 目标对象class]
     * @return: java.util.List<T>
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> objectListConverter(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList();
        if (sourceList == null) {
            return destinationList;
        }
        for (Object sourceObject : sourceList) {
            if (sourceObject != null) {
                T destinationObject = dozer.map(sourceObject, destinationClass);
                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }

}
