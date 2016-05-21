package com.rbc.basket;

/**
 * This Item class holds two discount codes. The parentDiscount code is to group all 
 * related items of the same offer. The discount code is the more granular code. 
 * Where the discount is straight forward like buy any at 10% discount, it will not have
 * parent code but just the discount code.
 * 
 * Examples are given below.
 * Example 1: Small packets of apple, orange, lemon, banana are sold at £1.00. 
 * The offer is buy any of three packets and pay only for two packets. In this 
 * case the parentDiscount code is same for all the above packets but their discount 
 * codes can be same or different.
 * 
 * Example 2: The lunch deal is £3.00 for a wrap/sandwitch, a drink and a snack. 
 * There are varieties of these items. So the parentDiscount code will have the 
 * same code and the discount code has to be different. Different discount code 
 * helps to handle situations where we buy 2 wrap, 2 drink but 5 snacks. In this 
 * case, we need to pay for two meal deals i.e. £6.00 plus three extra snacks.
 *
 */
public class Item {
	private String itemId;
	private String Description;
	private float unitPrice;
	private String parentDiscount; //Used to handle complex discount models
	private String discount; //Used to handle both simple and complex discount models

	public Item(String itemId, String description, float unitPrice, 
			String parentDiscount, String discount) {
		this.itemId = itemId;
		Description = description;
		this.unitPrice = unitPrice;
		this.parentDiscount = parentDiscount;
		this.discount = discount;
	}

	public String getItemId() {
		return itemId;
	}

	public String getDescription() {
		return Description;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public String getParentDiscount() {
		return parentDiscount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		Item other = (Item) obj;
		return itemId.equals(other.itemId);
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", Description=" + Description + ", unitPrice=" + unitPrice
				+ ", parentDiscount=" + parentDiscount + ", discount=" + discount
				+ "]";
	}

}
