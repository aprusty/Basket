package com.rbc.basket;

/**
 * This class holds an item and the number of units.
 * This gives additional provision to input same items in one go.
 */
public class BasketItem {
	private Item item;
	private int units;

	public BasketItem(Item item, int units) {
		this.item = item;
		this.units = units;
	}

	public Item getItem() {
		return item;
	}

	public int getUnits() {
		return units;
	}

	public void addUnits(int units) {
		this.units = this.units + units;
	}
}
