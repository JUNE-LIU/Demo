package wuziqi;

import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jdk.nashorn.internal.scripts.JO;

//实现接口
public class Panel extends JPanel implements ActionListener {
	private qipan qp = new qipan();
	private qizi lastqizi;
	private Caipan cp = new Caipan();// 成员变量应对其进行初始化操作
	// private qizi xinqizi;
	int w, h;// 宽和高，用像素为单位。
	// int c, r;// //列和行，列的线条和行的线条数用正整数表示。
	int align;// 边距
	int size;// 网格间距,网格距离窗体的距离
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;// 屏幕的宽
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;// 屏幕的长
	String[] choics = { "put.wav", "win.wav", "lost.wav" }; // 声音文件名数组
	URL file1 = getClass().getResource(choics[0]); // 落子声音文件
	URL file2 = getClass().getResource(choics[1]); // 获胜声音文件
	URL file3 = getClass().getResource(choics[2]); // 已有棋子声音文件
	AudioClip soundPut = java.applet.Applet.newAudioClip(file1); // 落子声音剪辑对象
	AudioClip soundWin = java.applet.Applet.newAudioClip(file2); // 获胜声音剪辑对象
	AudioClip soundLost = java.applet.Applet.newAudioClip(file3); // 当前位置不能下棋声音剪辑对象
	ImageIcon background = new ImageIcon("src/wuziqi/wzq.jpg");// 加载图片
	ImageIcon black = new ImageIcon("src/wuziqi/blackchess.GIF");// 加载图片
	ImageIcon white = new ImageIcon("src/wuziqi/whitechess.GIF");// 加载图片
	Image im = Toolkit.getDefaultToolkit().getImage("src/wuziqi/wzq.jpg");
	protected String message;

	public Panel(qipan qp, qizi lastqizi) {
		this.qp = qp;
		this.lastqizi = lastqizi;

	}

	public Panel()// 无参构造函数
	{
		this.w = 0;
		this.h = 0;
	}

	public Panel(int w, int h)// 只有宽和高数的构造函数
	{
		this.qp = new qipan(12, 12);
		this.lastqizi = null;

		this.w = w;
		this.h = h;

		// 添加图片的方法 并非背景图片
		// JLabel lblImage = new JLabel(new ImageIcon("D:wzq.JPG"));
		// lblImage.setBounds(0, 0, w, h);
		// this.add(lblImage);
		// String path = "D:wzq.jpg";
		// ImageIcon background = new ImageIcon(path);
		// // 把背景图片显示在一个标签里面
		// JLabel label = new JLabel(background);
		// // 把标签的大小位置设置为图片刚好填充整个面板
		// label.setBounds(0, 0, this.getWidth(), this.getHeight());
		// this.add(label, new Integer(Integer.MIN_VALUE));
		this.setBackground(Color.BLUE);

		// 添加按钮
		JButton b1 = new JButton("重新开局");
		JButton b2 = new JButton("退出");
		this.add(b1);
		this.add(b2);
		this.setLayout(null);// 要想使用绝对定位 必须先使用null布局 即空布局
		b1.setBounds(100, 480, 100, 50);
		b2.setBounds(250, 480, 100, 50);
		b1.addActionListener(this);
		b2.addActionListener(this);
		message = "黑方先行";
		this.addMouseListener(new MouseAdapter() { // 添加鼠标点击事件

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				int i = (x - align + size / 2) / size;
				int j = (y - align + size / 2) / size;
				System.out.println("点击坐标为(" + e.getX() + "," + e.getY() + ")");
				System.out.println("i:" + i + "j:" + j);
				qizi xinqizi = null;
				if (lastqizi == null) {
					xinqizi = new qizi(new position(i, j), '黑');
					message = "轮到白方";
					// qp.tianjiaqizi(xinqizi);
					soundPut.play();
				} else {
					if (lastqizi.getColor() == '白') {
						xinqizi = new qizi(new position(i, j), '黑');
						cp.panduanJinshou(xinqizi, qp);
						if (cp.getJinshou() != null && cp.getJinshou().equals("已有棋子")) {
							JOptionPane.showMessageDialog(Panel.this, "当前位置已有棋子", "请换个位置下棋",
									JOptionPane.WARNING_MESSAGE);
							soundLost.play();
							xinqizi.setColor('白');
							cp.setJinshou(" ");// 置空

						} // 先判断当前位置是否有棋子
						if ( cp.getJinshou().equals("三三禁手")) {
							JOptionPane.showMessageDialog(Panel.this, "黑棋三三禁手", "禁手", JOptionPane.WARNING_MESSAGE);
							cp.setJinshou(" ");// 置空
							xinqizi = null;
						}
						if ( cp.getJinshou().equals("四四禁手")) {
							JOptionPane.showMessageDialog(Panel.this, "黑棋四四禁手", "禁手", JOptionPane.WARNING_MESSAGE);
							cp.setJinshou(" ");// 置空
							xinqizi = null;
						}//若为黑棋，且处于禁手位置，当前位置不能下
						// qp.tianjiaqizi(xinqizi);
						message = "轮到白方";
						soundPut.play();
					} else if (lastqizi.getColor() == '黑') {
						xinqizi = new qizi(new position(i, j), '白');
						cp.panduanJinshou(xinqizi, qp);
						if (cp.getJinshou() != null && cp.getJinshou().equals("已有棋子")) {
							JOptionPane.showMessageDialog(Panel.this, "当前位置已有棋子", "请换个位置下棋",
									JOptionPane.WARNING_MESSAGE);
							soundLost.play();
							xinqizi.setColor('黑');
							cp.setJinshou(" ");
						}

						// qp.tianjiaqizi(xinqizi);
						message = "轮到黑方";
						soundPut.play();

					}

				}

				// lastqizi = new qizi(new position(i, j),
				// xinqizi.getColor());
				qp.tianjiaqizi(xinqizi);
				lastqizi = xinqizi;// 将下好的棋子变成上一颗棋子。
				repaint();
				// if(cp.panduanShengfu(lastqizi, qp)==null)

				// System.out.println("这里的str值是null");
				cp.panduanShengfu(lastqizi, qp);
				if (cp.getShengfu() != null && cp.getShengfu().equals("你贏啦！")) {
					if (lastqizi.getColor() == '黑') {
						JOptionPane.showMessageDialog(Panel.this, "黑棋胜利啦! ! !");// 显示对话框
						soundWin.play();
						cp.setShengfu(" ");
						// removeAll();清除组件
						// repaint();
						qp = new qipan(12, 12);// 胜利后清除棋子

						// qp.clear(9,9);
						repaint();
						lastqizi = null;

					} else if (lastqizi.getColor() == '白') {
						JOptionPane.showMessageDialog(Panel.this, "白棋胜利啦! ! !");
						soundWin.play();
						cp.setShengfu(" ");
						qp = new qipan(12, 12);// 胜利后清除棋子

						// qp.clear(9,9);
						repaint();
						lastqizi = null;
					}
				}

			}

		});

	}

	// 添加按钮点击事件
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if (cmd.equals("重新开局")) {
			JOptionPane.showMessageDialog(Panel.this, "确定放弃当前的棋局", "请君三思哦~", JOptionPane.OK_OPTION);
			qp = new qipan(12, 12);// 胜利后清除棋子
			repaint();
			lastqizi = null;

		}
		if (cmd.equals("退出")) {
			System.exit(0);
		}

	}

	// 落子声音
	public void putVoice() {
		soundPut.play();
	}

	// 获胜声音
	public void winVoice() {
		soundWin.play();
	}

	// 当前位置不能下棋声音
	public void lostVoice() {
		soundLost.play();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(im, 0, 0, null);
		g.setFont(new Font("黑体", Font.BOLD, 20));// 设置“游戏信息”的字体
		g.drawString("游戏信息：  " + message, 150, 40);

		g.setFont(new Font("华云彩文", Font.PLAIN, 14));// 再为以下文字设置字体
		g.drawString("黑方时间：无限制", 45, 470);
		g.drawString("白方时间：无限制", 260, 470);
		align = 50;// 棋盘距离窗体的网格距离
		size = (w - 2 * align) / (qp.getLie() - 1);// 每一列的平均宽度

		for (int y = 1; y < qp.getHang() - 1; y++)// 划横线。留下四周边界不画
		{
			g.drawLine(0 + align, y * size + align, align + (qp.getLie() - 1) * size, y * size + align);
		}
		for (int x = 1; x < qp.getLie() - 1; x++)// 划纵线。留下四周边界不画
		{
			g.drawLine(x * size + align, 0 + align, x * size + align, align + (qp.getHang() - 1) * size);
		}

		// 设置四周边线为粗线
		float lineWidth = 3.0f;
		((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
		// 划边界四周
		g.drawLine(align, align, size * (qp.getLie() - 1) + align, align);
		g.drawLine(align, size * (qp.getHang() - 1) + align, size * (qp.getLie() - 1) + align,
				(qp.getHang() - 1) * size + align);
		g.drawLine(align, align, align, (qp.getHang() - 1) * size + align);
		g.drawLine(align + size * (qp.getLie() - 1), align, align + size * (qp.getLie() - 1),
				(qp.getHang() - 1) * size + align);

		for (int i = 0; i < qp.getHang(); i++) {
			for (int j = 0; j < qp.getLie(); j++) {
				// System.out.println(j + " " + i);扫描行列
				if (qp.getContent()[i][j] == '黑') {
					// g.setColor(Color.black);
					// g.fillOval(align + size * i - 18, align + size * j - 18,
					// 36, 36);
					black.paintIcon(this, g, align + size * i - 15, align + size * j - 15);
				}
				if (qp.getContent()[i][j] == '白') {
					// g.setColor(Color.white);
					// g.fillOval(align + size * i - 18, align + size * j - 18,
					// 36, 36);
					white.paintIcon(this, g, align + size * i - 15, align + size * j - 15);
				}

			}
		}

	}

}
