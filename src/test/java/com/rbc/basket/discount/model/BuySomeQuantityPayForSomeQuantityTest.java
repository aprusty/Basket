package com.rbc.basket.discount.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rbc.basket.Basket;
import com.rbc.basket.BasketItem;
import com.rbc.basket.Item;
import com.rbc.basket.ProcessBasket;
import com.rbc.basket.discount.DiscountNotFoundException;

public class BuySomeQuantityPayForSomeQuantityTest {
	@Test
	public void testBuy3Pay2_SingleItemOneUnit() throws DiscountNotFoundException 
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 1));
		double total = new ProcessBasket().process(basket);

		assertEquals(1.00, total, 0.00);
	}

	@Test
	public void testBuy3Pay2_SingleItemMultipleUnits() throws DiscountNotFoundException 
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 4));
		double total = new ProcessBasket().process(basket);

		assertEquals(3.00, total, 0.00);
	}

	@Test
	public void testBuy3Pay2_ManyScanes() throws DiscountNotFoundException 
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 2));
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 4));
		double total = new ProcessBasket().process(basket);

		assertEquals(4.00, total, 0.00);
	}

	@Test
	public void testBuy1Get1Free_ManyScanes() throws DiscountNotFoundException 
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 3));
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "7003", "7003"), 2));
		double total = new ProcessBasket().process(basket);

		assertEquals(3.00, total, 0.00);
	}
}
