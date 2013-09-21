package DataStructs;

public class Copist {
	
	protected Object clone;
	
	private Copist(Object _clone){
		clone = _clone;
	}

	private Object getObject() {
		return clone;
	}
	
	public static Object copy(Object _clone){
		return new Copist(_clone).getObject();
	}
	
}
