package com.rbc.basket.discount.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.rbc.basket.BasketItem;
import com.rbc.basket.discount.Discount;

/**
 * This discount model handles offers like Meal Deal.
 * For example, Wrap=£2.00 + Drink=£1.00 + Snacks=£0.75, Total = £3.75
 * But the deal is to pay only £3.00 for all three together.
 */
public class CombinedFixedPrice implements Discount {
    List<BasketItem> basketItems;
	String discountId1;
	String discountId2;
	String discountId3;
	float combinedValue;
	
	public CombinedFixedPrice(String discountId1, String discountId2, String discountId3, float totalValue) {
		this.discountId1 = discountId1;
		this.discountId2 = discountId2;
		this.discountId3 = discountId3;
		this.combinedValue = totalValue;
	}

	public void setRelatedItems(List<BasketItem> items) {
		this.basketItems = items;
	}

	public float discountedValue() {
		float discountedValue = 0;
		int discountedItems1 = 0;
		int discountedItems2 = 0;
		int discountedItems3 = 0;
		List<Float> items1 = new ArrayList<Float>();
		List<Float> items2 = new ArrayList<Float>();
		List<Float> items3 = new ArrayList<Float>();
		
		for (BasketItem basketItem : basketItems) {
			int units = basketItem.getUnits();
			if (discountId1.equals(basketItem.getItem().getDiscount())) {
				splitUnitsToIndividualItems(items1, basketItem, units);
				discountedItems1 = discountedItems1 + basketItem.getUnits();
			}
			else if (discountId2.equals(basketItem.getItem().getDiscount())) {
				splitUnitsToIndividualItems(items2, basketItem, units);
				discountedItems2 = discountedItems2 + basketItem.getUnits();
			}
			else if (discountId3.equals(basketItem.getItem().getDiscount())) {
				splitUnitsToIndividualItems(items3, basketItem, units);
				discountedItems3 = discountedItems3 + basketItem.getUnits();
			}
		}	
		descendingSortLists(items1, items2, items3);
		
		int minDeals = Math.min(discountedItems1, Math.min(discountedItems2, discountedItems3));
		items1 = removeDealPrices(items1, minDeals);
		items2 = removeDealPrices(items2, minDeals);
		items3 = removeDealPrices(items3, minDeals);
		
		discountedValue = minDeals * combinedValue;
		discountedValue = discountedValue + AddRemainingPrices(items1); // Add remaining items, if any
		discountedValue = discountedValue + AddRemainingPrices(items2); // Add remaining items, if any
		discountedValue = discountedValue + AddRemainingPrices(items3); // Add remaining items, if any
		
		return discountedValue;
	}

	private List<Float> removeDealPrices(List<Float> items, int minDeals) {
		return items.subList(minDeals, items.size());
	}

	private void descendingSortLists(List<Float> items1, List<Float> items2, List<Float> items3) {
		Comparator<Float> comparator = Collections.reverseOrder();
		Collections.sort(items1, comparator);
		Collections.sort(items2, comparator);
		Collections.sort(items3, comparator);
	}

	private void splitUnitsToIndividualItems(List<Float> items, BasketItem basketItem, int units) {
		for (int i = 0; i < units; i++) {
			items.add(basketItem.getItem().getUnitPrice());
		}
	}

	private float AddRemainingPrices(List<Float> items) {
		float total = 0;
		for (float price : items) {
			total = total + price;
		}
		return total;
	}

}
