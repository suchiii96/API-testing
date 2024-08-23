package com.abc.bankingendpoints;




import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public  class TestBankingEndpoints {
	
	
	public static  void createaccount(String info) {
		
		
		Response response=RestAssured.given()
				
		.baseUri( NetBankingRoutes .POST_URL)
		.contentType("application/json")
		.body(info)
		
		.when().post()
		
		.then().extract().response();
		
		}
	
	
	public static void FundTransfer() {
		//Send a POST request to initiate a funds transfer between two accounts.
		// Assuming you have two account objects (sender and receiver)
		
        int senderAccountId = 104;
        int receiverAccountId = 105;
        double transferAmount = 20000;

        // Create JSON object for fund transfer request
        String requestBody = "{"
                + "\"senderAccountId\":" + senderAccountId + ","
                + "\"receiverAccountId\":" + receiverAccountId + ","
                + "\"amount\":" + transferAmount
                + "}";

        // Send a POST request to the fund transfer endpoint
        
         Response response7= RestAssured.given()
        
                .baseUri( NetBankingRoutes.BASE_URL )
                .contentType("application/json")
                .body(requestBody)
                .pathParam("id", 105)
                
                .when().post(NetBankingRoutes .TRANSFER_URL)
                
                .then().extract().response();
		
        //Validate that the response code is 200 or 201.
        
        int expectedStatusCode= 201;
        int actualStatusCode= response7.getStatusCode();
        
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "status code is not valid");
        
        
        //Check that the transfer is reflected in the account balances appropriately
        
        int expectedaccountBalance=30000;
        
        Response response11=RestAssured.given()
				
    			.baseUri( NetBankingRoutes.BASE_URL )
    			.pathParam("id",  105)
    				
    			.when().get(NetBankingRoutes.GET_URL)
    				
    			.then().extract().response();
        
        Response updatedActualAmount=RestAssured.get("account_balance");
    		
        Assert.assertEquals(expectedaccountBalance, updatedActualAmount, "amount not reflected");
        
            	//System.out.println(response11.body().asString());
        }
	
	public static void getAllAccount() {
		
            Response myResponse=RestAssured.given()
				
			.baseUri( NetBankingRoutes.GET_ALL_URL )
				
			.when().get()
				
			.then().extract().response();
		
	        System.out.println(myResponse.body().asString());
	        
	        //Validate that the response code is 200
	         int expectedStatusCode= 200;
	         int actualStatusCode= myResponse.getStatusCode();
	         
	         Assert.assertEquals(actualStatusCode, expectedStatusCode, "status code is not valid");
	         
	         //validate that the response body contains the expected number of bank accounts
	         
	         int actualBankAccounts =myResponse.jsonPath().getList("accounts").size();
	         //System.out.println("TotalBankAccounts:"+ actualBankAccounts);
	         int expectedBankAccounts=8;
	          
	         Assert.assertEquals(actualBankAccounts, expectedBankAccounts,"Totalnumber of bank accounts is different");
	         
		}
	
	public static  void getSpecificAccount() {
		
		
	         Response response1=RestAssured.given()
				
			.baseUri( NetBankingRoutes.BASE_URL )
			.pathParam("id",  103)
				
			.when().get(NetBankingRoutes.GET_URL)
				
			.then().extract().response();
		
        	System.out.println(response1.body().asString());
        	
        	//Validate that the response code is 200
	         int expectedStatusCode= 200;
	         int actualStatusCode= response1.getStatusCode();
	         
	         Assert.assertEquals(actualStatusCode, expectedStatusCode, "status code is not valid");
	         
	         
	         //validate that the response body contains the correct details of the bank account
        	
		    /* Map<String,?> account =response1.jsonPath().get("accounts[2]");
		     Object myAccount =account.get("account_holder_name");
		     Object myAccountnumber=account.get("accountNumber");
		     Object myAccountbalance=account.get("account_balance");
		     Object myphoneNum =account.get("phone_number");
		     Object myAdress=account.get("address");
		    
		    System.out.println(myAccount + " " + myAccountnumber + " " + myAccountbalance + " " + myphoneNum +" " +myAdress);*/
		    
		  
	           HashMap map= new HashMap();
	           map.put("account_holder_name", "tiana John");
	           map.put("accountNumber", 672117826);
	           map.put("account_balance", 300000);
	           map.put("phone_number", 803213367);
	           map.put("address", "canada");
	           
	           Response newResponse=RestAssured.given()
	   				
	       			.baseUri( NetBankingRoutes.BASE_URL ).body(map)
	       			.pathParam("id",  103)
	       				
	       			.when().get(NetBankingRoutes.GET_URL)
	       				
	       			.then().extract().response();
	           
	            String expecteddetails  =newResponse.toString();
	           
	           
		     Map<?,?> Actualdetails =response1.jsonPath().get("accounts[2]");
		     System.out.println(expecteddetails);
		     System.out.println(Actualdetails);
		     
		   
		     Assert.assertEquals(Actualdetails, expecteddetails," details are not matching");
		     
		    
		   	}
	
	public static void UpdateAccount(String info1) {
		
        	Response response=RestAssured.given()
		
			.baseUri( NetBankingRoutes.BASE_URL )
			.contentType("application/json")
			
			.pathParam("id", 102)
			
			.body(info1.toString())
		
			.when().put(NetBankingRoutes.PUT_URL)
		
			.then().extract().response();
        	
             System.out.println(response);
             
             
           //Validate that the response code is 200
	         int expectedStatusCode= 200;
	         int actualStatusCode= response.getStatusCode();
	         
	         Assert.assertEquals(actualStatusCode, expectedStatusCode, "status code is not valid");
	         
	         //validate that the updated account details match the new information.
	         
	         //using get request retrive the upated data.
	         Response response2=RestAssured.given()
	 				
	     			.baseUri( NetBankingRoutes.BASE_URL )
	     			.pathParam("id",  102)
	     				
	     			.when().get(NetBankingRoutes.GET_URL)
	     				
	     			.then().extract().response();
	     		
	             	System.out.println(response2.body().asString());
	             	
	             	int expectedAccountNumber=671127816;
	             	int actualAccountNumber=response2.jsonPath().getInt("accounts[1].account_number");
	             	System.out.println("number:"+ actualAccountNumber);
	             	
	             	//Assert.assertEquals(actualAccountNumber, expectedAccountNumber,"account information not matching");
	             	
	             	
	      	}
	
	public static void deleteAccount() {
		
            Response response8=RestAssured.given()
				
			.baseUri( NetBankingRoutes.BASE_URL )
			.pathParam("id", 106)
				
			.when().delete(NetBankingRoutes.DELETE_URL)
				
			.then().extract().response();
		
            System.out.println(response8.getStatusLine());
            
            
            //Validate that the response code is 204 (no content).
            
            int expectedStatusCode= 204;
	        int actualStatusCode= response8.getStatusCode();
	         
	         Assert.assertEquals(actualStatusCode, expectedStatusCode, "status code is not valid");
	         
	         
	         //Confirm that the closed account is no longer present in the list of all accounts
	         
	         Response response9=RestAssured.given()
	 				
	     			.baseUri( NetBankingRoutes.BASE_URL )
	     			.pathParam("id",  106)
	     				
	     			.when().get(NetBankingRoutes.GET_URL)
	     				
	     			.then().extract().response();
	     		
	             	System.out.println(response9.body().asString());
	         }
	
	
}
