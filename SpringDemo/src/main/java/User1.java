
public class User1 {
	
	// we called the method 4 times, and 4 objects created
		// we can define class outside, then 2 objects
	// for 2 classes	
	// best: use static method?
	// single object	
	// no matter how many times called, only one object
	Clazz c = Util.getObject();	
	public void call1(){	
		c.callMe();
	}	
	public void call2(){	
		c.callMe();
	}
}
