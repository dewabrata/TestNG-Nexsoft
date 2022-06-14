package com.nexsoft.testng.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderNexSoft {
	
	
	@DataProvider(name="data-provider")
	public Object[][] dataProviderMethod(){
		return new Object[][] {{"dewabrata"},{"nextSoft"}};
	}
	
}
