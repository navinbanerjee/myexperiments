package polmorphismtest;

public class B extends A {
	

	
	@Override
	public void doSomething(){
		System.out.println("do something B");
	}
	
	@Override
	public void close(){
//		super.close();
		System.out.println("in close B");
	}
	
	public static void main(String[] args) {
		A a = new B();
		a.testing();
//		a.close();
	}

}
