package jp.co.smartservice.fw.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtils {
	
	@SuppressWarnings("rawtypes")
	private static final Class[] emptyClassArray = new Class[0];
	
	private static final Object[] emptyObjectArray = new Object[0];

	@SuppressWarnings("rawtypes")
	public static Object invokeMethod(Object object, String methodName,
			Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		if (parameterTypes == null) {
			parameterTypes = emptyClassArray;
		}
		if (args == null) {
			args = emptyObjectArray;
		}

		Method method = null;
		Class clazz = object.getClass();
		while(true) {
			if (clazz == null) {
				break;
			}
			method = getMatchingMethod(clazz, methodName, parameterTypes);
			if (method == null) {
				clazz = clazz.getSuperclass();
			} else {
				break;
			}
		}
		if (method == null)
			throw new NoSuchMethodException("No such accessible method: "
					+ methodName + "() on object: "
					+ object.getClass().getName());
		return method.invoke(object, args);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getMatchingMethod(Class clazz, String methodName, Class[] parameterTypes) {

		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, parameterTypes);
			if (method != null) {
				method.setAccessible(true);
			}
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		return method;
	}
}
