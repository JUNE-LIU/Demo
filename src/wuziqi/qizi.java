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

	

	
	
	/*��get()��set()���Կ��Ʊ�����
	 JAVA����������еķ���ԺͰ�ȫ�ԡ�����Լ������е���������з�ղ�����
	����private���������ǣ����һ�����������ܶԸñ������ʡ�
	�������Ǿͽ���Щ��������������ڲ�����������������ݵİ�ȫ�ԣ�
	��������Ҫ������Щ�������ô���أ����ǿ���ͨ�����ַ�����
	��һ�м�ͨ��public��ʽ�Ĺ���������ƹ��캯����������һʵ�����ͶԸñ�����ֵ��
	�ڶ��־���ͨ�������ᵽ��set��get�����������Ҿ�һ���ض������ӣ�
	�Ҷ���һ��Person�࣬��������name��age������˽���������
	Ȼ���Ҷ���setname()��getname()��setage()��getage()���ĸ�������
	ͨ�����ĸ�������ʵ�ֶ�name��age�Ĳ���������һ�����Ҳ���ֱ�Ӷ�Person���е������������
	����ͨ��set��get������ӵز�����Щ�����������������������İ�ȫ�ԣ�ͬʱ�ֱ�֤��������ķ�װ�͡�*/

	
}