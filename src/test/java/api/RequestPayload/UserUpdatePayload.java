package api.RequestPayload;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import api.Utilities.BaseClass;
import api.Utilities.Cache;
import io.restassured.response.Response;

public class UserUpdatePayload extends BaseClass
{
    private static JSONObject payload = new JSONObject();
    private static JSONArray updateroleID=new JSONArray();

    static JSONObject RoleMapPayload; 
    static JSONArray UserBatchRoleMap;
    static JSONObject rolemap;
    
    static JSONObject LoginPayload; 
    static JSONArray UserRoleId;;
   
    private static int programId= (int) Cache.getData("PROGRAMID_7"); // program Post
	private static int batchId= (int) Cache.getData("batchId_0");   //Batch Post
	private static String loginEmail = "zbIfXQ_sree.malineni.53@gmail.com"; //
	private static String passwd = "test123";
    
    
    //Update User role ID- Payload
    @SuppressWarnings("unchecked")
	public static JSONObject UserRoleListPayload(String roleId)
    {
    	updateroleID.clear();
    	updateroleID.add(roleId);
    	payload.put("userRoleList", updateroleID);   	
        return payload;
        
     }
    
    //Update User role ID- Invalid Payload
    @SuppressWarnings("unchecked")
	public static JSONObject UserRoleListInvalidPayload(String roleId)
    {
    	updateroleID.add(roleId);
    	updateroleID.add(roleId);
    	payload.put("userRoleList", updateroleID);    	
        return payload;
        
     }
    
    //Update User RoleStatus
    @SuppressWarnings("unchecked")
	public static JSONObject UserRoleStatusPayload(String roleId,String status)
    {

    	JSONObject RoleStatusPayload=new JSONObject();
    	RoleStatusPayload.put("roleId", roleId);
    	RoleStatusPayload.put("userRoleStatus", status);
    	System.out.println(RoleStatusPayload);
        return RoleStatusPayload;
        
     }

    //Update User Program/Batch Role
    @SuppressWarnings("unchecked")
	public static JSONObject UserProgramBatchRolePayload(String roleId,String status)
    {
    	RoleMapPayload = new JSONObject();
    	
    	RoleMapPayload.put("programId", programId);
    	RoleMapPayload.put("roleId", roleId);
    	
    	UserBatchRoleMap = new JSONArray();
    	rolemap = new JSONObject();
    	rolemap.put("batchId", batchId);
    	rolemap.put("userRoleProgramBatchStatus", status);
    	
    	UserBatchRoleMap.add(rolemap);
    	
    	RoleMapPayload.put("userRoleProgramBatches", UserBatchRoleMap);
    	
    	System.out.println(RoleMapPayload);
    	  	
        return RoleMapPayload;
        
     }   
    
    //Update User Login Status
    @SuppressWarnings("unchecked")
	public static JSONObject UserLoginStatusPayload(String roleId,String status)
    {
    	LoginPayload = new JSONObject();
    	
    	LoginPayload.put("loginStatus", status);
    	LoginPayload.put("password", passwd);
    	
    	UserRoleId = new JSONArray();
    	UserRoleId.add(roleId);
    	
    	LoginPayload.put("roleIds", UserRoleId);
    	LoginPayload.put("status", status);
    	LoginPayload.put("userLoginEmail", loginEmail);
    	
    	System.out.println(LoginPayload);
    	  	
        return LoginPayload;
        
     }
    
  //Update User Login Status - Scenario Outline
    @SuppressWarnings("unchecked")
	public static JSONObject UserLoginStatusPayloadScenario(String roleId,String email,String status,String password)
    {
    	LoginPayload = new JSONObject();
    	
    	LoginPayload.put("loginStatus", status);
    	LoginPayload.put("password", password);
    	
    	UserRoleId = new JSONArray();
    	UserRoleId.add(roleId);
    	
    	LoginPayload.put("roleIds", UserRoleId);
    	LoginPayload.put("status", status);
    	String randomString = BaseClass.generateRandomString(3);
    	LoginPayload.put("userLoginEmail", randomString+"_"+email);
    	
    	System.out.println(LoginPayload);
    	  	
        return LoginPayload;
        
     }
    
  //Update User Login Status - Invalid Payload
    @SuppressWarnings("unchecked")
	public static JSONObject UserLoginStatusInvalidPayload(String roleId,String status)
    {
    	LoginPayload = new JSONObject();
    	
    	LoginPayload.put("loginStatus", status);
    	LoginPayload.put("password", passwd);
    	
    	UserRoleId = new JSONArray();
    	UserRoleId.add(roleId);
    	
    	LoginPayload.put("roleIds", UserRoleId);
    	LoginPayload.put("status", status);
    	LoginPayload.put("userLoginEmail", loginEmail);
    	LoginPayload.put("RoleId", roleId);
    	
    	System.out.println(LoginPayload);
    	  	
        return LoginPayload;
        
     }

}