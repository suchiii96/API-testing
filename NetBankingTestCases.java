package com.abc.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abc.bankingendpoints.TestBankingEndpoints;
import com.abc.dataProvider.BankDataProvider;
import com.abc.payload.Banking;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class NetBankingTestCases {
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	
	@BeforeTest
	public void generateReport() {
		
		ExtentReports extentReports= new ExtentReports();
		ExtentSparkReporter extentsparkreporter= new ExtentSparkReporter("./reports/APIreport.html");
			
		extentReports.attachReporter(extentsparkreporter);
	}
	

	@Test(dataProviderClass= BankDataProvider.class,dataProvider="provideBankData")
	public void postTestCases(String name,
							  int accNumber,
							  int balance,
							  String phoneNum,
							  String address
							  ) throws JsonProcessingException
	{
		
		ExtentTest extentTest=extentReports.createTest("Bank account Creation Test");
		
		
		Faker faker= new Faker();
		
		
	    Banking bank= new Banking();
	    
	    bank.setAccount_holder_name(name);
	    bank.setAccount_number(accNumber);
	    bank.setAccount_balance(balance);
	    bank.setPhone_number(phoneNum);
	    bank.setAddress(address);
		
		/*bank.setAccount_holder_name(faker.artist().name());
		bank.setAccount_number(faker.number().numberBetween(251459000,352459000));
		bank.setAccount_balance(faker.number().numberBetween(10000, 500000));
		bank.setPhone_number(faker.phoneNumber().cellPhone());
		bank.setAddress(faker.address().fullAddress());*/
		
	    extentTest.log(Status.INFO,"The bank object storing bank data is converted to json object");
	    
		ObjectMapper mapper= new ObjectMapper();
		String bankjson=mapper.writeValueAsString(bank);
		
		extentTest.log(Status.INFO,"The bank object storing bank data is  sucessfully converted to corresponding json object");
		
		 Response output =TestBankingEndpoints.createaccount(bankjson);
		 
		 extentTest.log(Status.INFO,"The status code is:" +output.getStatusCode());
		 
		 extentTest.log(Status.INFO,"The response body is:" +output.getBody().asString());
		 
		 int expectedStatusCode=201;
		 Assert.assertEquals(output.getStatusCode(), expectedStatusCode);
		 
		 extentTest.log(Status.PASS,"The status code is correct");
		 
		 
	}
	@Test(enabled=false)
	public void fundTransferTestCases() {
		
		extentTest.log(Status.INFO,"Executing fundTransferTestCases method");
		
		TestBankingEndpoints.FundTransfer();
		
		extentTest.log(Status.INFO,"fundTransferTestCases method execution completed");
		
	}
	
	
	@Test(enabled=false)
	public  void getAllAccountTestCases() {
		
		extentTest.log(Status.INFO,"Executing getAllAccountTestCases method");
		
		TestBankingEndpoints.getAllAccount();
		
		extentTest.log(Status.INFO,"getAllAccountTestCases method execution completed");
	}
	
	 @Test
	  public void getParticularAccountTestCases() {
		
		extentTest.log(Status.INFO,"Executing getParticularAccountTestCases method");
		
		TestBankingEndpoints.getSpecificAccount();
		
		extentTest.log(Status.INFO,"getParticularAccountTestCases method execution completed");		
	}
	 
	 @Test(enabled=false)
	 public void putAccountTestCases() throws JsonProcessingException {
		 
		 Banking bank1= new Banking();
		 
		 	bank1.setAccount_holder_name("Mariam john");
			bank1.setAccount_number(671127816);
			bank1.setAccount_balance(40000);
			bank1.setPhone_number("808221386");
			bank1.setAddress("georgia");
			
			
			extentTest.log(Status.INFO,"The bank object storing bank data is converted to json object");
			
			ObjectMapper mapper= new ObjectMapper();
			String bankjson1=mapper.writeValueAsString(bank1);
			
			extentTest.log(Status.INFO,"The bank object storing bank data is  sucessfully converted to corresponding json object");
			
			
			 Response output1=TestBankingEndpoints.UpdateAccount(bankjson1);
			 
			 extentTest.log(Status.INFO,"The status code is:" +output1.getStatusCode());
			 
			 extentTest.log(Status.INFO,"The response body is:" +output1.getBody().asString());
			 
			 int expectedStatusCode=201;
			 Assert.assertEquals(output1.getStatusCode(), expectedStatusCode);
			 
			 extentTest.log(Status.PASS,"The status code is correct");
		 
		 
	 }
	 
	 @Test(enabled=false)
	 public void deleteTestCases() {
		 
		 extentTest.log(Status.INFO,"Executing deleteTestCases method");
			
		 TestBankingEndpoints.deleteAccount();
		  
		 extentTest.log(Status.INFO,"deleteTestCases method execution completed");
	
	}
	 
	 
}
