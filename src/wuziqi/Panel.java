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

//ʵ�ֽӿ�
public class Panel extends JPanel implements ActionListener {
	private qipan qp = new qipan();
	private qizi lastqizi;
	private Caipan cp = new Caipan();// ��Ա����Ӧ������г�ʼ������
	// private qizi xinqizi;
	int w, h;// ��͸ߣ�������Ϊ��λ��
	// int c, r;// //�к��У��е��������е�����������������ʾ��
	int align;// �߾�
	int size;// ������,������봰��ľ���
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;// ��Ļ�Ŀ�
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;// ��Ļ�ĳ�
	String[] choics = { "put.wav", "win.wav", "lost.wav" }; // �����ļ�������
	URL file1 = getClass().getResource(choics[0]); // ���������ļ�
	URL file2 = getClass().getResource(choics[1]); // ��ʤ�����ļ�
	URL file3 = getClass().getResource(choics[2]); // �������������ļ�
	AudioClip soundPut = java.applet.Applet.newAudioClip(file1); // ����������������
	AudioClip soundWin = java.applet.Applet.newAudioClip(file2); // ��ʤ������������
	AudioClip soundLost = java.applet.Applet.newAudioClip(file3); // ��ǰλ�ò�������������������
	ImageIcon background = new ImageIcon("src/wuziqi/wzq.jpg");// ����ͼƬ
	ImageIcon black = new ImageIcon("src/wuziqi/blackchess.GIF");// ����ͼƬ
	ImageIcon white = new ImageIcon("src/wuziqi/whitechess.GIF");// ����ͼƬ
	Image im = Toolkit.getDefaultToolkit().getImage("src/wuziqi/wzq.jpg");
	protected String message;

	public Panel(qipan qp, qizi lastqizi) {
		this.qp = qp;
		this.lastqizi = lastqizi;

	}

	public Panel()// �޲ι��캯��
	{
		this.w = 0;
		this.h = 0;
	}

	public Panel(int w, int h)// ֻ�п�͸����Ĺ��캯��
	{
		this.qp = new qipan(12, 12);
		this.lastqizi = null;

		this.w = w;
		this.h = h;

		// ���ͼƬ�ķ��� ���Ǳ���ͼƬ
		// JLabel lblImage = new JLabel(new ImageIcon("D:wzq.JPG"));
		// lblImage.setBounds(0, 0, w, h);
		// this.add(lblImage);
		// String path = "D:wzq.jpg";
		// ImageIcon background = new ImageIcon(path);
		// // �ѱ���ͼƬ��ʾ��һ����ǩ����
		// JLabel label = new JLabel(background);
		// // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		// label.setBounds(0, 0, this.getWidth(), this.getHeight());
		// this.add(label, new Integer(Integer.MIN_VALUE));
		this.setBackground(Color.BLUE);

		// ��Ӱ�ť
		JButton b1 = new JButton("���¿���");
		JButton b2 = new JButton("�˳�");
		this.add(b1);
		this.add(b2);
		this.setLayout(null);// Ҫ��ʹ�þ��Զ�λ ������ʹ��null���� ���ղ���
		b1.setBounds(100, 480, 100, 50);
		b2.setBounds(250, 480, 100, 50);
		b1.addActionListener(this);
		b2.addActionListener(this);
		message = "�ڷ�����";
		this.addMouseListener(new MouseAdapter() { // ���������¼�

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				int i = (x - align + size / 2) / size;
				int j = (y - align + size / 2) / size;
				System.out.println("�������Ϊ(" + e.getX() + "," + e.getY() + ")");
				System.out.println("i:" + i + "j:" + j);
				qizi xinqizi = null;
				if (lastqizi == null) {
					xinqizi = new qizi(new position(i, j), '��');
					message = "�ֵ��׷�";
					// qp.tianjiaqizi(xinqizi);
					soundPut.play();
				} else {
					if (lastqizi.getColor() == '��') {
						xinqizi = new qizi(new position(i, j), '��');
						cp.panduanJinshou(xinqizi, qp);
						if (cp.getJinshou() != null && cp.getJinshou().equals("��������")) {
							JOptionPane.showMessageDialog(Panel.this, "��ǰλ����������", "�뻻��λ������",
									JOptionPane.WARNING_MESSAGE);
							soundLost.play();
							xinqizi.setColor('��');
							cp.setJinshou(" ");// �ÿ�

						} // ���жϵ�ǰλ���Ƿ�������
						if ( cp.getJinshou().equals("��������")) {
							JOptionPane.showMessageDialog(Panel.this, "������������", "����", JOptionPane.WARNING_MESSAGE);
							cp.setJinshou(" ");// �ÿ�
							xinqizi = null;
						}
						if ( cp.getJinshou().equals("���Ľ���")) {
							JOptionPane.showMessageDialog(Panel.this, "�������Ľ���", "����", JOptionPane.WARNING_MESSAGE);
							cp.setJinshou(" ");// �ÿ�
							xinqizi = null;
						}//��Ϊ���壬�Ҵ��ڽ���λ�ã���ǰλ�ò�����
						// qp.tianjiaqizi(xinqizi);
						message = "�ֵ��׷�";
						soundPut.play();
					} else if (lastqizi.getColor() == '��') {
						xinqizi = new qizi(new position(i, j), '��');
						cp.panduanJinshou(xinqizi, qp);
						if (cp.getJinshou() != null && cp.getJinshou().equals("��������")) {
							JOptionPane.showMessageDialog(Panel.this, "��ǰλ����������", "�뻻��λ������",
									JOptionPane.WARNING_MESSAGE);
							soundLost.play();
							xinqizi.setColor('��');
							cp.setJinshou(" ");
						}

						// qp.tianjiaqizi(xinqizi);
						message = "�ֵ��ڷ�";
						soundPut.play();

					}

				}

				// lastqizi = new qizi(new position(i, j),
				// xinqizi.getColor());
				qp.tianjiaqizi(xinqizi);
				lastqizi = xinqizi;// ���ºõ����ӱ����һ�����ӡ�
				repaint();
				// if(cp.panduanShengfu(lastqizi, qp)==null)

				// System.out.println("�����strֵ��null");
				cp.panduanShengfu(lastqizi, qp);
				if (cp.getShengfu() != null && cp.getShengfu().equals("���A����")) {
					if (lastqizi.getColor() == '��') {
						JOptionPane.showMessageDialog(Panel.this, "����ʤ����! ! !");// ��ʾ�Ի���
						soundWin.play();
						cp.setShengfu(" ");
						// removeAll();������
						// repaint();
						qp = new qipan(12, 12);// ʤ�����������

						// qp.clear(9,9);
						repaint();
						lastqizi = null;

					} else if (lastqizi.getColor() == '��') {
						JOptionPane.showMessageDialog(Panel.this, "����ʤ����! ! !");
						soundWin.play();
						cp.setShengfu(" ");
						qp = new qipan(12, 12);// ʤ�����������

						// qp.clear(9,9);
						repaint();
						lastqizi = null;
					}
				}

			}

		});

	}

	// ��Ӱ�ť����¼�
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if (cmd.equals("���¿���")) {
			JOptionPane.showMessageDialog(Panel.this, "ȷ��������ǰ�����", "�����˼Ŷ~", JOptionPane.OK_OPTION);
			qp = new qipan(12, 12);// ʤ�����������
			repaint();
			lastqizi = null;

		}
		if (cmd.equals("�˳�")) {
			System.exit(0);
		}

	}

	// ��������
	public void putVoice() {
		soundPut.play();
	}

	// ��ʤ����
	public void winVoice() {
		soundWin.play();
	}

	// ��ǰλ�ò�����������
	public void lostVoice() {
		soundLost.play();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(im, 0, 0, null);
		g.setFont(new Font("����", Font.BOLD, 20));// ���á���Ϸ��Ϣ��������
		g.drawString("��Ϸ��Ϣ��  " + message, 150, 40);

		g.setFont(new Font("���Ʋ���", Font.PLAIN, 14));// ��Ϊ����������������
		g.drawString("�ڷ�ʱ�䣺������", 45, 470);
		g.drawString("�׷�ʱ�䣺������", 260, 470);
		align = 50;// ���̾��봰����������
		size = (w - 2 * align) / (qp.getLie() - 1);// ÿһ�е�ƽ�����

		for (int y = 1; y < qp.getHang() - 1; y++)// �����ߡ��������ܱ߽粻��
		{
			g.drawLine(0 + align, y * size + align, align + (qp.getLie() - 1) * size, y * size + align);
		}
		for (int x = 1; x < qp.getLie() - 1; x++)// �����ߡ��������ܱ߽粻��
		{
			g.drawLine(x * size + align, 0 + align, x * size + align, align + (qp.getHang() - 1) * size);
		}

		// �������ܱ���Ϊ����
		float lineWidth = 3.0f;
		((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
		// ���߽�����
		g.drawLine(align, align, size * (qp.getLie() - 1) + align, align);
		g.drawLine(align, size * (qp.getHang() - 1) + align, size * (qp.getLie() - 1) + align,
				(qp.getHang() - 1) * size + align);
		g.drawLine(align, align, align, (qp.getHang() - 1) * size + align);
		g.drawLine(align + size * (qp.getLie() - 1), align, align + size * (qp.getLie() - 1),
				(qp.getHang() - 1) * size + align);

		for (int i = 0; i < qp.getHang(); i++) {
			for (int j = 0; j < qp.getLie(); j++) {
				// System.out.println(j + " " + i);ɨ������
				if (qp.getContent()[i][j] == '��') {
					// g.setColor(Color.black);
					// g.fillOval(align + size * i - 18, align + size * j - 18,
					// 36, 36);
					black.paintIcon(this, g, align + size * i - 15, align + size * j - 15);
				}
				if (qp.getContent()[i][j] == '��') {
					// g.setColor(Color.white);
					// g.fillOval(align + size * i - 18, align + size * j - 18,
					// 36, 36);
					white.paintIcon(this, g, align + size * i - 15, align + size * j - 15);
				}

			}
		}

	}

}
