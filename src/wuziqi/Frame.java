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
	JMenuBar bar;// ����
	JMenu menu1;
	JMenu menu2;
	JMenuItem item;
	JFrame frame = new JFrame();

	public Frame() {
		super();//���ø���
		this.setTitle("������");
		// ���ò˵���
		bar = new JMenuBar();
		menu1 = new JMenu("��ʼ");
		menu2 = new JMenu("����");
		JMenuItem item1 = new JMenuItem("���¿�ʼ");
		JMenuItem item2 = new JMenuItem("����");
		JMenuItem item3 = new JMenuItem("�˳�");
		JMenuItem item4 = new JMenuItem("����");
		JMenuItem item5 = new JMenuItem("������");
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

	ActionListener listen = new ActionListener() // �˵���Ϣ����
	{

		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem) e.getSource();
			String actionCommand = target.getActionCommand();
			if (actionCommand.equals("restart")) {
				JOptionPane.showMessageDialog(Frame.this, "ȷ��������ǰ�����", "�����˼Ŷ~", JOptionPane.OK_OPTION);
				frame=new Frame();// ʤ�����������
				repaint();
				lastqizi = null;
			} else if (actionCommand.equals("Undo")) { // �˳�
				if(cp.getShengfu() != null && cp.getShengfu().equals("���A����")) {
        			JOptionPane.showMessageDialog(null,"����Ѿ�����,���ܻ���!�����¿�ʼ�µ����!");
        			return;
                }
			}
			else if (actionCommand.equals("exit")) { // �˳�
				System.exit(1);
			} else if (actionCommand.equals("rule")) { // ����
				JOptionPane.showConfirmDialog(Frame.this, "1���޽��֣�" + "\n" + "   �ڰ�˫���������ӣ���һ�������������γ����������(���������)���ӵ�һ��Ϊʤ��"
						+ "\n" + "2���н��֣����߽��־��䣬���ֲ������ӣ�" + "\n" + "   �����޽��ֹ�������ʤ�����ǲ��ϲ���һЩ�������ƺ������е����ƣ���ƽ��ڰ�˫������ʽ��" + "\n"
						+ "   ������Ժ���ĸ��ֽ������γɡ�" + "\n" + "   ������Ҫ��Ϊ���¼��ࣺ" + "\n" + "   (1)�ڳ������֣�������������������ͬ�����ӡ�" + "\n"
						+ "   (2)���������֣��������ϵĻ�����" + "\n" + "   (3)�����Ľ��֣��������ϵ��ġ�" + "\n" + "   ��������Ժ�����Եģ�����û���κν��֡�",
						"����", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			} else if (actionCommand.equals("aboutme")) { // ��Ȩ�����
				JOptionPane
						.showConfirmDialog(Frame.this,
								"�Ͳ������� ����~~~/n",
								"������", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
		}

	};
}
