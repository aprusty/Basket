package com.rbc.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbc.basket.discount.Discount;
import com.rbc.basket.discount.DiscountMapping;
import com.rbc.basket.discount.DiscountNotFoundException;

/**
 * This process() method of this class takes a basket and calculate the basket Total
 * 
 * First, it calculate the items those are not on any offer and simple offer items. 
 * The simple offer means the item doesn't have any link to any other item or quantity bought.
 * The design is such that for all non-simple offers, the Item will have both
 * parentDiscount and discount information.
 * 
 * The complex offer items are stored by parentDiscount in Map offerItems for further processing.
 * 
 * This class has no knowledge of the implementations of discount models
 */
public class ProcessBasket {

	Map<String, List<BasketItem>> offerItems = new HashMap<String, List<BasketItem>>();

	/**
	 * @param basket : Takes a basket to calculate the total cost
	 * @return : return total value of the basket
	 * @throws DiscountNotFoundException
	 */
	public double process (Basket basket) throws DiscountNotFoundException {
		BigDecimal basketTotal = new BigDecimal(0);

		for (BasketItem basketItem : basket.getBasket()) {
			Item item = basketItem.getItem();
			float value = item.getUnitPrice() * basketItem.getUnits();

			String parentDiscountCode = item.getParentDiscount();
			if (parentDiscountCode != null) {
				List<BasketItem> existingItems = offerItems.get(parentDiscountCode);
				if (existingItems == null) {
					existingItems = new ArrayList<BasketItem>();
					offerItems.put(parentDiscountCode, existingItems);
				}
				existingItems.add(basketItem);
			} else {
				if (item.getDiscount() != null) {
					value = getPriceOfSimpleOffers(basketItem);
				}
				basketTotal = basketTotal.add(new BigDecimal(value));
			}	
		}

		for (String parentOffer : offerItems.keySet()) {
			List<BasketItem> itemsWithSameParentId = offerItems.get(parentOffer);
			float discountedValue = getPriceOfComplexOffers(itemsWithSameParentId);
			basketTotal = basketTotal.add(new BigDecimal(discountedValue));
		}

		basketTotal = basketTotal.setScale(2, RoundingMode.HALF_UP);
		return basketTotal.doubleValue();
	}

	/**
	 * Used for Simple discount calculation.
	 * Finds out the discount model associated with this item and use that for calculation.
	 * @param basketItem : takes a BasketItem i.e. an Item and it's units
	 * @return : value of the BasketItem after discount applied.
	 * @throws DiscountNotFoundException
	 */
	private float getPriceOfSimpleOffers(BasketItem basketItem) throws DiscountNotFoundException {
		String discountCode = basketItem.getItem().getDiscount();
		Discount discount = DiscountMapping.getDiscountModel(discountCode);
		
		if (discount == null) 
			throw new DiscountNotFoundException("Discount details not found for discount Code: " + discountCode);

//		discount.setRelatedItems(Arrays.asList(basketItem));
		return discount.discountedValue(Arrays.asList(basketItem));
	}

	/**
	 * Used for Complex discount calculation.
	 * Finds out the discount model associated with this item and use that for calculation.
	 * @param basketItems : takes a list of BasketItems
	 * @return : value of the BasketItem after discount applied.
	 * @throws DiscountNotFoundException
	 */
	private float getPriceOfComplexOffers(List<BasketItem> basketItems) throws DiscountNotFoundException {
		String parentDiscountCode = basketItems.get(0).getItem().getParentDiscount();
		Discount discount = DiscountMapping.getDiscountModel(parentDiscountCode);
		
		if (discount == null) 
			throw new DiscountNotFoundException("Parent Discount details not found for discount Code: " + parentDiscountCode);

		return discount.discountedValue(basketItems);
	}
}
