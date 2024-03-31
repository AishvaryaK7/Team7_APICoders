package api.RequestPayload;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import api.Utilities.BaseClass;

public class UserUpdatePayload extends BaseClass {
	static String baseUri = routes.getString("baseURL");
	private static String userId = "U141"; 
    private static String userId1 = "A123";
   private static JSONObject payload = new JSONObject();
	   //UserUpdatePayload updater = new UserUpdatePayload();
	   private static JSONArray userRoleList = new JSONArray();
	   private String roleId="R01";
	   
	   private String invalidRoleId = "R0";
    
  public static void  UserRoleListupdate() {
    userRoleList.add("roleId"); 
    payload.put("userRoleList", userRoleList);
    
  }
  public static void  UserRoleListupdateInvalid() {
	  userRoleList.add("invalidRoleId "); 
	    payload.put("userRoleList", userRoleList);
	    
	  }
    
public static void updatestatuscode() {
int statusCode = response.getStatusCode();

System.out.println("Status Code: " + statusCode);
System.out.println("Response Body: " + response.getBody().asString());
}



public static void userroleupdate(){
	

String endpointTemplate = routes.getString("User_Update_RoleID");
String endpoint = endpointTemplate.replace("{userId}", userId);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
}

public static void userroleupdateinvalid(){

String endpointTemplate = routes.getString("User_Update_RoleID");
String endpoint = endpointTemplate.replace("{userId}", userId);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint);
//response = request.when().put(endpoint);

}

public static void userrolestatusupdate(){

String endpointTemplate = routes.getString("User_Update_RoleStatus");
String endpoint = endpointTemplate.replace("{userId}", userId);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint);
}


public static void  userrolestatusupdateinvalid() {

String endpointTemplate = routes.getString("User_Update_RoleStatus");
String endpoint = endpointTemplate.replace("{userId}", userId1);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint);
}

public static void  userroleprogrambatchstatusupdate() {

String endpointTemplate = routes.getString("User_Update_RoleProgramBatchStatus");
String endpoint = endpointTemplate.replace("{userId}", userId);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint);

}
public static void  userroleprogrambatchstatusinvalidupdate() {

String endpointTemplate = routes.getString("User_Update_RoleProgramBatchStatus");
String endpoint = endpointTemplate.replace("{userId}", userId1);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint);

}

public static void userloginstatusupdate() {

String endpointTemplate = routes.getString("User_Update_LoginStatus");
String endpoint = endpointTemplate.replace("{userId}", userId);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint);
}

public static void userloginstatusinvalidupdate() {

String endpointTemplate = routes.getString("User_Update_LoginStatus");
String endpoint = endpointTemplate.replace("{userId}", userId1);
response = given().spec(ReqSpec).body(payload.toJSONString()).when().put(baseUri + endpoint).then().extract().response();
//response = given().spec(BaseClass.ReqSpec).when().put(baseUri + endpoint); 
}
public static int  statuscode() {
int statusCode = response.getStatusCode();
System.out.println("Status Code: " + statusCode);
System.out.println("Response Body: " + response.getBody().asString());
return statusCode;
}
}