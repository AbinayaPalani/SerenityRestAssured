package com.steps;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.utils.DataReader;

import io.restassured.RestAssured;


public class commonObject {

	public Map<String, Object> createUserObject 						= 					new LinkedHashMap<String, Object>(); 
	public Map<String, Object> billingContactCreateUser 				= 					new LinkedHashMap<String, Object>();
	public Map<String, Object> createUserPaymentObject					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> accntInfoCreateUserPayment				=					new LinkedHashMap<String, Object>();
	public Map<String, Object> billingContactCreateUserPayment			=					new LinkedHashMap<String, Object>();
	public Map<String, Object> communicationAddressCreateUserPayment	=					new LinkedHashMap<String, Object>();
	public Map<String, Object> billingAddressCreateUserPayment			=					new LinkedHashMap<String, Object>();
	public Map<String, Object> paymentDetailCreateUserPayment			=					new LinkedHashMap<String, Object>();
	public Map<String, Object> createPlans								=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updatePlans								=					new LinkedHashMap<String, Object>();
	public Map<String, Object> credits									=					new LinkedHashMap<String, Object>();
	public Map<String, Object> debits									=					new LinkedHashMap<String, Object>();
	public Map<String, Object> ChangeAssetPrice							=					new LinkedHashMap<String, Object>();
	public Map<String, Object> SynAsset									=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUser								=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserPrimaryDetails					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserCustomDetails					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserAccountInfo					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserBillingContacts				=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserCommAddress					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserBillingAddress					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateMetaFieldObject					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateMetaFieldBContact					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateMetaCommunicationAddress			=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updateMetaCCAddress						=					new LinkedHashMap<String, Object>();
	public Map<String, Object> processPayment							=					new LinkedHashMap<String, Object>();
	public Map<String, Object> paymentTypeObject						=					new LinkedHashMap<String, Object>();
	public Map<String, Object> paymentAddressDetails					=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updatePIByAmt							=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updatePIByAmtPaymentDetails				=					new LinkedHashMap<String, Object>();
	public Map<String, Object> updatePI									=					new LinkedHashMap<String, Object>();
	public Map<String, Object> createManualPaymentObject				=					new LinkedHashMap<String, Object>();
	public Map<String, Object> manualPaymentDetails						=					new LinkedHashMap<String, Object>();
	public Map<String, Object> refundObject								=					new LinkedHashMap<String, Object>();
	public Map<String, Object> delayBillingObject						=					new LinkedHashMap<String, Object>();
	public Map<String, Object> unblockObject							=					new LinkedHashMap<String, Object>();
	public Map<String, Object> taskUnblockObject						=					new LinkedHashMap<String, Object>();
	public Map<String, Object> blockObject								=					new LinkedHashMap<String, Object>();
		
	
	DataReader data														=					new DataReader();
	
	char[] chars														= 					"abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	char[] accountNum 													=					"0123456789".toCharArray();
    StringBuilder sb1													= 					new StringBuilder();
    StringBuilder sb2												 	=					new StringBuilder();
    Random random 														=					new Random();
    UUID uuid;
    Date todayDate 														=					new Date();
    
 
	//Method for createUser
	public void createUser() throws Throwable{
		
		
		
		data.createUserSheet();
		
		System.out.println(data.brandIds.size());
		
		for(int index = 0; index < data.brandIds.size(); ++index){
			
			   for (int j = 0; j < 6; j++) {
			        char c = chars[random.nextInt(chars.length)];
			        sb1.append(c);
			    }
			    for (int j = 0; j < 10; j++) {
			        char c = accountNum[random.nextInt(accountNum.length)];
			        sb2.append(c);
			    }
			    uuid = UUID.randomUUID();
				
			
			
			System.out.println(data.Amounts.get(index) != null);
			
			
		
			//if(data.Amounts.get(index) == null){
	
				createUserObject.put("firstName", "Testing");
				createUserObject.put("lastName", "Jbilling");
				createUserObject.put("userEmail", sb1.toString()+"_test@mail.com");
				createUserObject.put("brandId", data.brandIds.get(index));
				createUserObject.put("uniquePin", uuid.toString());
				createUserObject.put("serviceAgreement", "90 Days");
				if(RestAssured.baseURI.equalsIgnoreCase("http://staging.jbilling.a-cti.com:8081")){
					createUserObject.put("linkToCRM", "https://dist-sourcetest.appspot.com/crm#account/"+uuid.toString());
				}
				if(RestAssured.baseURI.equalsIgnoreCase("http://access.jbilling.a-cti.com:8081")){
					createUserObject.put("linkToCRM", "https://my.distributedsource.com/crm#account/"+uuid.toString());
				}
				if(RestAssured.baseURI.equalsIgnoreCase("http://access.jbilling.a-cti.com")){
					createUserObject.put("linkToCRM", "https://my.distributedsource.com/crm#account/"+uuid.toString());
				}
				createUserObject.put("accountNumber", sb2.toString());
				createUserObject.put("setUpFee", 0);
				createUserObject.put("address", "Addr1");
				createUserObject.put("address2", "Addr2");
				createUserObject.put("city", "OR");
				createUserObject.put("state", "Portland");
				createUserObject.put("country", "US");
				createUserObject.put("zip", "98343");
				createUserObject.put("organizationName", "TEST"+sb1.toString());
				createUserObject.put("phoneNumber", "0123456789");
				createUserObject.put("paymentType", data.PaymentTypeCreateUser.get(index));
					if( data.PaymentTypeCreateUser.get(index)!=null){
						if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("cc") ){
							createUserObject.put("nameOnCard", "Abinaya");
							createUserObject.put("cardNumber", "4111111111111111");
							createUserObject.put("expDate", "09/2020");
							createUserObject.put("cardType", "Visa");
							createUserObject.put("transactionID", "gvhvh3456");
						}
						if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("ach")){
							createUserObject.put("routingNumber", "091000019");
							createUserObject.put("bankAccountNumber", "98387847312");
							createUserObject.put("customerName", "Abinaya");
						}
					}
				
				createUserObject.put("agentPin", "8943");
				createUserObject.put("agentName", "Test_Ab");
				billingContactCreateUser.put("First Name", "Abi");
				billingContactCreateUser.put("Last Name", "naya");
				billingContactCreateUser.put("Phone Number", "87673467376");
				billingContactCreateUser.put("Email", "testing@billing.com");
				createUserObject.put("billingContact1", billingContactCreateUser);
				
	
				}
			//}
		
		}
	public void createUserWithAmount(){
		
	
		for(int index = 0; index < data.brandIds.size(); ++index){
			
			   for (int j = 0; j < 6; j++) {
			        char c = chars[random.nextInt(chars.length)];
			        sb1.append(c);
			    }
			    for (int j = 0; j < 10; j++) {
			        char c = accountNum[random.nextInt(accountNum.length)];
			        sb2.append(c);
			    }
			   uuid = UUID.randomUUID();
				

			createUserPaymentObject.put("userEmail", sb1.toString()+"_test@mail.com");
			createUserPaymentObject.put("brandId", data.brandIds.get(index));
			createUserPaymentObject.put("accountPin", uuid.toString());
			createUserPaymentObject.put("accountNumber", sb2.toString());
			if(RestAssured.baseURI.equalsIgnoreCase("http://staging.jbilling.a-cti.com:8081")){
				createUserPaymentObject.put("linkToCRM", "https://dist-sourcetest.appspot.com/crm#account/"+uuid.toString());
			}
			if(RestAssured.baseURI.equalsIgnoreCase("http://access.jbilling.a-cti.com:8081")){
				createUserPaymentObject.put("linkToCRM", "https://my.distributedsource.com/crm#account/"+uuid.toString());
			}
			if(RestAssured.baseURI.equalsIgnoreCase("http://access.jbilling.a-cti.com")){
				createUserPaymentObject.put("linkToCRM", "https://my.distributedsource.com/crm#account/"+uuid.toString());
			}
			createUserPaymentObject.put("paymentType", data.PaymentTypeCreateUser.get(index));
			createUserPaymentObject.put("amount", data.Amounts.get(index));
			accntInfoCreateUserPayment.put("salesPerson", "Testing");
			accntInfoCreateUserPayment.put("serviceAgreement", "90");
			accntInfoCreateUserPayment.put("status", "Active");
			createUserPaymentObject.put("accountInfo",accntInfoCreateUserPayment);
			billingContactCreateUserPayment.put("firstName", "testingFName");
			billingContactCreateUserPayment.put("lastName", "testingLName");
			billingContactCreateUserPayment.put("phoneNumber", "9487587434");
			billingContactCreateUserPayment.put("email", "test@test.com");
			createUserPaymentObject.put("billingContact1",billingContactCreateUserPayment );
			communicationAddressCreateUserPayment.put("organizationName", "TEST"+sb1.toString());
			communicationAddressCreateUserPayment.put("userName", "Abin");
			communicationAddressCreateUserPayment.put("address1", "address test1");
			communicationAddressCreateUserPayment.put("address2", "address test2");
			communicationAddressCreateUserPayment.put("city", "Portland");
			communicationAddressCreateUserPayment.put("state", "Oregon");
			communicationAddressCreateUserPayment.put("country", "US");
			communicationAddressCreateUserPayment.put("zip", "939848");
			createUserPaymentObject.put("communicationAddress", communicationAddressCreateUserPayment);
			billingAddressCreateUserPayment.put("firstName", "Testing");
			billingAddressCreateUserPayment.put("lastName", "Ab");
			billingAddressCreateUserPayment.put("address1", "Address1 billing");
			billingAddressCreateUserPayment.put("address2", "Address2 billing");
			billingAddressCreateUserPayment.put("city", "Portland");
			billingAddressCreateUserPayment.put("state", "Oregon");
			billingAddressCreateUserPayment.put("country", "US");
			billingAddressCreateUserPayment.put("zip", "939848");
			billingAddressCreateUserPayment.put("billingEmail", "testing@billing.com");
			createUserPaymentObject.put("billingAddress", billingAddressCreateUserPayment);
			
			if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("cc")){
				paymentDetailCreateUserPayment.put("nameOnCard", "Abinaya");
				paymentDetailCreateUserPayment.put("cardNumber", data.cardNum.get(index));
				paymentDetailCreateUserPayment.put("expDate", data.expDate.get(index));
			}
			if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("ach")){
				paymentDetailCreateUserPayment.put("routingNumber", "091000019");
				paymentDetailCreateUserPayment.put("bankAccountNumber", "0910000198478");
				paymentDetailCreateUserPayment.put("customerName", "Abinaya");
				paymentDetailCreateUserPayment.put("bankAccountType", "Checking");
			}
			if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("cc")){
				paymentDetailCreateUserPayment.put("vaultId", data.vaultIds.get(index));
				paymentDetailCreateUserPayment.put("customerKey", data.btCustIds.get(index));
			}
			createUserPaymentObject.put("paymentDetails", paymentDetailCreateUserPayment);
		}
		
	}
	
	public void creditAdjustment(){
		
		credits.put("uniquePin", "");
		credits.put("brandId", "");
		credits.put("creditAmount", "");
		credits.put("approvedBy", "");
		credits.put("reason", "");
		credits.put("description", "");
		credits.put("processDate", "");
		credits.put("taskId", "");
		
		
	}
	
	public void debitAdjustment(){
		
		debits.put("uniquePin", "");
		debits.put("brandId", "");
		debits.put("debitAmount", "");
		debits.put("approvedBy", "");
		debits.put("reason", "");
		debits.put("description", "");
		debits.put("processDate", "");
		debits.put("taskId", "");
	}
		
	
		
		
	
	public void createPlans(){
		
		
		createPlans.put("accountPin", "");
		createPlans.put("brandId","" );
		createPlans.put("voicePlanId","" );
		createPlans.put("ivrPlanId","" );
		createPlans.put("addOnIds","" );
		createPlans.put("assetId","" );
		createPlans.put("activeSinceDate","" );
		createPlans.put("dateCreated", todayDate.getTime());
	}
	
	public void swapPlans(){
		
		updatePlans.put("uniquePin", "");
		updatePlans.put("userId", "");
		updatePlans.put("brandId", "");
		updatePlans.put("voicePlanId", "");
		updatePlans.put("ivrPlanId", "");
		updatePlans.put("addedAddonsId", "");
		updatePlans.put("removedAddonsId", "");
		
	}
	
	public void dynamicAssetPrice(){
		
		ChangeAssetPrice.put("accountPin", "");
		ChangeAssetPrice.put("productId", "");
		ChangeAssetPrice.put("assetValue", "");
		ChangeAssetPrice.put("price", "");
		ChangeAssetPrice.put("brandId", "");
	}
	
	public void assoicateAsset(){
		
		SynAsset.put("assetId", "");
		SynAsset.put("uniquePin", "");
		SynAsset.put("brandId", "");
		SynAsset.put("isMapAsset", "");
		
		
	}
	
	public void updateUserInfo(){
		
		updateUser.put("accountPin", "");
		updateUser.put("brandId", "");
		updateUserPrimaryDetails.put("accountPin", "");
		updateUserPrimaryDetails.put("linkToCRM", "");
		updateUserPrimaryDetails.put("accountNumber", "");
		updateUserPrimaryDetails.put("allowAchDebit", "");
		updateUserPrimaryDetails.put("autoPlanUpgrade", "");
		updateUser.put("primaryDetails", updateUserPrimaryDetails);
		updateUserCustomDetails.put("creditLimit", "");
		updateUserCustomDetails.put("autoRechargeAmount", "");
		updateUserCustomDetails.put("autoRechargeThreshold", "");
		updateUserCustomDetails.put("autoRechargeMonthlyLimit", "");
		updateUserCustomDetails.put("nextInvoiceDate", "");
		updateUser.put("customDetails", updateUserCustomDetails);
		updateUserAccountInfo.put("salesPerson", "");
		updateUserAccountInfo.put("serviceAgreement", "");
		updateUserAccountInfo.put("status", "");
		updateUserAccountInfo.put("industry", "");
		updateUserAccountInfo.put("accountTypes", "");
		updateUser.put("accountInfo", updateUserAccountInfo);
		updateUserBillingContacts.put("firstName", "");
		updateUserBillingContacts.put("lastName", "");
		updateUserBillingContacts.put("phoneNumber", "");
		updateUserBillingContacts.put("email", "");
		updateUser.put("billingContact1", updateUserBillingContacts);
		updateUserCommAddress.put("organizationName", "");
		updateUserCommAddress.put("userName", "");
		updateUserCommAddress.put("address1", "");
		updateUserCommAddress.put("address2", "");
		updateUserCommAddress.put("city", "");
		updateUserCommAddress.put("state", "");
		updateUserCommAddress.put("country", "");
		updateUserCommAddress.put("zip", "");
		updateUser.put("communicationAddress", updateUserCommAddress);
		updateUserBillingAddress.put("firstName", "");
		updateUserBillingAddress.put("lastName", "");
		updateUserBillingAddress.put("address1", "");
		updateUserBillingAddress.put("address2", "");
		updateUserBillingAddress.put("city", "");
		updateUserBillingAddress.put("state", "");
		updateUserBillingAddress.put("country", "");
		updateUserBillingAddress.put("zip", "");
		updateUserBillingAddress.put("billingEmail", "");
		updateUser.put("creditCardBillingAddress", updateUserBillingAddress);
		
	}

	
	public void updateMetaFieldData(){
		

		updateMetaFieldObject.put("uniquePin", "");
		updateMetaFieldObject.put("userId", "");
		updateMetaFieldObject.put("brandId", "");
		
		//if(userData.updtMetaFieldType.get(index).equalsIgnoreCase("billingContact1") ||
			//	userData.updtMetaFieldType.get(index).equalsIgnoreCase("billingContact2") ||
			//	userData.updtMetaFieldType.get(index).equalsIgnoreCase("billingContact3")) {
		
			updateMetaFieldBContact.put("First Name", "");
			updateMetaFieldBContact.put("Last Name", "");
			updateMetaFieldBContact.put("Phone Number", "");
			updateMetaFieldBContact.put("Email", "");
			
			//updateMetaFieldObject.put(userData.updtMetaFieldType.get(index), updateMetaFieldBContact);
		//}
			
		
		//if(userData.updtMetaFieldType.get(index).equalsIgnoreCase("communicationAddress")) {
			
			updateMetaCommunicationAddress.put("Statement Name 1", "");
			updateMetaCommunicationAddress.put("Statement Name 2","");
			updateMetaCommunicationAddress.put("Statement Address 1", "");
			updateMetaCommunicationAddress.put("Statement Address 2", "");
			updateMetaCommunicationAddress.put("Statement City", "");
			updateMetaCommunicationAddress.put("Statement State", "");
			updateMetaCommunicationAddress.put("Statement Country", "");
			updateMetaCommunicationAddress.put("Statement ZIP", "");
			
			//updateMetaFieldObject.put(userData.updtMetaFieldType.get(index), updateMetaCommunicationAddress);
		//}
		
		
		//if(userData.updtMetaFieldType.get(index).equalsIgnoreCase("creditCardBillingAddress")) {
			
		
			updateMetaCCAddress.put("First Name","");
			updateMetaCCAddress.put("Last Name", "");
			updateMetaCCAddress.put("Address 1", "");
			updateMetaCCAddress.put("Address 2", "");
			updateMetaCCAddress.put("City", "");
			updateMetaCCAddress.put("State", "");
			updateMetaCCAddress.put("Country", "");
			updateMetaCCAddress.put("ZIP", "");
			updateMetaCCAddress.put("Billing Email", "");
			
			
			//updateMetaFieldObject.put(userData.updtMetaFieldType.get(index), updateMetaCCAddress);
		
	}
	
	public void oneTimePayment(){
		
		processPayment.put("accountPin", "");
		processPayment.put("brandId", "");
		processPayment.put("instrumentId", "");
		processPayment.put("paymentType", "");
		processPayment.put("paymentAmount", "");
		paymentTypeObject.put("nameOnCard", "");
		paymentTypeObject.put("cardNumber", "");
		paymentTypeObject.put("expDate", "");
		paymentTypeObject.put("routingNumber", "");
		paymentTypeObject.put("bankAccountNumber", "");
		paymentTypeObject.put("customerName", "");
		paymentTypeObject.put("bankAccountType", "");
		processPayment.put("paymentDetails", paymentTypeObject);
		paymentAddressDetails.put("firstName", "");
		paymentAddressDetails.put("lastName", "");
		paymentAddressDetails.put("address", "");
		paymentAddressDetails.put("address2", "");
		paymentAddressDetails.put("country", "");
		paymentAddressDetails.put("city", "");
		paymentAddressDetails.put("state", "");
		paymentAddressDetails.put("zip", "");
		processPayment.put("addressDetails", paymentAddressDetails);
		
	}
	
	public void updatePaymentInstruments(){
		
		updatePI.put("accountPin","" );
		updatePI.put("brandId","" );
		updatePI.put("paymentType","" );
		updatePI.put("deletePIID","" );
		updatePI.put("amount","" );
		updatePIByAmtPaymentDetails.put("nameOnCard","" );
		updatePIByAmtPaymentDetails.put("cardNumber","" );
		updatePIByAmtPaymentDetails.put("expDate","" );
		updatePIByAmtPaymentDetails.put("firstName","" );
		updatePIByAmtPaymentDetails.put("lastName","" );
		updatePIByAmtPaymentDetails.put("address1","" );
		updatePIByAmtPaymentDetails.put("address2","" );
		updatePIByAmtPaymentDetails.put("country","" );
		updatePIByAmtPaymentDetails.put("city","" );
		updatePIByAmtPaymentDetails.put("state","" );
		updatePIByAmtPaymentDetails.put("zip","" );
		updatePI.put("paymentDetails",updatePIByAmtPaymentDetails);

		
	}
	
	public void manualPayment(){
		
		createManualPaymentObject.put("userId", "");
		createManualPaymentObject.put("uniquePin", "");
		createManualPaymentObject.put("brandId", "");
		createManualPaymentObject.put("paymentType", "");
		createManualPaymentObject.put("paymentAmount", "");
		createManualPaymentObject.put("paymentNotes", "");
		manualPaymentDetails.put("cardNumber", "");
		manualPaymentDetails.put("expDate", "");
		manualPaymentDetails.put("nameOnCard", "");
		manualPaymentDetails.put("transactionId", "");
		manualPaymentDetails.put("routingNumber", "");
		manualPaymentDetails.put("customerName", "");
		manualPaymentDetails.put("accountNumber", "");
		manualPaymentDetails.put("chequeNumber", "");
		manualPaymentDetails.put("token", "");
		manualPaymentDetails.put("payer_ID", "");
		manualPaymentDetails.put("BAID", "");
		createManualPaymentObject.put("paymentDetails", "manualPaymentDetails");
	}
	
	
	public void refundStack(){
		
		refundObject.put("paymentId", "");
		refundObject.put("brandId", "");
		refundObject.put("amount", "");
		refundObject.put("reason", "");
		
	}
	
	public void delayBillingProcess(){
		
		delayBillingObject.put("accountPin", "");
		delayBillingObject.put("brandId", "");
		delayBillingObject.put("goLiveDate", "");
		delayBillingObject.put("reason", "");
		delayBillingObject.put("taskId", "");
		
	}
	
	public void blockAccountProcess(){
		
		blockObject.put("accountPin", "");
		blockObject.put("brandId", "");
		blockObject.put("reason", "");
	}
	
	public void unBlockAccountProcess(){
		
		taskUnblockObject.put("linkedAccount", "");
		taskUnblockObject.put("productID", "");
		taskUnblockObject.put("reason", "");
		unblockObject.put("task", taskUnblockObject);
	}

}
