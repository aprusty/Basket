package com.rbc.basket;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rbc.basket.discount.DiscountNotFoundException;

public class ProcessBasketTest 
{
	@Rule
	public final ExpectedException exceptionThrown = ExpectedException.none();

	@Test 
	public void testWholeBasket() throws DiscountNotFoundException
	{
		Basket basket = new Basket();

		//Items with offer
		basket.add(new BasketItem(new Item("Banana", "Banana", 0.25f, null, "1001"), 3));
		basket.add(new BasketItem(new Item("Apple", "Apple", 0.75f, null, "1002"), 2));
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 1));
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "1003", "1003"), 4));

		// Items without any offer
		basket.add(new BasketItem(new Item("Orange", "Orange", 1.00f, null, null), 2));
		basket.add(new BasketItem(new Item("Lemon", "Lemon", 1.00f, null, null), 3));

		double total = new ProcessBasket().process(basket);

		assertEquals(10.60d, total, 0.00);
	}

	@Test
	public void testWholeBasketWithInvalidDiscountCode() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Banana", "Banana", 0.25f, null, "9001"), 3));

		exceptionThrown.expect(DiscountNotFoundException.class);
		exceptionThrown.expectMessage("Discount details not found for discount Code: 9001");

		double total = new ProcessBasket().process(basket);

		assertEquals(10.60d, total, 0.00);
	}

	@Test
	public void testWholeBasketWithInvalidParentDiscountCode() throws DiscountNotFoundException
	{
		Basket basket = new Basket();
		basket.add(new BasketItem(new Item("Peach", "Peach", 1.00f, "9003", "9003"), 1));

		exceptionThrown.expect(DiscountNotFoundException.class);
		exceptionThrown.expectMessage("Parent Discount details not found for discount Code: 9003");

		double total = new ProcessBasket().process(basket);

		assertEquals(10.60d, total, 0.00);
	}
}
