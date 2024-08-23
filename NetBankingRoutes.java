package com.abc.bankingendpoints;

public class NetBankingRoutes {
	
	public static String BASE_URL="http://localhost:3000";
	
	public static String POST_URL=BASE_URL+"/accounts";
	public static String GET_ALL_URL=BASE_URL+"/accounts";
	public static String GET_URL=BASE_URL+"/accounts/{id}";
	public static String PUT_URL=BASE_URL+"/accounts/{id}";
	public static String DELETE_URL=BASE_URL+"/accounts/{id}";
	public static String TRANSFER_URL=BASE_URL+"/accounts/{id}";

}
