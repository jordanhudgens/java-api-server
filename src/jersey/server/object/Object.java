package jersey.server.object;

import java.util.HashMap;
import java.util.Map;
import jersey.server.java.ToDo;

public enum Object {
	instance;
	
	private Map<String, ToDo> contentProvider = new HashMap<String, ToDo>();
	
	private Object() 
	{
		ToDo todoExample = new ToDo("1", "Millenium Falcon Oil Change");
		todoExample.setDescription("Mos Eisley, Docking Bay 94");
		contentProvider.put("1", todoExample);
		
		todoExample = new ToDo("2", "Pay Jabba");
		todoExample.setDescription("Avoid Greedo");
		contentProvider.put("2", todoExample);
		
		todoExample = new ToDo("3", "Marry Leia");
		todoExample.setDescription("She's totally just Luke's sister");
		contentProvider.put("3", todoExample);	
	}
	
	public Map<String, ToDo> getModel()
	{
		return contentProvider;
	}
	
}