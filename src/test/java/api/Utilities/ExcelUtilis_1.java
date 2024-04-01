package api.Utilities;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.runner.Request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ExcelUtilis_1 {
	static JSONObject request;
	Response response;
	

//	public static RequestSpecification getBasicAuth() {
//		JSONObject request = new JSONObject();
//		request.put("password", "lmsHackathon@2024");
//		request.put("user_last_name", "numpyninja@gmail.com");
//		return RestAssured.given().auth().basic("Numpy@gmail.com", "userAPI").contentType(ContentType.JSON)
//				.baseUri("https://userapi-8877aadaae71.herokuapp.com/uap");
//		
//	}

	public static String  login() {
        RestAssured.baseURI = "https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
        JSONObject request = new JSONObject();
        request.put("password", "lmsHackathon@2024");
		request.put("userLoginEmailId", "numpyninja@gmail.com");
		Response response = given().contentType(ContentType.JSON)
				.body(request.toJSONString()).when().post("/login");
		return response.jsonPath().getString("token");
//        System.out.println("Response Status Code: " + response.getStatusCode());
//
//        // Print the token
//        System.out.println("Token: " + token);

	}
	
//	public static String POST_Createuser_Payload() throws IOException {
//		String path = "C:\\Rest_assured_hackathon\\Testdata.xlsx";
//		File excelFile = new File(path);
//		FileInputStream fis = new FileInputStream(excelFile);
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
//
//		XSSFSheet sheet = wb.getSheet("UserController");
//		int totalRows=sheet.getLastRowNum();
//		
//		DataFormatter format = new DataFormatter();
//		for(int i=1;i<=totalRows;i++) {
//			Row row = sheet.getRow(i);
//			if(row !=null) {
//	request = new JSONObject();
//        request.put("userEduPg", row[] 0));
//		request.put("userEduUg", row[] 1));
//		request.put("userFirstName", row[] 2));
//		request.put("userLastName", row[] 3));
//		request.put("userLinkedinUrl", row[] 4));
//		request.put("userLocation", row[] 5));
//	
//		JSONObject userLogin = new JSONObject();
//        userLogin.put("loginStatus", row[] 6));
//        userLogin.put("userLoginEmail", row[] 7));
//       
//        request.put("userLogin", userLogin);
//
//        request.put("userMiddleName", row[] 8));
//		request.put("userPhoneNumber", format.formatCellValue(row.getCell(9)));
//		
//        JSONArray userRoleMapsArray = new JSONArray();
//		JSONObject userRoleMap = new JSONObject();
//        userRoleMap.put("roleId", row[] 10));
//        userRoleMap.put("userRoleStatus", row[] 11));
//       
//        userRoleMapsArray.add(userRoleMap);
//
//        request.put("userRoleMaps", userRoleMapsArray);
//        
//		request.put("userTimeZone", row[] 12));
//		request.put("userVisaStatus", row[] 13));
//			}
//		}
//		return request.toJSONString();
//	}
	
	public static RequestSpecification getRequestSpecification(String token) {
		return RestAssured.given()
        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON);
	}
	
	public static RequestSpecification getRequestSpecificationNoAuth() {
		return RestAssured.given()
        .contentType(ContentType.JSON);
	}
		
//	public static void main(String[] args) throws IOException {
//		POST_Createuser_Payload();
//	}
	
	public static Object[][] readDataFromExcel(String filePath, String sheetName) throws IOException {
		DataFormatter format = new DataFormatter();
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = row.getCell(j)!= null? format.formatCellValue(row.getCell(j)): "";
            }
        }

        workbook.close();
        file.close();

        return data;
    }
	 public static JSONObject createRequestObject(Object[] row) {
			

		 request = new JSONObject();
	        request.put("userEduPg", row[0]);
			request.put("userEduUg", row[1]);
			request.put("userFirstName", row[2]);
			request.put("userLastName", row[3]);
			request.put("userLinkedinUrl", row[4]);
			request.put("userLocation", row[5]);
		
			JSONObject userLogin = new JSONObject();
	        userLogin.put("loginStatus", row[6]);
	        userLogin.put("userLoginEmail", row[7]);
	       
	        request.put("userLogin", userLogin);

	        request.put("userMiddleName", row[8]);
			request.put("userPhoneNumber", row[9]);
			
	        JSONArray userRoleMapsArray = new JSONArray();
			JSONObject userRoleMap = new JSONObject();
	        userRoleMap.put("roleId", row[10]);
	        userRoleMap.put("userRoleStatus", row[11]);
	       
	        userRoleMapsArray.add(userRoleMap);

	        request.put("userRoleMaps", userRoleMapsArray);
	        
			request.put("userTimeZone", row[12]);
			request.put("userVisaStatus", row[13]);
			return request; 
	 }
	 public String calculateRowNumber(int totalRows, String offset) {
	        // Add your logic here to calculate the row number dynamically
	        // For example, you could use the offset to determine the row number
	        return offset;
	    }
	
	
	

}
