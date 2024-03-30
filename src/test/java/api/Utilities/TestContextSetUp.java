package api.Utilities;

//This is the Dependency Class
public class TestContextSetUp 
{	
	public static String BToken;
	public static String UserId;
	

    public static String getUserId() {
		return UserId;
	}

	public static void setUserId(String userId) {
		UserId = userId;
	}

	public String getBearerToken()
    {
        return BToken;
    }

    public void setBearerToken(String bearerToken) 
    {
        this.BToken = bearerToken;
    }   

}
