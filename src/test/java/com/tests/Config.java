package com.tests;


import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
@RunWith(SerenityRunner.class)   
public class Config {
	


	
	static
	{
		RestAssured.baseURI = "http://staging.jbilling.a-cti.com:8081";	
	}
	
	  @Steps
	   com.steps.JbillingServiceSteps jbillingSteps;

	  @Test
	   public void verifyTheFunctionalityOfCustomerProfileCreation() throws Throwable {
		  
		  jbillingSteps.givenHaveToCreateAUserWithData();
		
		  jbillingSteps.whenCreateAUserWithoutPaymentDetails();
		  
		  jbillingSteps.thenAssertingTheStatusOfProfileEntry();
	   
	   }

}
