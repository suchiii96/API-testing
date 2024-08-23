package com.abc.dataProvider;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class BankDataProvider {
	
	@DataProvider
	public Object[][] provideBankData() {
		
		
		Faker faker1= new Faker();
		
		
		Object[][] data=
			{
					{
						faker1.artist().name(),
						faker1.number().numberBetween(251459000,352459000),	
						faker1.number().numberBetween(10000, 500000),
						faker1.phoneNumber().cellPhone(),
						faker1.address().fullAddress()
					},	
					
					{
						faker1.artist().name(),
						faker1.number().numberBetween(251459000,352459000),	
						faker1.number().numberBetween(10000, 500000),
						faker1.phoneNumber().cellPhone(),
						faker1.address().fullAddress()
						
					}
					
					
			};
		
		return data;
		
		
	}
	
	

}
