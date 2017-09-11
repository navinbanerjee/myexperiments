package polmorphismtest;

public abstract class A {
	
	public void testing(){
		try{
			doSomething();
		}finally{
			close();
		}
	}
	
	public void doSomething(){
		System.out.println("do something A");
	}

	public void close(){
		System.out.println("in close A");
	}
}
