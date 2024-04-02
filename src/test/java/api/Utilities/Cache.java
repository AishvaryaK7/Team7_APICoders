package api.Utilities;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private static Map<String, Object> cache;
	
	public static Object getData(String key) {
		if(cache!=null) {
			return cache.get(key);
		}
		return null;
	}
	public static Object setData(String key,Object value) {
		if(cache==null) {
			cache=new HashMap<String, Object>();
			
			}
		return cache.put(key, value);
		
	}
	

}
