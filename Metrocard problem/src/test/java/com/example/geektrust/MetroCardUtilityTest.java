package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.geektrust.beans.Metrocard;
import com.example.geektrust.utilities.MetroCardUtility;

public class MetroCardUtilityTest {

	@Test
	void rechargeTest()
	{
		MetroCardUtility utils = new MetroCardUtility();
		Metrocard card = new Metrocard("MC1",50);
		int amount = 100;
	    assertEquals(amount/50, utils.recharge(card, 100));
	}
	
	
	
}
