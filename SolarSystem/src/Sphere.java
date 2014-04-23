import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Sphere extends JApplet {

	public Sphere(){
		add( new SphereControl());

		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame  frame = new JFrame();
		Sphere applet=new Sphere();
		frame.add(applet);
		
		frame.setTitle("Sphere");
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}

class SphereControl extends JPanel{
	private Sun sun=new Sun();
	private JButton addSpeed =new JButton("Add Speed");
	private JButton slowSpeed = new JButton("Slow Speed");
	SphereControl(){
		setLayout(new BorderLayout());
	    JPanel panel=new JPanel();
	    panel.add(addSpeed);
	    panel.add(slowSpeed);
	    add(panel,BorderLayout.SOUTH);
	    add(sun,BorderLayout.CENTER);
	    
	    addSpeed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sun.addSpeed();
			}
	    	
	    });
	    
	    slowSpeed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sun.slowSpeed();
			}
	    	
	    });
	}
}
class Sun extends JPanel{
	public double a=0;
	public double b =0;
	private double k =4;
	Timer timer =new Timer(50, new TimerListener());
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	}
	public Sun(){
		timer.start();
	}
	public void timeStart(){
		timer.start();
	}
	public void slowSpeed(){
		k = k/1.1;
		changeAngle(k);
	}
	
	public void addSpeed(){
		k =k*1.1;
		changeAngle(k);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(430, 250, 40, 40);
		g.setColor(Color.BLUE);
		g.fillOval((int)(230*Math.sin(a))+305,(int)(101*Math.cos(a))+265, 15, 15);
		g.setColor(Color.BLACK);
	//	g.drawOval((int)(230*Math.sin(a))+290, (int)(101*Math.cos(a))+25, 45, 45);
		g.fillOval((int)(32*Math.sin(b))+(int)(230*Math.sin(a))+305,(int) (20*Math.cos(b))+(int) (101*Math.cos(a))+265, 10, 10);
		g.drawOval(90, 168, 460,205);
		changeAngle(k);
	}
	public void changeAngle(double j){
		a = a+j*0.005;
		b = b+j*0.06;
	}
}

/*	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
	Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
	                              middle.y - (frame.getHeight() / 2));
	frame.setLocation(newLocation);*/

