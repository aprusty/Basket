package com.rbc.basket;

import junit.framework.TestCase;

public class ProcessBasketTest 
    extends TestCase
{
    public void testWholeBasket() throws Exception
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
    	
        assertEquals(10.60d, total);
    }
}
