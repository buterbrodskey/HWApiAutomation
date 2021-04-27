package HWApiAutomation.client;

public class DeleteResponse{
	private final int code = 200;
	private final String type = "unknown";
	private String message = "1";


	public int getCode(){
		return code;
	}

	public String getType(){
		return type;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"DeleteResponse{" + 
			"code = '" + code + '\'' + 
			",type = '" + type + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
