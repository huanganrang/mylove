package jb.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class BeanToMapUtil {
	
	/**
	 * @param o
	 * @param cProperties 传入只需转的字段属性,而不考虑ucProperties、isNull两个参数
	 * @param ucProperties 传入不需要转的字段属性
	 * @param isNull 为true时则为空字段不进行转换
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> convertBean(Object o, String[] cProperties, String[] ucProperties, boolean isNull) {
		Assert.notNull(o, "Source must not be null");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
	        Class type = o.getClass();
	        BeanInfo beanInfo = Introspector.getBeanInfo(type);
	
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
	        List cList = cProperties != null ? Arrays.asList(cProperties) : null;
	        List ucList = ucProperties != null ? Arrays.asList(ucProperties) : null;
	        
	        for (int i = 0; i< propertyDescriptors.length; i++) {
	            PropertyDescriptor descriptor = propertyDescriptors[i];
	            String propertyName = descriptor.getName();
	            if (!propertyName.equals("class")) {
	                Method readMethod = descriptor.getReadMethod();
	                if(cProperties != null && !cList.contains(propertyName)) {
	                	continue;
	                } 
	                if(ucProperties != null && ucList.contains(propertyName)) {
	                	continue;
	                }
	                Object result = readMethod.invoke(o, new Object[0]);
	                if ((cProperties != null && cList.contains(propertyName)) || !isNull || (isNull && result != null)) {
	                	result = result == null ? "" : result;
	                    returnMap.put(propertyName, result);
	                } 
	            }
	        }
		} catch(Throwable ex) {
			 throw new FatalBeanException("Could not convert from bean to map", ex);
		}
        return returnMap;
    }
	
	public static Map<String, Object> convertBean(Object o, String[] cProperties) {
		return convertBean(o, cProperties, null, false);
    }
	
	public static Map<String, Object> convertBean(Object o, String[] ucProperties, boolean isNull) {
		return convertBean(o, null, ucProperties, isNull);
	}
	
	public static Map<String, Object> convertBean(Object o) {
		return convertBean(o, null, null, false);
	}
	
	public static Map<String, Object> convertBean(Object o, boolean isNull) {
		return convertBean(o, null, null, isNull);
	}
	
}
