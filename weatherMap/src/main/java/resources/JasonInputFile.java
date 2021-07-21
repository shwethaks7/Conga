package resources;

public class JasonInputFile {
	
	public String getStationRegBody1() {
	return "{\r\n"
			+ "  \"external_id\": \"SF_TEST001\",\r\n"
			+ "  \"name\": \"San Francisco Test Station\",\r\n"
			+ "  \"latitude\": 37.76,\r\n"
			+ "  \"longitude\": -122.43,\r\n"
			+ "  \"altitude\": 150\r\n"
			+ "}";
	}
	
	public String getStationRegBody2() {
		return "{\r\n"
				+"\"external_id\": \"DEMO_TEST001\",\r\n"
				+ "\"name\": \"Interview Station <Random Number>\", \"latitude\": 33.33,\r\n"
				+ "\"longitude\": -111.43,\r\n"
				+ "\"altitude\": 444 \r\n"
				+ "}";
		}
	public String getStationRegBody3() {
		return "{\r\n"
				+"\"external_id\": \"Interview1 \",\r\n"
				+ "\"name\": \"Interview Station <Random Number>\", \"latitude\": 33.44,\r\n"
				+ "\"longitude\": -12.44,\r\n"
				+ "\"altitude\": 444 \r\n"
				+ "}";
		}

}
