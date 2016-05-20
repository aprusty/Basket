package com.rbc.basket;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the complete Shopping Basket
 * The basket list holds list of BasketItems 
 * and each BasketItem got an item and it's quantity
 */
public class Basket {

	List<BasketItem> basket;

	public Basket() {
		basket = new ArrayList<BasketItem>();
	}

	public void add(BasketItem basketItem) {
		basket.add(basketItem);
	}

	public List<BasketItem> getBasket() {
		return basket;
	}
}
