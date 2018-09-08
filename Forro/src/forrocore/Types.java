/**
 * 
 */
package forrocore;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ccacore.Type;
import ccacore.TypeMap;

/**
 * @author correa
 *
 */
public class Types {

	private static final Map<Class, Type> typeMap = new HashMap<Class, Type>();
	
	{
		Method[] method = TypeMap.class.getDeclaredMethods();
		String methodName;
		String typeName;
		String prefix = "put";
		int prefixLength = prefix.length();
		String suffix = "Array";
		int suffixLength = suffix.length();
		for (int i = 0; i < method.length; i++) {
			methodName = method[i].getName();
			if (methodName.startsWith(prefix)) {
				if (methodName.endsWith(suffix))
					typeName = methodName.substring(prefixLength, methodName.length() - suffixLength) + "[]";
				else
					typeName = methodName.substring(prefixLength, methodName.length());

				try {
					typeMap.put(Class.forName(typeName), new Type());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Answers whether a specified object is of type boolean.
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isBoolean(Object t) {
		return (t == typeMap.get(Boolean.class)) || (t instanceof Boolean);
	}
	
	/**
	 * Answers whether a specified object is of type String.
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isString(Object t) {
		return (t == typeMap.get(String.class)) || (t instanceof String);
	}
	
	public static Type getType(Class<?> c) {
		Class<?> s = c.getSuperclass();
		while (s != null && !s.equals(Object.class))
			s = s.getSuperclass();
		return typeMap.get(s);
	}
	
	public static Type getBooleanType() {
		return typeMap.get(Boolean.class);
	}
	
	public static Type getStringType() {
		return typeMap.get(String.class);
	}
	
}
