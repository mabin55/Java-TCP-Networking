import java.io.*;

public class CSMessage implements Task, Serializable {

	//The variable that holds the error information
	private String finalResult;

	public CSMessage() {
	}

	//Return the final computing result

	public Object getResult() {
		return finalResult;
	}

	//Set the error message
	public void setMessage(String msg) {
		finalResult=msg;
	}

	public void executeTask() {
	}
}