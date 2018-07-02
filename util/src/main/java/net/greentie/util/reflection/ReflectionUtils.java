package net.greentie.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ReflectionUtils {
	
	private static final Logger logger=Logger.getGlobal();
	
	public static List<Field> getAllDeclaredNoneStaticFields(Class<?> clazz){
		List<Field> filedList=new ArrayList<>();
		do{
			List<Field> fields=Arrays.asList(clazz.getDeclaredFields())
					.stream()
					.filter(field->!Modifier.isStatic(field.getModifiers()))
					.collect(Collectors.toList());
			filedList.addAll(fields);
			clazz=clazz.getSuperclass();
		}while(!clazz.equals(Object.class));
		return filedList;
	}
	
	@SuppressWarnings("unchecked")
	public static  <T> int compare(T a,T b){
		if(a==null&&b==null)return 0;
		if(a==null)return -1;
		if(b==null)return 1;
		if(Comparable.class.isInstance(a)) {
			return Comparable.class.cast(a).compareTo(b);
		}else {
			return a.hashCode()-b.hashCode();
		}
	}
	
	public static <T> int compare(T a,T b,Field f) {
		Object va=null,vb=null;
		f.setAccessible(true);
			try {
				va=f.get(a);
				vb=f.get(b);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.warning("Fail to get the value of the filed"+f.toString());
			}
		return compare(va,vb);
	}
}
