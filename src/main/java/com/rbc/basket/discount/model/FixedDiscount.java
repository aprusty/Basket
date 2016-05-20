package com.rbc.basket.discount.model;

import java.util.List;

import com.rbc.basket.BasketItem;
import com.rbc.basket.discount.Discount;
import com.rbc.basket.discount.DiscountType;

/**
 * This discount model handles simple offers like 20% or 50% or £2 off on the item
 */
public class FixedDiscount implements Discount {
	float amount;
	DiscountType type;
	BasketItem basketItem;
	
	public FixedDiscount(DiscountType type, float amount) {
		this.type = type;
		this.amount = amount;
	}

	public float discountedValue() {
		float discountedValue = 0;
		float unitPrice = basketItem.getItem().getUnitPrice();
		int units = basketItem.getUnits();
		
		if (type == DiscountType.FIXED_PERCENT) 
			discountedValue = unitPrice * units * (100 - amount) / 100;
		else if (type == DiscountType.FIXED_AMOUNT)
			discountedValue = (unitPrice - amount) * units;
		
		return discountedValue;
	}

	public void setRelatedItems(List<BasketItem> items) {
		basketItem = items.get(0);
	}

}
