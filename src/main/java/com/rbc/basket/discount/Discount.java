package com.rbc.basket.discount;

import java.util.List;

import com.rbc.basket.BasketItem;

/**
 * All the discount model classes need to implement this interface.
 */
public interface Discount {
	float discountedValue(List<BasketItem> basketItems);
}
