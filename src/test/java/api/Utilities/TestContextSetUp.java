package api.Utilities;

//This is the Dependency Class
public class TestContextSetUp 
{	
	public static String BToken;
	
	public String getBearerToken()
    {
        return BToken;
    }

    public void setBearerToken(String bearerToken) 
    {
        this.BToken = bearerToken;
    }   

}
