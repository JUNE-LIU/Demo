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
		 * ���ֵĶ��壺��Ϊ�����ƺڷ����������ƶ��趨��һ�ֱ�������
		 * ���ֵķ��ࣺ�������֣�����һ������ͬʱ�γ��������������ϵĻ��������ӱ���Ϊ����������ͬ�Ĺ����ӣ���
		 * ���Ľ��֣�����һ������ͬʱ�γ��������������ϵĳ��Ļ���ģ����������֣�����һ�������γ�һ����һ �����ϵĳ�������
		 * ���ɽ��ֵĻ�������Ҫ�أ���������������һ�ſ����γɻ��ĵ����������ģ�����������Գ�����ģ���
		 * ���ģ�ֻ��һ������Գ�����ģ����������������ϵ����ߺ���������һ�����ϣ��γɵ�5������ͬɫ ���Ӳ������������
		 * ���ڽ��ֵĹ涨���ڷ����������ͬʱ�γɣ�����ʧЧ���ڷ�ʤ
		 */
		int hang = qp.getHang();
		int lie = qp.getLie();
		char color = qz.getColor();
		int x = qz.getPosition().getX();
		int y = qz.getPosition().getY();
		char[][] content = qp.getContent();
		if (content[x][y] != '��') {
			jinshou = "��������";
		}
		int sameH = 1;// �M��
		int sameS = 1;// ����
		int sameL = 1;// ��б
		int sameR = 1;// ��б
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
		// �������ж�û�������������ת����һ�������ж�
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
		// �������ж�û�������������ת����һ����б���ж�
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
        	jinshou="��������";
        }
        if(sameH>=4&&sameS>=4){
        	jinshou="���Ľ���";
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
		this.shengfu = "��ʤ��";
		this.jinshou = "�޽���";
	}

	public String panduanShengfu(qizi qz, qipan qp) {
		int hang = qp.getHang();
		int lie = qp.getLie();
		int x = qz.getPosition().getX();
		int y = qz.getPosition().getY();
		char color = qz.getColor();

		int sameH = 1;// �M��
		int sameS = 1;// ����
		int sameL = 1;// ��б
		int sameR = 1;// ��б
		char[][] content = qp.getContent();

		/*
		 * for (int j = y - 1; j >= 0; j--) { if (content[x][j] == color) { left
		 * = j; } } for (int j = left; j <= qp.lie; j++) { if (content[x][j] ==
		 * color) { xiangtongqizi++; } } if (xiangtongqizi >= 5) { shengfu =
		 * "��Ӯ��"; }
		 */
		// �M��
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
			shengfu = "���A����";
		} else { // �������ж�û�������������ת����һ�������ж�
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
				shengfu = "���A����";
			} else { // �������ж�û�������������ת����һ����б���ж�
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
					shengfu = "���A����";
				} else { // ����б���ж�û�������������ת����һ����б���ж�
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
						shengfu = "���A����";
					}
				}
			}
		}
		return shengfu;
	}

}