package com.nitesh.springDemo;

public class MyCustomBeanProcessor implements DisposableBean{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("MyCustomBean : Destroy");
		
	}

}
