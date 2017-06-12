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
	 * System.out.println("初始化中。"); hang = 11; lie = 11; content = new
	 * char[hang][lie]; for (int i = 0; i < hang; i++) { for (int j = 0; j <
	 * lie; j++) { content[i][j] = '空';
	 * 
	 * } }
	 */
	// }
	public qipan(int H, int L) { // 运用构造方法，将棋盘初始化
		hang = H;
		lie = L;
		content = new char[H][L];
		System.out.println("初始化中。");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < L; j++) {
				content[i][j] = '空';
			}
		}
	}

	public qipan() {

	}// 无参

	public void xianshiqipan() {
		System.out.println("更新棋盘");

		/*
		 * for(int i=0;i<hang;i++){ for(int j=0;j<lie;j++){
		 * System.out.print("+ "); } System.out.print('\n'); }
		 */
		System.out.print("       ");
		for (int j = 0; j < lie; j++) {

			System.out.print(j + "   ");
		}
		System.out.print("\n");// 显示列标号
		for (int i = 0; i < hang; i++) {
			System.out.print("  " + i);// 显示行标号

			for (int j = 0; j < lie; j++) {
				if (content[i][j] == '空') {
					System.out.print("   +");
				} else if (content[i][j] == '黑') {
					System.out.print("   ■");
				} else if (content[i][j] == '白') {
					System.out.print("   ○");
				}

			}
			System.out.print("\n");
		}

	}

	public boolean tianjiaqizi(qizi qz) { // 布尔类型变量只能有两个值：true和false.
		int x = qz.getPosition().getX();
		int y = qz.getPosition().getY();
		// System.out.println("x:"+x+"y:"+y);
		if (content[x][y] == '空') {// 判断只有棋子之前没被下过才能添加棋子
			char color = qz.getColor();
			content[x][y] = color;
		} else
			System.out.println("已有棋子");

		return true;
	}

	public void clear(int H, int L) {
		hang = H;
		lie = L;
		content = new char[H][L];
		for (int i = 0; i < qp.getHang(); i++) {
			for (int j = 0; j < qp.getLie(); j++) {
				content[i][j] = '空';
				setHang(-1);
				setLie(-1);
			}
		}
	}

}
