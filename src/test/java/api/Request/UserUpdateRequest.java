package api.Request;
import api.Utilities.ExcelUtilis;

import java.io.IOException;

import org.json.simple.JSONObject;

import api.Utilities.BaseClass;

public class UserUpdateRequest extends BaseClass{
	  private static JSONObject payload = new JSONObject();
		public static ExcelUtilis xlutils=new ExcelUtilis(path.getString("TestDataFile"));
		 
	    private static final String sheet = "User_Update";
	    
	    
	    public static void RoleProgramBatchStatusUpdate() throws IOException {
	        
	            // Assuming TotalRows indicates the total number of rows to process
	        	int TotalRows = 5;

	            for (int i = 1; i <= TotalRows; i++) { // Assuming row 1 contains headers and data starts from row 2
	                String roleID = null;
	                String email = null;
	                String batchId = null;
	                String batchStatus = null;
	                String programID = null;

	                // Assuming the last column index you're interested in is 7 (adjust if needed)
	                int TotalCells = 7; 

	                for (int j = 0; j <= TotalCells; j++) {
	                   
	    			
	    				switch (j) {
	                        case 0:
	                            roleID = xlutils. getCellData(sheet, i, j);
	                            break;
	                        case 3:
	                            email =  xlutils.getCellData(sheet, i, j);
	                            break;
	                        case 4:
	                            batchId =  xlutils.getCellData(sheet, i, j);
	                            break;
	                        case 5:
	                            batchStatus =  xlutils.getCellData(sheet, i, j);
	                            break;
	                        case 6:
	                            programID =  xlutils.getCellData(sheet, i, j);
	                            break;
	                    }
	                }
	                System.out.println("Role ID: " + roleID + ", Email: " + email + ", Batch ID: " + batchId + ", Status: " + batchStatus + ", Program ID: " + programID);
	            }
	    	
	        }
	    
	        
	    public static  void RoleProgramBatchStatusInvalidUpdate() throws IOException {
	        
            // Assuming TotalRows indicates the total number of rows to process
        	int TotalRows = 5;

            for (int i = 4; i <= TotalRows; i++) { // Assuming row 1 contains headers and data starts from row 2
                String roleID = null;
                String email = null;
                String batchId = null;
                String batchStatus = null;
                String programID = null;

                // Assuming the last column index you're interested in is 7 (adjust if needed)
                int TotalCells = 7; 

                for (int j = 0; j <= TotalCells; j++) {
                   
    			
    				switch (j) {
                        case 0:
                            roleID = xlutils. getCellData(sheet, i, j);
                            break;
                        case 3:
                            email =  xlutils.getCellData(sheet, i, j);
                            break;
                        case 4:
                            batchId =  xlutils.getCellData(sheet, i, j);
                            break;
                        case 5:
                            batchStatus =  xlutils.getCellData(sheet, i, j);
                            break;
                        case 6:
                            programID =  xlutils.getCellData(sheet, i, j);
                            break;
                    }
                }
                System.out.println("Role ID: " + roleID + ", Email: " + email + ", Batch ID: " + batchId + ", Status: " + batchStatus + ", Program ID: " + programID);
            }
    	
        }
    
        
    

   

	   
	    
	    

	        
	   
//rolestatus
	    public static void RolestatusUpdateUsersInvalidData() {
	        try {
	            // Assuming TotalRows indicates the total number of rows to process
	            int TotalRows = 5;
	            int TotalCells = 2; // Assuming you are interested in two cells (roleID and userRoleStatus)

	            for (int i = 5; i <= TotalRows; i++) { // Assuming row 1 contains headers and data starts from row 2
	                String roleID = null;
	                String userRoleStatus = null;

	                for (int j = 0; j <= TotalCells; j++) {
	                    switch (j) {
	                        case 0: // Assuming roleID is in the first column
	                            roleID = xlutils.getCellData(sheet, i, j);
	                            break;
	                        case 1: // Assuming userRoleStatus is in the second column
	                            userRoleStatus = xlutils.getCellData(sheet, i, j);
	                            break;
	                        default:
	                            break;
	                    }
	                }

	                System.out.println("Role ID: " + roleID + ", UserRoleStatus: " + userRoleStatus);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


	public static void RolestatusUpdateUsers() {
	    try {
	        // Assuming TotalRows indicates the total number of rows to process
	        int TotalRows = 5;

	        for (int i = 1; i <= TotalRows; i++) { // Assuming row 1 contains headers and data starts from row 2
	            String roleID = null;
	            String userRoleStatus = null;

	            // Assuming the last column index you're interested in is 2 (adjust if needed)
	            int TotalCells = 2;

	            for (int j = 0; j <= TotalCells; j++) {
	                switch (j) {
	                    case 0:
	                        roleID = xlutils.getCellData(sheet, i, j);
	                        break;
	                    case 1:
	                        userRoleStatus = xlutils.getCellData(sheet, i, j);
	                        break;
	                }
	            }
	            System.out.println("Role ID: " + roleID + ", User Role Status: " + userRoleStatus);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	
	
	
//login
 
		
		public static void loginUpdateUsers() {
		    try {
		        // Assuming TotalRows indicates the total number of rows to process
		        int TotalRows = 5;
		        int TotalCells = 7; // Assuming this is the last column index you're interested in

		        for (int i = 1; i <= TotalRows; i++) {
		            String roleID = null;
		            String userRoleStatus = null;
		            String loginStatus = null;
		            String userLoginEmail = null;
		            String password = null;

		            for (int j = 0; j <= TotalCells; j++) {
		                switch (j) {
		                    case 0:
		                        roleID = xlutils.getCellData(sheet, i, j);
		                        break;
		                    case 1:
		                        userRoleStatus = xlutils.getCellData(sheet, i, j);
		                        break;
		                    case 2:
		                        loginStatus = xlutils.getCellData(sheet, i, j);
		                        break;
		                    case 3:
		                        userLoginEmail = xlutils.getCellData(sheet, i, j);
		                        break;
		                    case 7:
		                        password = xlutils.getCellData(sheet, i, j);
		                        break;
		                }
		            }
		            System.out.println("Role ID: " + roleID + "  User Role Status: " + userRoleStatus + "  Login Status: " + loginStatus + "  User Login Email: " + userLoginEmail + "  Password: " + password);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}


		
public static void loginInvalidUpdateUsers() {
    try {
        // Assuming TotalRows indicates the total number of rows to process
        int TotalRows = 5;
        // TotalCells to define the number of columns we're interested in
        int TotalCells = 7; // Assuming the highest column index you need is 7

        for (int i = 4; i <= TotalRows; i++) { // Starting from row 2 assuming row 1 contains headers
            String roleID = null;
            String userRoleStatus = null;
            String loginStatus = null;
            String userLoginEmail = null;
            String password = null;

            for (int j = 0; j <= TotalCells; j++) {
                switch (j) {
                    case 0:
                        roleID = xlutils.getCellData(sheet, i, j);
                        break;
                    case 1:
                        userRoleStatus = xlutils.getCellData(sheet, i, j);
                         
                        break;
                    case 2:
                    	loginStatus = xlutils.getCellData(sheet, i, j);
                        break;
                    case 3:
                    	userLoginEmail = xlutils.getCellData(sheet, i, j);
                        break;
                    case 7:
                        password = xlutils.getCellData(sheet, i, j);
                        break;
                   
                }
            }
            // Assuming userLoginEmail is meant to be set from one of the cells
            System.out.println("Role ID: " + roleID + ", User Role Status: " + userRoleStatus + ", Login Status: " + loginStatus + ", User Email: " + userLoginEmail + ", Password: " + password);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
		
		
		
		
	        

		

	
		

}
