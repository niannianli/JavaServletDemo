package com.spring.bean;

public class Clazz3 {
	
	public static Operation operation;
	
	public static Operation Operation(){
		
		if(operation == null){
			operation = new Operation();
		}else{
			
		}		
		return operation;
		
	}

}

// maintain singleton like this is too hard,
// as you have to write for everyone

// Spring helps you do all of these

// no need to create object by new keyword

// Spring has a container: includes all objects you need

// tomcat: servlet object will be created
// you use service() method directly

// Spring: ApplicationContext