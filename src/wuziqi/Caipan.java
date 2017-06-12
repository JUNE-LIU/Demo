package wuziqi;

public class Caipan {
	private String shengfu;
	private String jinshou;
	private qipan qp;
	private Caipan cp;
	private qizi lastqizi;

	public String getShengfu() {
		return shengfu;
	}

	public void setShengfu(String shengfu) {
		this.shengfu = shengfu;
	}

	public String getJinshou() {
		return jinshou;
	}

	public void setJinshou(String jinshou) {
		this.jinshou = jinshou;
	}

	public String panduanJinshou(qizi qz, qipan qp) {
		/*
		 * 禁手的定义：是为了限制黑方的先手优势而设定的一种比赛规则
		 * 禁手的分类：三三禁手（黑棋一子落下同时形成两个或两个以上的活三，此子必须为两个活三共同的构成子）、
		 * 四四禁手（黑棋一子落下同时形成两个或两个以上的冲四或活四）、长连禁手（黑棋一子落下形成一个或一 个以上的长连）。
		 * 构成禁手的基本子力要素：活三（本方再走一着可以形成活四的三）、活四（有两个点可以成五的四）、
		 * 冲四（只有一个点可以成五的四）、长连（在棋盘上的阳线和阴线任意一条线上，形成的5个以上同色 棋子不间隔的相连）
		 * 关于禁手的规定：黑方五连与禁手同时形成，禁手失效，黑方胜
		 */
		int hang = qp.getHang();
		int lie = qp.getLie();
		char color = qz.getColor();
		int x = qz.getPosition().getX();
		int y = qz.getPosition().getY();
		char[][] content = qp.getContent();
		if (content[x][y] != '空') {
			jinshou = "已有棋子";
		}
		int sameH = 1;// 橫向
		int sameS = 1;// 竖向
		int sameL = 1;// 左斜
		int sameR = 1;// 右斜
		for (int i = 1; i < 5; i++) {
			if (x >= hang - 1) {
				break;
			}
			if (content[x + i][y] == color) {
				sameH++;
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (x <= 1) {
				break;
			}
			if (content[x - i][y] == color) {
				sameH++;
			} else {
				break;
			}
		}
		// 若横向判断没有连上五颗棋则转向下一个竖向判断
		for (int i = 1; i < 5; i++) {
			if (y >= lie - 1) {
				break;
			}
			if (content[x][y + i] == color) {
				sameS++;
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (y <= 1) {
				break;
			}
			if (content[x][y - i] == color) {
				sameS++;
			} else {
				break;
			}
		}
		// 若竖向判断没有连上五颗棋则转向下一个左斜向判断
		for (int i = 1; i < 5; i++) {
			if (x >= hang - 1 | y <= 1) {
				break;
			}
			if (content[x + i][y - i] == color) {
				sameL++;
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (x <= 1 | y >= lie - 1) {
				break;
			}
			if (content[x - i][y + i] == color) {
				sameL++;
			} else {
				break;
			}
		}

		for (int i = 1; i < 5; i++) {
			if (x >= hang - 1 | y >= lie - 1) {
				break;
			}
			if (content[x + i][y + i] == color) {
				sameR++;
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (x <= 1) {
				break;
			}
			if (content[x - i][y - i] == color) {
				sameR++;
			} else {
				break;
			}
		}
        if(sameH>=3&&sameS>=3){
        	jinshou="三三禁手";
        }
        if(sameH>=4&&sameS>=4){
        	jinshou="四四禁手";
        }
		return jinshou;
	}

	public Caipan(String shengfu, String jinshou) {
		super();
		this.shengfu = shengfu;
		this.jinshou = jinshou;
	}

	public Caipan() {
		super();
		this.shengfu = "无胜负";
		this.jinshou = "无禁手";
	}

	public String panduanShengfu(qizi qz, qipan qp) {
		int hang = qp.getHang();
		int lie = qp.getLie();
		int x = qz.getPosition().getX();
		int y = qz.getPosition().getY();
		char color = qz.getColor();

		int sameH = 1;// 橫向
		int sameS = 1;// 竖向
		int sameL = 1;// 左斜
		int sameR = 1;// 右斜
		char[][] content = qp.getContent();

		/*
		 * for (int j = y - 1; j >= 0; j--) { if (content[x][j] == color) { left
		 * = j; } } for (int j = left; j <= qp.lie; j++) { if (content[x][j] ==
		 * color) { xiangtongqizi++; } } if (xiangtongqizi >= 5) { shengfu =
		 * "你赢啦"; }
		 */
		// 橫向
		for (int i = 1; i < 5; i++) {
			if (x >= hang - 1) {
				break;
			}
			if (content[x + i][y] == color) {
				sameH++;
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (x <= 1) {
				break;
			}
			if (content[x - i][y] == color) {
				sameH++;
			} else {
				break;
			}
		}
		if (sameH > 4) {
			shengfu = "你贏啦！";
		} else { // 若横向判断没有连上五颗棋则转向下一个竖向判断
			for (int i = 1; i < 5; i++) {
				if (y >= lie - 1) {
					break;
				}
				if (content[x][y + i] == color) {
					sameS++;
				} else {
					break;
				}
			}
			for (int i = 1; i < 5; i++) {
				if (y <= 1) {
					break;
				}
				if (content[x][y - i] == color) {
					sameS++;
				} else {
					break;
				}
			}
			if (sameS > 4) {
				shengfu = "你贏啦！";
			} else { // 若竖向判断没有连上五颗棋则转向下一个左斜向判断
				for (int i = 1; i < 5; i++) {
					if (x >= hang - 1 | y <= 1) {
						break;
					}
					if (content[x + i][y - i] == color) {
						sameL++;
					} else {
						break;
					}
				}
				for (int i = 1; i < 5; i++) {
					if (x <= 1 | y >= lie - 1) {
						break;
					}
					if (content[x - i][y + i] == color) {
						sameL++;
					} else {
						break;
					}
				}
				if (sameL > 4) {
					shengfu = "你贏啦！";
				} else { // 若左斜向判断没有连上五颗棋则转向下一个右斜向判断
					for (int i = 1; i < 5; i++) {
						if (x >= hang - 1 | y >= lie - 1) {
							break;
						}
						if (content[x + i][y + i] == color) {
							sameR++;
						} else {
							break;
						}
					}
					for (int i = 1; i < 5; i++) {
						if (x <= 1) {
							break;
						}
						if (content[x - i][y - i] == color) {
							sameR++;
						} else {
							break;
						}
					}
					if (sameR > 4) {
						shengfu = "你贏啦！";
					}
				}
			}
		}
		return shengfu;
	}

}