package api.RequestPayload;
import org.json.simple.JSONObject;
import api.Utilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ProgramPayload {

	static JSONObject request;
	String programName;
	String programDesc;
	String programStatus;

	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramDesc() {
		return programDesc;
	}
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}
	public String getProgramStatus() {
		return programStatus;
	}
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	
	public static JSONObject createRequestObject(Object[] row) {
		 request = new JSONObject();
		 request.put("programDescription", row[0]);
		 int rndInt = (int) Math.floor(Math.random() * (1000)+1);
		 request.put("programName", row[1]+ String.valueOf(rndInt));	 
		 request.put("programStatus", row[2]);
		 return request; 
	 }
}