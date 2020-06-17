package com.pshaikh.price_calculation_service.calculation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pshaikh.price_calculation_service.web.Item;

public class PriceCalculatorTest {
	private PriceCalculator underTest = new PriceCalculator();
	private Item item;
	
	
	private final int ITEM_ID= 1;
	private final String ITEM_DESCRIPTION= "";
	private final float BASE_PRICE = 1.189f;
	private final int ZERO_UNITS = 0;
	private float percentageDiscount;
	
	private float endprice;
	private final float NO_DISCOUNT_PRICE = 1.19f;
	private final int AMOUNT_DECIMAL_DIGITS_AFTER_ROUNDING = 2;
	
	@Test
	public void testCalculationWithZeroUnitsAndWithoutDiscount() {
		given().zeroUnits().noDiscountProvided();
		
		when().itemIsCreated().priceIsCalculated();
		
		than().priceEqualsNoDiscountPrice().priceHasOnlyTwoDecimalDigits();
	}

	private PriceCalculatorTest given() {
		return this;
	}
	
	private PriceCalculatorTest zeroUnits() {
		return this;
	}
	
	private PriceCalculatorTest noDiscountProvided() {
		// when no discount provided in request object, it will be always 0
		percentageDiscount = 0f;
		return this;
	}
	
	private PriceCalculatorTest when() {
		return this;
	}
	
	private PriceCalculatorTest itemIsCreated() {
		item = new Item(BASE_PRICE, ITEM_DESCRIPTION, ZERO_UNITS, percentageDiscount, ITEM_ID);
		return this;
	}

	
	private PriceCalculatorTest priceIsCalculated() {
		endprice = underTest.calculate(item).getPrice();
		return this;
	}
	
	private PriceCalculatorTest than() {
		return this;
	}
	
	private PriceCalculatorTest priceEqualsNoDiscountPrice() {
		assertTrue(endprice == NO_DISCOUNT_PRICE);
		return this;
	}
	
	private PriceCalculatorTest priceHasOnlyTwoDecimalDigits() {
		String decimalDigits = String.valueOf(endprice).split("\\.")[1];
		assertTrue(decimalDigits.length() == AMOUNT_DECIMAL_DIGITS_AFTER_ROUNDING);
		return this;
	}
	
}