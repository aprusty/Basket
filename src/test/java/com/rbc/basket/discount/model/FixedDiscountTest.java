package com.rbc.basket.discount.model;

import com.rbc.basket.Basket;
import com.rbc.basket.BasketItem;
import com.rbc.basket.Item;
import com.rbc.basket.ProcessBasket;

import junit.framework.TestCase;

public class FixedDiscountTest extends TestCase {

	public void testFixed20_PercentDiscount() throws Exception 
    {
		Basket basket = new Basket();
    	basket.add(new BasketItem(new Item("Banana", "Banana", 0.25f, null, "1001"), 3));
    	double total = new ProcessBasket().process(basket);
    	
        assertEquals(0.60, total);
    }
	
	public void testFixed25p_AmountDiscount() throws Exception 
    {
		Basket basket = new Basket();
    	basket.add(new BasketItem(new Item("Apple", "Apple", 0.75f, null, "1002"), 4));
    	double total = new ProcessBasket().process(basket);
    	
        assertEquals(2.00, total);
    }
	
	public void testFixed50_PercentDiscount() throws Exception 
    {
		Basket basket = new Basket();
    	basket.add(new BasketItem(new Item("Banana", "Banana", 20.00f, null, "7001"), 5));
    	double total = new ProcessBasket().process(basket);
    	
        assertEquals(50.00, total);
    }
	
	public void testFixed50p_AmountDiscount() throws Exception 
    {
		Basket basket = new Basket();
    	basket.add(new BasketItem(new Item("Apple", "Apple", 10.00f, null, "7002"), 1));
    	double total = new ProcessBasket().process(basket);
    	
        assertEquals(9.50, total);
    }
}
