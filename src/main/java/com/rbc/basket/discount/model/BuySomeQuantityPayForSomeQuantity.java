package com.rbc.basket.discount.model;

import java.util.List;

import com.rbc.basket.BasketItem;
import com.rbc.basket.discount.Discount;

/**
 * This discount model handles offers like "Buy n get m free"
 * For example, Buy one get one free
 * or Buy two get one free.
 *
 */
public class BuySomeQuantityPayForSomeQuantity implements Discount {
	int buyQuantity;
	int payQuantity;
	
	public BuySomeQuantityPayForSomeQuantity(int buyQuantity, int payQuantity) {
		this.buyQuantity = buyQuantity;
		this.payQuantity = payQuantity;
	}

	public float discountedValue(List<BasketItem> basketItems) {
		int totalItems = 0;
		for (BasketItem item : basketItems) {
			totalItems = totalItems + item.getUnits();
		}
		int itemsToBePaid = (totalItems / buyQuantity) * payQuantity + (totalItems % buyQuantity);
		return itemsToBePaid * basketItems.get(0).getItem().getUnitPrice();
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public int getPayQuantity() {
		return payQuantity;
	}

}
