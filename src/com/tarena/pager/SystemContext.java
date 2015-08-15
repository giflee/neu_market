package com.tarena.pager;

public class SystemContext {

	private static ThreadLocal<Integer> offset=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> quantity=new ThreadLocal<Integer>();
	
	public static int getOffset() {
		return offset.get();
	}
	public static void setOffset(int offset) {
		SystemContext.offset.set(offset);
	}
	public static int getQuantity() {
		return quantity.get();
	}
	public static void setQuantity(int quantity) {
		SystemContext.quantity .set(quantity);
	}
	public static void removeOffset(){
		SystemContext.offset.remove();
	}
	public static void removeQuantity(){
		SystemContext.quantity.remove();
	}
	
	
}
