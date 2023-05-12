	import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;


class vars{
	static int bX = 0, bY=250, y = 0, y1 = 0, p = 0, p1 = 0, speed = 7; 
	static boolean pause = false;


	public static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}
}


public class Pong extends JFrame{
	public Pong(){

		Thread run = new Thread(new runs());
		run.start();

		setContentPane(new DrawPane());
		setSize(815, 540);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true); 
		setFocusable(true);



		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();


				if (vars.bX < 400) {

					if (keyChar == 'w') {
						vars.y -= 20;
					}
					else if (keyChar == 's') {
						vars.y += 20;
					}
				}

				else {
					if (keyChar == 'k') {
						vars.y1 += 20;
					}
					else if(keyChar == 'i') {
						vars.y1 -= 20;
					}
				}


			}



		});
	}






	class DrawPane extends JPanel {
		public void paint(Graphics g) {

			g.fillRect(0, 0, 800, 500); //Background

			g.setColor(Color.white); //Mid Line
			g.fillRect(397, 0, 6, 500);

			//Players
			g.fillRoundRect(0, vars.y,  5,  40,  10, 10);
			g.fillRoundRect(795, vars.y1,  5,  40,  10, 10);

			g.drawString("Points: "+vars.p1, 330,  20);
			g.drawString("Points: "+vars.p, 420,  20);

			if (vars.pause == false) {
				g.fillOval(vars.bX, vars.bY,  10, 10); //Ball
			}
			repaint();

		}
	}




	public static void main(String args[]) {
		new Pong();
	}
}

class runs implements Runnable{
	public void run() {
		int temp = 0, vert = (int) (Math.random()*3);
		Random rand = new Random();
		
		while (1 >0) {
			System.out.println(vars.bX + " " + temp + " " + vars.p + " " + vars.p1 + " " + vars.bY + " " + vars.speed);

			if (vars.bY < 10) {
				vert = vert*-1;
			}
			else if(vars.bY > 460) {
				vert = vert*-1;
			}
			vars.bY += vert;


			temp += 5 + vars.speed;
			if (temp <= -5) {
				vars.bX -= 5+vars.speed;
			}
			else if (temp <= 825){
				vars.bX += 5+vars.speed;
			}


			if (vars.bX >= 780+vars.speed && vars.bY >= vars.y1 && vars.bY <= vars.y1+40) {
				vert = rand.nextInt(-4, 4);
				temp = -815;
				switch (vars.speed) {
				case 10:
					break;
				default:
					vars.speed+=1;
					break;
				}
				
			}
			else if (vars.bX <= 5+vars.speed && vars.bY >= vars.y && vars.bY <= vars.y+40) {
				vert = rand.nextInt(-4, 4);
				temp = 10;
				switch (vars.speed) {
				case 10:
					break;
				default:
					vars.speed+=1;
					break;
				}
			}

			if(vars.bX >= 800) {
				vars.p1++;
				vars.pause = true;
				vars.sleep(1000);
				vars.pause = false;
				temp = -850;
				vars.bX = 780;
				vert = (int) (Math.random()*3);
				vars.speed = 0;
			}
			else if(vars.bX < 0) {
				vars.p++;
				vars.pause = true;
				vars.sleep(1000);
				vars.pause = false;
				temp = 10;
				vars.bX = 20;
				vert = (int) (Math.random()*3);
				vars.speed = 0;
			}


			vars.sleep(50);
		}
	}
}
