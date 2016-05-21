package com.rbc.basket.discount.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.rbc.basket.Basket;
import com.rbc.basket.BasketItem;
import com.rbc.basket.Item;
import com.rbc.basket.ProcessBasket;
import com.rbc.basket.discount.DiscountNotFoundException;

public class CombinedFixedPriceTest {

	@Test
	public void testThreeItemsCost3Pounds_SingleItem() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Orange", "Orange", 1.00f, "5000", "5001"), 1));
		double total = new ProcessBasket().process(basket);

		assertEquals(1.00, total, 0.00);
	}

	@Test
	public void testThreeItemsCost3Pounds_TwoItems() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Orange", "Orange", 1.00f, "5000", "5001"), 1));
		basket.add(new BasketItem(new Item("Coke", "Coke", 1.00f, "5000", "5002"), 1));
		double total = new ProcessBasket().process(basket);

		assertEquals(2.00, total, 0.00);
	}

	@Test
	public void testThreeItemsCost3Pounds_AllItems() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Orange", "Orange", 1.00f, "5000", "5001"), 1));
		basket.add(new BasketItem(new Item("Coke", "Coke", 1.00f, "5000", "5002"), 1));
		basket.add(new BasketItem(new Item("Sandwitch", "Sandwitch", 2.00f, "5000", "5003"), 1));
		double total = new ProcessBasket().process(basket);

		assertEquals(3.00, total, 0.00);
	}

	@Test
	public void testThreeItemsCost3Pounds_AllItemsMultiples() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Orange", "Orange", 1.00f, "5000", "5001"), 4));
		basket.add(new BasketItem(new Item("Coke", "Coke", 1.00f, "5000", "5002"), 4));
		basket.add(new BasketItem(new Item("Sandwitch", "Sandwitch", 2.00f, "5000", "5003"), 4));
		double total = new ProcessBasket().process(basket);

		assertEquals(12.00, total, 0.00);
	}

	@Test
	public void testThreeItemsCost3Pounds_MisMatchItems() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Orange", "Orange", 1.00f, "5000", "5001"), 4));
		basket.add(new BasketItem(new Item("Coke", "Coke", 1.00f, "5000", "5002"), 3));
		basket.add(new BasketItem(new Item("Sandwitch", "Sandwitch", 2.00f, "5000", "5003"), 2));

		double total = new ProcessBasket().process(basket);

		assertEquals(9.00, total, 0.00);
	}
}
