package wuziqi;

public class qipan {
	private qipan qp;
	int hang;

	public int getHang() {
		return hang;
	}

	public void setHang(int hang) {
		this.hang = hang;
	}

	public int getLie() {
		return lie;
	}

	public void setLie(int lie) {
		this.lie = lie;
	}

	int lie;
	char[][] content;

	public char[][] getContent() {
		return content;
	}

	public void setContent(char[][] content) {
		this.content = content;
	}

	// private int[][]board;
	// public qipan(int dimension){
	// board=new int[dimension][dimension];
	//
	// }
	//
	// public int getDimension(){
	// return board.length;
	// }
	// public void chushihua() {
	/*
	 * System.out.println("��ʼ���С�"); hang = 11; lie = 11; content = new
	 * char[hang][lie]; for (int i = 0; i < hang; i++) { for (int j = 0; j <
	 * lie; j++) { content[i][j] = '��';
	 * 
	 * } }
	 */
	// }
	public qipan(int H, int L) { // ���ù��췽���������̳�ʼ��
		hang = H;
		lie = L;
		content = new char[H][L];
		System.out.println("��ʼ���С�");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < L; j++) {
				content[i][j] = '��';
			}
		}
	}

	public qipan() {

	}// �޲�

	public void xianshiqipan() {
		System.out.println("��������");

		/*
		 * for(int i=0;i<hang;i++){ for(int j=0;j<lie;j++){
		 * System.out.print("+ "); } System.out.print('\n'); }
		 */
		System.out.print("       ");
		for (int j = 0; j < lie; j++) {

			System.out.print(j + "   ");
		}
		System.out.print("\n");// ��ʾ�б��
		for (int i = 0; i < hang; i++) {
			System.out.print("  " + i);// ��ʾ�б��

			for (int j = 0; j < lie; j++) {
				if (content[i][j] == '��') {
					System.out.print("   +");
				} else if (content[i][j] == '��') {
					System.out.print("   ��");
				} else if (content[i][j] == '��') {
					System.out.print("   ��");
				}

			}
			System.out.print("\n");
		}

	}

	public boolean tianjiaqizi(qizi qz) { // �������ͱ���ֻ��������ֵ��true��false.
		int x = qz.getPosition().getX();
		int y = qz.getPosition().getY();
		// System.out.println("x:"+x+"y:"+y);
		if (content[x][y] == '��') {// �ж�ֻ������֮ǰû���¹������������
			char color = qz.getColor();
			content[x][y] = color;
		} else
			System.out.println("��������");

		return true;
	}

	public void clear(int H, int L) {
		hang = H;
		lie = L;
		content = new char[H][L];
		for (int i = 0; i < qp.getHang(); i++) {
			for (int j = 0; j < qp.getLie(); j++) {
				content[i][j] = '��';
				setHang(-1);
				setLie(-1);
			}
		}
	}

}
