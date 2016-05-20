package com.rbc.basket.discount;

public class DiscountNotFoundException extends Exception {
	private static final long serialVersionUID = 149749348409L;

	public DiscountNotFoundException () {

	}

	public DiscountNotFoundException (String message) {
		super (message);
	}

	public DiscountNotFoundException (Throwable cause) {
		super (cause);
	}

	public DiscountNotFoundException (String message, Throwable cause) {
		super (message, cause);
	}
}
