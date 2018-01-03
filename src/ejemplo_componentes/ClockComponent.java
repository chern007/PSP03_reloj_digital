package ejemplo_componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockComponent extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6463299937922430098L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = WIDTH;
	private static final int SAFE_ZONE = 5;

	private BufferedImage image;

	public ClockComponent() {
		super();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		paintClockFace();
		repaint();

		new Timer(1000, this).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		paintClockFace();
		repaint();
	}

	private void paintClockFace() {
		Graphics g = image.getGraphics();

		// enable anti-aliasing to stop it looking crap
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// clear previous drawings
		g.clearRect(0, 0, WIDTH, HEIGHT);

		// fill background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// draw clock border
		g.setColor(Color.BLACK);
		g.drawOval(SAFE_ZONE, SAFE_ZONE, WIDTH - (SAFE_ZONE * 2), HEIGHT - (SAFE_ZONE * 2));

		// retrieve time
		int[] time = getTime();

		// draw hour hand
		int hourAngle = (int) (0.5 * (60 * time[0] + time[1]));
		int[] hourPoint = getPoint(hourAngle, WIDTH / 2 + SAFE_ZONE, HEIGHT / 2 + SAFE_ZONE, WIDTH / 2
				- (SAFE_ZONE * 2));
		g.drawLine(WIDTH / 2 + SAFE_ZONE, HEIGHT / 2 + SAFE_ZONE, hourPoint[0], hourPoint[1]);

		// draw the minute hand
		int minuteAngle = (int) (6 * time[1]);
		int[] minutePoint = getPoint(minuteAngle, WIDTH / 2 + SAFE_ZONE, HEIGHT / 2 + SAFE_ZONE, WIDTH / 2
				- (SAFE_ZONE * 2) - 30);
		g.drawLine(WIDTH / 2 + SAFE_ZONE, HEIGHT / 2 + SAFE_ZONE, minutePoint[0], minutePoint[1]);

		// draw the second hand
		int secondAngle = (int) (6 * time[2]);
		int[] secondPoint = getPoint(secondAngle, WIDTH / 2 + SAFE_ZONE, HEIGHT / 2 + SAFE_ZONE, WIDTH / 2
				- (SAFE_ZONE * 2) - 120);
		g.drawLine(WIDTH / 2 + SAFE_ZONE, HEIGHT / 2 + SAFE_ZONE, secondPoint[0], secondPoint[1]);
	}

	private int[] getPoint(int angle, int x, int y, int radius) {
		int[] ret = new int[2];

		angle += 90 * 3;

		ret[0] = (int) (x + radius * Math.cos(Math.toRadians(angle)));
		ret[1] = (int) (y + radius * Math.sin(Math.toRadians(angle)));

		return ret;
	}

	private int[] getTime() {
		int[] ret = new int[3];

		Calendar now = Calendar.getInstance();
		ret[0] = now.get(Calendar.HOUR);
		ret[1] = now.get(Calendar.MINUTE);
		ret[2] = now.get(Calendar.SECOND);

		return ret;
	}
}
