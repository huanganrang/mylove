package jb.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanToMapUtil {
	
	/**
	 * @param o
	 * @param cProperties
	 * @param ucProperties
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertBean(Object o, String[] cProperties, String[] ucProperties, boolean isNull) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
        Class type = o.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(o, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
	
	@SuppressWarnings("rawtypes")
	public static Map convertBean(Object o, String[] cProperties) throws IllegalArgumentException, IntrospectionException, IllegalAccessException, InvocationTargetException{
		return convertBean(o, cProperties, null, false);
    }
	
	@SuppressWarnings("rawtypes")
	public static Map convertBean(Object o, String[] ucProperties, boolean isNull) throws IllegalArgumentException, IntrospectionException, IllegalAccessException, InvocationTargetException{
		return convertBean(o, null, ucProperties, isNull);
	}
	
	@SuppressWarnings("rawtypes")
	public static Map convertBean(Object o) throws IllegalArgumentException, IntrospectionException, IllegalAccessException, InvocationTargetException{
		return convertBean(o, null, null, false);
	}
	
	@SuppressWarnings("rawtypes")
	public static Map convertBean(Object o, boolean isNull) throws IllegalArgumentException, IntrospectionException, IllegalAccessException, InvocationTargetException{
		return convertBean(o, null, null, isNull);
	}
	
}
