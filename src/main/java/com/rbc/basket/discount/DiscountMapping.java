package com.rbc.basket.discount;

import java.util.HashMap;
import java.util.Map;

import com.rbc.basket.discount.model.BuySomeQuantityPayForSomeQuantity;
import com.rbc.basket.discount.model.CombinedFixedPrice;
import com.rbc.basket.discount.model.FixedDiscount;

/**
 * This class holds the mappings of discount code and it's discount model 
 * The key can be either parentDiscount code or discount code
 * In ideal world, this mapping data will be read from database and cached.
 * 
 */
public class DiscountMapping {

	private static final Map<String, Discount> discountMappings = new HashMap<String, Discount>();
	static {
		discountMappings.put("1001", new FixedDiscount(DiscountType.FIXED_PERCENT, 20));
		discountMappings.put("1002", new FixedDiscount(DiscountType.FIXED_AMOUNT, 0.25f));
		discountMappings.put("7001", new FixedDiscount(DiscountType.FIXED_PERCENT, 50));
		discountMappings.put("7002", new FixedDiscount(DiscountType.FIXED_AMOUNT, 0.50f));
		
		discountMappings.put("1003", new BuySomeQuantityPayForSomeQuantity(3, 2));
		discountMappings.put("7003", new BuySomeQuantityPayForSomeQuantity(2, 1));
		
		discountMappings.put("5000", new CombinedFixedPrice("5001", "5002", "5003", 3.00f));
	 }
	
	/**
	 * @param discountId
	 * @return : returns a new copy everytime as these model classes are not immutable
	 */
	public static Discount getDiscountModel(String discountId) {
		Discount discount = discountMappings.get(discountId);
		return discount == null ? null : discount.getNewCopy(discount);
	}
}
