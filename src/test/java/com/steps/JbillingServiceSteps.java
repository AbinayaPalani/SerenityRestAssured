package com.steps;

import static org.hamcrest.Matchers.is;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import com.google.gson.Gson;
import com.utils.DataReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class JbillingServiceSteps {
	

	private Response response_profileInfo,response_getUserInfoById,response_getUserInfoByAccount;
	DataReader data											=					    new DataReader();
	commonObject jsonData 									= 						new commonObject();
	Map<String, Object>	userData							=						new LinkedHashMap<String, Object>();
	String accountPin, JsoninfoProfileData;
	JSONObject getBrandId,resJson_createUser;

	
	@Step
	public void givenHaveToCreateAUserWithData() throws Throwable{
		 
	    data.createUserSheet();
	    jsonData.createUser();
		Gson gson 										=							new Gson();
		JsoninfoProfileData								=							gson.toJson(jsonData.createUserObject,LinkedHashMap.class); 
		System.out.println(JsoninfoProfileData);
	}
	 
	 @Step
	 public void whenCreateAUserWithoutPaymentDetails(){
		 
	 	getBrandId 										= 							new JSONObject(JsoninfoProfileData);	
	 	System.out.println(getBrandId.toString());
		response_profileInfo 							= 							SerenityRest.given().when().contentType(ContentType.JSON).accept(ContentType.JSON).content(JsoninfoProfileData).post("/createUser");
		
	 }
	 
	 @Step
	 public void whenCreateAUserWithCCPaymentInformationNotWithAmount(){
		 
		 
	 	getBrandId 										= 							new JSONObject(JsoninfoProfileData);	
		response_profileInfo 							= 							SerenityRest.given().when().contentType(ContentType.JSON).accept(ContentType.JSON).post("/createUser");
		
		 
	 }
	 
	 @Step
	 public void whenCreateAUserWithACHPaymentInformationNotWithAmount(){
		 
	 
	 	getBrandId 										= 							new JSONObject(JsoninfoProfileData);	
		response_profileInfo 							= 							SerenityRest.given().when().contentType(ContentType.JSON).accept(ContentType.JSON).post("/createUser");
			
	 }
	 
	 @Step
	 public void whenCreateAUserWithCCPaymentDetailWithAmount(){
		 
		getBrandId 										= 							new JSONObject(JsoninfoProfileData.toString());	
		response_profileInfo 							= 							SerenityRest.given().when().contentType(ContentType.JSON).accept(ContentType.JSON).post("/v1/createUserAndPayment");
		 	 
	 }
	 
	 @Step
	 public void whenCreateAUserWithACHPaymentDetailWithAmount(){
		 
		getBrandId 										= 							new JSONObject(JsoninfoProfileData.toString());	
		response_profileInfo 							= 							SerenityRest.given().when().contentType("application/json").accept(ContentType.JSON).post("/v1/createUserAndPayment");
			
		 
	 }
	  
	 @Step
	 public void thenAssertingTheStatusOfProfileEntry(){
		 
			
			resJson_createUser 							= 							new JSONObject(response_profileInfo.asString());	
			
			//System.out.println(getBrandId.get("brandId"));	
			response_profileInfo.then().body("status", is(resJson_createUser.get("status")));
			
			Assert.assertThat("Failed to create a user, check the problem", resJson_createUser.get("status") , is("success"));
			
			
	 }
	 
	 @Step
	 public void thenCheckWithRelevantServiceToGetTheUserDetails(){
		 
		 response_getUserInfoById 						=							RestAssured.given().contentType(ContentType.JSON)
																						.queryParam("userId", resJson_createUser.get("userId"))
																						.queryParam("brandId",getBrandId.get("brandId")).get("/getUserById");

		JSONObject resJson_getUserInfoById				=							new JSONObject(response_getUserInfoById.asString());
		
		Assert.assertEquals("Not getting the user information", resJson_getUserInfoById.get("success"), true);
		
		JSONArray primaryContactGetAccountPin_UserId	 =    						resJson_getUserInfoById.getJSONArray("primaryContact");
		
		for(int metaFieldIndex = 0; metaFieldIndex < primaryContactGetAccountPin_UserId.length(); ++metaFieldIndex){
		
		JSONObject primaryContacts 						 = 						primaryContactGetAccountPin_UserId.getJSONObject(metaFieldIndex);
			
			if(primaryContacts.get("fieldName").equals("Account PIN")){
			
				accountPin 											=						String.valueOf(primaryContacts.get("fieldName"));
			}
		} 
		
		
		response_getUserInfoByAccount					 =							RestAssured.given().contentType(ContentType.JSON)
																						.queryParam("brandId", getBrandId.get("brandId"))
																						.queryParam("accountPin", accountPin.toString()).get("/getUserByAccountPin");
		
	}
	 
	 @Step
	 public void andVaildateTheUserInformationFromTheGetService(){
		 
		 response_getUserInfoByAccount					 =							RestAssured.given().contentType(ContentType.JSON)
																						.pathParam("accountPin", accountPin.toString())
																						.pathParam("brandId", getBrandId.get("brandId"))
																						.get("/v1/getUserByAccountPin/accountPin/{accountPin}/brandId/{brandId}");
		 
		 JSONObject vaildateResponse					 =							new JSONObject(response_getUserInfoByAccount);
		 
	 
	 }
	 
	 
	 @Step
	 public void givenCreateAPlansAndAddonsForACustomer(){
		 
		 
		 
	 }
 

}
