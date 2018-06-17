package Clock;

import static Clock.Alignment.*;
import static java.lang.Math.*;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Clock extends Applet implements ActionListener, Runnable {
	Button AC;
	Button DC;
	Button AD;
	int status = 0;

	public static final int R = 200;
	public static final int CX = 350;
	public static final int CY = 300;

	public Clock() {
		this.setLayout(null);

		AC = new Button("アナログ時計");
		AC.setBounds(100, 20, 100, 50);

		DC = new Button("デジタル時計");
		DC.setBounds(300, 20, 100, 50);

		AD = new Button("アナログ・デジタル時計");
		AD.setBounds(500, 20, 150, 50);

		AC.addActionListener(this);
		DC.addActionListener(this);
		AD.addActionListener(this);

		this.add(AC);
		this.add(DC);
		this.add(AD);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g);

		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		DateFormat format2 = new SimpleDateFormat("M月dd日(E曜日)");
		String now = format.format(new Date());
		String now2 = format2.format(new Date());

		// アナログ時計
		if (status == 1) {
			g2.setStroke(new BasicStroke(2.0f));
			for (int i = 1; i <= 12; i++) {
				Point p = arc(i, 12, R * 0.84);
				String s = "" + i;
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				g.drawString(s, center(s, p.x, g), middle(p.y, g));
			}

			Calendar c = Calendar.getInstance();

			// draw second
			int s = c.get(Calendar.SECOND);
			Point sp = arc(s, 60, R * 0.8);
			g.setColor(Color.blue);
			g.drawLine(CX, CY, sp.x, sp.y);

			// draw minute
			int m = c.get(Calendar.MINUTE);
			Point mp = arc((m * 60 + s) / 10, 60 * 60 / 10, R * 0.8);
			g.setColor(Color.black);
			g.drawLine(CX, CY, mp.x, mp.y);

			// draw hour
			int h = c.get(Calendar.HOUR);
			Point hp = arc((h * 60 + m) / 2, 12 * 60 / 2, R * 0.5);
			g.setColor(Color.red);
			g.drawLine(CX, CY, hp.x, hp.y);

			// draw dial
			for (int i = 0; i < 60; i++) {
				double rad = PI * (i - 15) / 30;
				int X1 = (int) (CX + R * Math.cos(rad));
				int Y1 = (int) (CY + R * Math.sin(rad));
				if (i % 5 == 0) {
					g.fillRect(X1, Y1, 0, 0);
					g.setColor(Color.red);
					g.drawLine(CX + (int) (180 * Math.cos(rad)), CY
							+ (int) (180 * Math.sin(rad)), X1, Y1);
				} else {
					g.fillRect(X1, Y1, 0, 0);
					g.setColor(Color.black);
					g.drawLine(CX + (int) (190 * Math.cos(rad)), CY
							+ (int) (190 * Math.sin(rad)), X1, Y1);
					g.setColor(Color.red);
					g.fillRoundRect(343, 294, 12, 12, 12, 12);
					g.setColor(Color.black);
					g.drawOval(CX - R, CY - R, 2 * R, 2 * R);
				}
			}
			// デジタル時計
		} else if (status == 2) {
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
			g.drawString(now, 230, 300);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			g.drawString(now2, 270, 325);

			// アナログ・デジタル時計
		} else if (status == 3) {
			g2.setStroke(new BasicStroke(2.0f));
			for (int i = 1; i <= 12; i++) {
				Point p = arc(i, 12, R * 0.84);
				String s = "" + i;
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				g.drawString(s, center(s, p.x, g), middle(p.y, g));
			}

			Calendar c = Calendar.getInstance();

			// draw second
			int s = c.get(Calendar.SECOND);
			Point sp = arc(s, 60, R * 0.8);
			g.setColor(Color.blue);
			g.drawLine(CX, CY, sp.x, sp.y);

			// draw minute
			int m = c.get(Calendar.MINUTE);
			Point mp = arc((m * 60 + s) / 10, 60 * 60 / 10, R * 0.8);
			g.setColor(Color.black);
			g.drawLine(CX, CY, mp.x, mp.y);

			// draw hour
			int h = c.get(Calendar.HOUR);
			Point hp = arc((h * 60 + m) / 2, 12 * 60 / 2, R * 0.5);
			g.setColor(Color.red);
			g.drawLine(CX, CY, hp.x, hp.y);

			// draw dial
			for (int i = 0; i < 60; i++) {
				double rad = PI * (i - 15) / 30;
				int X1 = (int) (CX + R * Math.cos(rad));
				int Y1 = (int) (CY + R * Math.sin(rad));
				if (i % 5 == 0) {
					g.fillRect(X1, Y1, 0, 0);
					g.setColor(Color.red);
					g.drawLine(CX + (int) (180 * Math.cos(rad)), CY
							+ (int) (180 * Math.sin(rad)), X1, Y1);
				} else {
					g.fillRect(X1, Y1, 0, 0);
					g.setColor(Color.black);
					g.drawLine(CX + (int) (190 * Math.cos(rad)), CY
							+ (int) (190 * Math.sin(rad)), X1, Y1);
					g.setColor(Color.red);
					g.fillRoundRect(343, 294, 12, 12, 12, 12);
					g.setColor(Color.black);
					g.drawOval(CX - R, CY - R, 2 * R, 2 * R);

					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
					g.drawString(now, 230, 560);
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
					g.drawString(now2, 270, 585);
				}
			}
		}
	}

	// n分周
	Point arc(int m, int n, double scale) {
		double radian = Math.toRadians(m * 360 / n - 90);
		int x = CX + (int) (Math.cos(radian) * scale);
		int y = CY + (int) (Math.sin(radian) * scale);
		return new Point(x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.AC) {
			status = 1;
		} else if (e.getSource() == this.DC) {
			status = 2;
		} else if (e.getSource() == this.AD) {
			status = 3;
		}
	}

	// RealTimeApplet
	public long speed = 1000; // ms

	Thread thread = null;

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public Thread getThread() {
		return thread;
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread = null;
		}
	}

	@Override
	public void run() {
		while (thread == Thread.currentThread()) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
}