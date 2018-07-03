package net.greentie.util.sort;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.greentie.util.reflection.ReflectionUtils;
import net.greentie.util.sort.Sort.Direction;
import net.greentie.util.sort.Sort.Order;
import net.greentie.util.sort.annotation.Sortable;

public class SortUtils {

	public <T> void sort(List<T> list, Sort sort) {
		if (sort == null || isEmpty(list) || list.size() <= 1)
			return;
		list.sort(getComparator(sort, list.get(0).getClass()));
	}
	
	public <T> void sort(T[] array, Sort sort) {
		if (array == null || array.length <= 1)
			return;
		Arrays.sort(array, getComparator(sort, array[0].getClass()));
	}

	public <T> void sort(List<T> list, Sort sort, Class<T> entryClass) {
		if (sort == null || isEmpty(list) || list.size() <= 1)
			return;
		list.sort(getComparator(sort, entryClass));
	}

	private boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	
	

	public <T> Comparator<? super T> getComparator(Sort sort, Class<?> entryClass) {
		List<Field> fields = ReflectionUtils.getAllDeclaredNoneStaticFields(entryClass);
		final Map<String, Field> fieldMap = fields.stream().filter(f -> f.getAnnotation(Sortable.class) != null)
				.collect(Collectors.toMap(f -> f.getAnnotation(Sortable.class).value(), Function.identity()));
		return new Comparator<T>() {
			@Override
			public int compare(T a, T b) {
				if (a == null && b == null)
					return 0;
				if (a == null)
					return -1;
				if (b == null)
					return 1;
				if (!sort.iterator().hasNext() || fieldMap.isEmpty()) {
					return ReflectionUtils.compare(a, b);
				} else {
					int ret = 0;
					Iterator<Order> iterator = sort.iterator();
					while (iterator.hasNext() && ret == 0) {
						Order x = iterator.next();
						Field f = fieldMap.get(x.getProperty());
						if (f == null)
							continue;
						else {
							ret = ReflectionUtils.compare(a, b, f);
							if (x.getDirection().equals(Direction.DESC) && ret != 0)
								ret = -ret;
						}
					}
					if (ret == 0) {
						ret = a.hashCode() - b.hashCode();
					}
					return ret;
				}
			}
		};
	}
}
