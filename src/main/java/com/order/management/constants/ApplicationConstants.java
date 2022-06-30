package com.order.management.constants;

public class ApplicationConstants {
	private ApplicationConstants() {
	}

	public static final String CUSTOMER_PARTITION_KEY = "customer_details";
	public static final String ORDER_PARTITION_KEY = "orders";
	public static final String GOLD = "Gold";
	public static final Double GOLD_DISCOUNT = 10.0;
	public static final String PLATINUM = "Platinum";
	public static final Double PLATINUM_DISCOUNT = 20.0;
	public static final String REGULAR= "Regular";
}
