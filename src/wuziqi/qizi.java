package wuziqi;



public class qizi {

	public position getPosition() {
		return position;
	}

	public void setPosition(position position) {
		this.position = position;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	private position position;
	private char color;

	public qizi(position position,char  color){
		super();
		this.position=position;
		this.color=color;
	}

	

	
	
	/*用get()与set()可以控制变量。
	 JAVA面向对象编程中的封闭性和安全性。封闭性即对类中的域变量进行封闭操作，
	即用private来修饰他们，如此一来其他类则不能对该变量访问。
	这样我们就将这些变量封闭在了类内部，这样就提高了数据的安全性，
	当我们想要操作这些域变量怎么办呢？我们可以通过两种方法，
	第一中即通过public方式的构造器（或称构造函数），对象一实例化就对该变量赋值。
	第二种就是通过上面提到的set和get方法，这里我举一个特定的例子，
	我定义一个Person类，该类中有name、age这两个私有域变量，
	然后我定义setname()、getname()、setage()、getage()这四个方法，
	通过这四个方法来实现对name和age的操作。这样一来，我不用直接对Person类中的域变量操作，
	而是通过set和get方法间接地操作这些变量，这样就能提高域变量的安全性，同时又保证了域变量的封装型。*/

	
}