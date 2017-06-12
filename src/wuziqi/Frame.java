package wuziqi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Frame extends JFrame {
	private Panel aPanel;
	private qipan qp = new qipan();
	private qizi lastqizi;
	private Caipan cp=new Caipan();
	JMenuBar bar;// 声明
	JMenu menu1;
	JMenu menu2;
	JMenuItem item;
	JFrame frame = new JFrame();

	public Frame() {
		super();//调用父类
		this.setTitle("五子棋");
		// 设置菜单栏
		bar = new JMenuBar();
		menu1 = new JMenu("开始");
		menu2 = new JMenu("帮助");
		JMenuItem item1 = new JMenuItem("重新开始");
		JMenuItem item2 = new JMenuItem("悔棋");
		JMenuItem item3 = new JMenuItem("退出");
		JMenuItem item4 = new JMenuItem("规则");
		JMenuItem item5 = new JMenuItem("关于我");
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		bar.add(menu1);
		bar.add(menu2);
		this.setJMenuBar(bar);

		item1.setActionCommand("restart");
		item2.setActionCommand("undo");
		item3.setActionCommand("exit");
		item4.setActionCommand("rule");
		item5.setActionCommand("aboutme");
		item1.addActionListener(listen);
		item2.addActionListener(listen);
		item3.addActionListener(listen);
		item4.addActionListener(listen);
		item5.addActionListener(listen);
		Panel aPanel = new Panel(500, 500);
		this.getContentPane().add(aPanel);
		this.setSize(600, 600);
		frame.pack();
		this.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	ActionListener listen = new ActionListener() // 菜单消息处理
	{

		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem) e.getSource();
			String actionCommand = target.getActionCommand();
			if (actionCommand.equals("restart")) {
				JOptionPane.showMessageDialog(Frame.this, "确定放弃当前的棋局", "请君三思哦~", JOptionPane.OK_OPTION);
				frame=new Frame();// 胜利后清除棋子
				repaint();
				lastqizi = null;
			} else if (actionCommand.equals("Undo")) { // 退出
				if(cp.getShengfu() != null && cp.getShengfu().equals("你贏啦！")) {
        			JOptionPane.showMessageDialog(null,"棋局已经结束,不能悔棋!请重新开始新的棋局!");
        			return;
                }
			}
			else if (actionCommand.equals("exit")) { // 退出
				System.exit(1);
			} else if (actionCommand.equals("rule")) { // 规则
				JOptionPane.showConfirmDialog(Frame.this, "1、无禁手：" + "\n" + "   黑白双方依次落子，任一方先在棋盘上形成连续的五个(含五个以上)棋子的一方为胜。"
						+ "\n" + "2、有禁手：（走禁手就输，禁手不能落子）" + "\n" + "   鉴于无禁手规则黑棋必胜，人们不断采用一些方法限制黑棋先行的优势，以平衡黑白双方的形式。" + "\n"
						+ "   于是针对黑棋的各种禁手逐渐形成。" + "\n" + "   禁手主要分为以下几类：" + "\n" + "   (1)黑长连禁手：连成六个以上连续相同的棋子。" + "\n"
						+ "   (2)黑三三禁手：两个以上的活三。" + "\n" + "   (3)黑四四禁手：两个以上的四。" + "\n" + "   禁手是针对黑棋而言的，白棋没有任何禁手。",
						"规则", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			} else if (actionCommand.equals("aboutme")) { // 版权与帮助
				JOptionPane
						.showConfirmDialog(Frame.this,
								"就不告诉你 哈哈~~~/n",
								"关于我", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
		}

	};
}
