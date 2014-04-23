package domain;

public class OverdraftException extends Exception{
	private double deficit;
	
	//return the deficit
	public double getDeficit(){
		return deficit;
	}
	
	//construct a exception with a message and a deficit
	public OverdraftException(String message, double inideficit){
		super(message);
		deficit =inideficit;
	}
}
