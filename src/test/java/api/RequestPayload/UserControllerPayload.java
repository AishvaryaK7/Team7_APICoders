package api.RequestPayload;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import api.Utilities.BaseClass;


public class UserControllerPayload {
	static JSONObject request;
	public static JSONObject userLogin;
	
	public static JSONObject PostRequestPayload(Object[] row) {
            request = new JSONObject();
	        request.put("userEduPg", row[0]);
			request.put("userEduUg", row[1]);
			request.put("userFirstName", row[2]);
			request.put("userLastName", row[3]);
			request.put("userLinkedinUrl", row[4]);
			request.put("userLocation", row[5]);
		
			 userLogin = new JSONObject();
	        userLogin.put("loginStatus", row[6]);
	        String randomString = BaseClass.generateRandomString(6);
	        if(row[7]==null) {
	        	userLogin.put("userLoginEmail", row[7]);
	        }
	        else {
	        	userLogin.put("userLoginEmail", randomString+"_"+row[7]);
	        }
	        
	        userLogin.put("password", row[15]);
	        //userLogin.put("userLoginEmail", "sree.malineni.72@gmail.com");
	       
	        request.put("userLogin", userLogin);

	        request.put("userMiddleName", row[8]);
	        Random random = new Random();
	        int randomNumber = 10000 + random.nextInt(90000);
	        //request.put("userPhoneNumber", row[9]);
	        if(row[9]==null) {
	        	request.put("userPhoneNumber", row[9]);
	        	
	        }
	        else {
	        request.put("userPhoneNumber", row[9]+ String.valueOf(randomNumber));
	        }
			
	        JSONArray userRoleMapsArray = new JSONArray();
			JSONObject userRoleMap = new JSONObject();
	        userRoleMap.put("roleId", row[10]);
	        userRoleMap.put("userRoleStatus", row[11]);
	        
	       
	        userRoleMapsArray.add(userRoleMap);

	        request.put("userRoleMaps", userRoleMapsArray);
	        
			request.put("userTimeZone", row[12]);
			request.put("userVisaStatus", row[13]);
			request.put("userComments", row[14]);
			
			
			return request; 
	 }
	
	public static JSONObject PutRequestPayload(Object[] row) {
		

		 request = new JSONObject();
	        request.put("userComments", row[21]);
			request.put("userEduPg", row[22]);
			request.put("userEduUg", row[23]);
			request.put("userFirstName", row[16]);
			request.put("userLastName", row[17]);
			request.put("userMiddleName", row[24]);
	        request.put("userLinkedinUrl", row[25]);
           request.put("userLocation", row[26]);
			request.put("userPhoneNumber", row[18]);
			request.put("userTimeZone", row[20]);
			request.put("userVisaStatus", row[19]);
			request.put("userLoginEmail", row[27]);
			
			
			return request; 
	 }
	
	public static JSONObject PutRequestPayload_fornegativecases() {
		

		 request = new JSONObject();
	        request.put("userComments", "abc");
			request.put("userEduPg", "abc");
			request.put("userEduUg", "abc");
			request.put("userFirstName", "abc");
			request.put("userLastName", "abc");
			request.put("userMiddleName", "abc");
	        request.put("userLinkedinUrl", "abc");
          request.put("userLocation", "abc");
			request.put("userPhoneNumber", 1234567834);
			request.put("userTimeZone", "EST");
			request.put("userVisaStatus", "H1B");
			request.put("userLoginEmail", "abc@gmail.com");
			
			
			return request; 
	 }
	
	public static JSONObject PostRequestPayload_fornegativecases() {
		

		JSONObject request = new JSONObject();
        request.put("userEduPg", "BA");
		request.put("userEduUg", "MTECH");
		request.put("userFirstName", "SRee");
		request.put("userLastName", "vidya");
		request.put("userLinkedinUrl", "www.linkedin.com");
		request.put("userLocation", "USA");
	
		JSONObject userLogin = new JSONObject();
        userLogin.put("loginStatus", "active");
        String randomString = BaseClass.generateRandomString(6);
        userLogin.put("userLoginEmail", randomString+"_"+ "test.sree@gmail.com");
        userLogin.put("password", "test123");
        //userLogin.put("userLoginEmail", "sree.malineni.72@gmail.com");
       
        request.put("userLogin", userLogin);

        request.put("userMiddleName", "NA");
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        //request.put("userPhoneNumber", row[9]);
        request.put("userPhoneNumber", "89697"+randomNumber);
		
        JSONArray userRoleMapsArray = new JSONArray();
		JSONObject userRoleMap = new JSONObject();
        userRoleMap.put("roleId", "R01");
        userRoleMap.put("userRoleStatus", "active");
        
       
        userRoleMapsArray.add(userRoleMap);

        request.put("userRoleMaps", userRoleMapsArray);
        
		request.put("userTimeZone", "EST");
		request.put("userVisaStatus", "H1");
		request.put("userComments", "test");

			
			
			return request; 
	 }

}
