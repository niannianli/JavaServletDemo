
public class Util {
	
	private static Clazz c = null;
	
	static {
		c = new Clazz();
	}

	public static Clazz getObject(){
		return c;
	}
}
