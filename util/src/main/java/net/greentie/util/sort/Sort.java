package net.greentie.util.sort;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Sort {

	public static enum Direction{
		ASC,DESC
	}
	
	public static class Order{
		
		private String property;
		
		private Sort.Direction direction;
		
		public Order(String property) {
			this(Direction.ASC,property);
		}
		public Order(Direction direction,String property) {
			this.property=property;
			this.direction=direction;
		}
		
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public Sort.Direction getDirection() {
			return direction;
		}
		public void setDirection(Sort.Direction direction) {
			this.direction = direction;
		};
	}
	
	private LinkedList<Order> orders;
	
	public Sort(List<Order> orders) {
		this.orders=new LinkedList<>();
		if(orders!=null&&!orders.isEmpty())
			this.orders.addAll(orders);
	}
	
	public Sort(Order...orders) {
		this(Arrays.asList(orders));
	}
	
	public Iterator<Order> iterator() {
		return orders.iterator();
	}
	
}
