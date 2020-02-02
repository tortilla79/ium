import java.util.HashMap;

public class Gson {

    //public static String toJson(String data){}

    // esempio di stringa da parsare:
    // {"username":"","email":"","password":"","passwordrip":""}
    public static HashMap<String, String> fromJson(String json) {
	HashMap<String, String> nameValue = new HashMap<>();
	int xy[] = new int[4];
	int symcnt = 0;

	for (int i = 0; i < json.length(); i++){
	    if (json.charAt(i) == '"') {
		xy[symcnt] = i;
		symcnt += 1;
	    }
	    if (symcnt == 4) {
		nameValue.put(json.substring(xy[0]+1, xy[1]),
			      json.substring(xy[2]+1, xy[3]));
		symcnt = 0;
	    }
	}
	return nameValue;

    }
    public static void main(String args[]) {
	String str = "{\"username\":\"aldo\",\"email\":\"moro\",\"password\":\"maro\",\"passwordrip\":\"chepalle\"}";
	System.out.println(Gson.fromJson(str).toString());
	    
    }
}
