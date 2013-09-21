package BinaryNotationConverterPackege.Exceptions;

public class NumberOutRangedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String msgError = "The number is outside the specified limits.";
	
	public NumberOutRangedException(){
		super(msgError);
	}
	
	public String getMessage(){
		return msgError;
	}
}
