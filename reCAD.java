import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyPanel extends JPanel
{
	JButton b1,b2,b3,b4;
	JTextField tf1;
	TextArea ta;
	Polygon p= new Polygon();
	int i=1,j=0,n=4,cx=100,cy=150,r=40,iterator=0,ex=10,ey=40;
	int []x=new int[2];
	int []y= new int[2];
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.white);
		
		//JTextField tf1 = new JTextField("Number of Sides");
		//this.add(tf1);
		
			if(i%2==0)
			{
				if(j==1 && tf1.isVisible()==true && b4.isVisible()==true)
				{
				for(int i=0;i<n;i++)
				{
					p.addPoint((int)(cx+r*Math.cos(i*2*Math.PI/n)), (int)(cy+r*Math.sin(i*2*Math.PI/n)));
				}
				}
				//g.setXORMode(Color.green);
				g.drawPolygon(p);
				b1.setVisible(true);
				tf1.setVisible(false);
				b4.setVisible(false);
				//i=0;
			}
			if(i%3==0)
			{
				//g.setXORMode(Color.green);
				g.drawLine(cx,cy,ex,ey);
			}
			if(i%5==0)
			{
				//g.setXORMode(Color.green);
				g.drawOval(cx, cy, 100, 100);
			}
		//b1.addActionListener(actionListener);
		//this.add(b1);
	}
	public MyPanel()
	{
	addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e)
		{
				/*if(i%2==0)
				{
				x[0]=e.getX();	
				y[0]=e.getY();
				}
				else if(i%5==0)
				{
					x[1]=e.getX();	
					y[1]=e.getY();
				}
				else if(i%3==0)
				{*/
					cx=e.getX();	
					cy=e.getY();
				//}
				if(i%3!=0)
				repaint();
		}
		@Override
		public void mouseReleased(MouseEvent e)
		{
				ex=e.getX();	
				ey=e.getY();
				if(i%3==0)
				repaint();
		}
	});
	tf1 = new JTextField("Enter polygon sides you want and press Input");
	tf1.setBounds(0, 0, 100, 450);
	b1 = new JButton("Draw Polygon");
	b1.setBounds(0,0,100,200);
	b2 = new JButton("Draw Line");
	b2.setBounds(0,0,100,400);
	b3 = new JButton("Draw Ellipse");
	b3.setBounds(0,0,100,600);
	b4 = new JButton("Input");
	b4.setBounds(0,0,100,800);
	ActionListener al = new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
						if(e.getSource()==b1)
						{
							
							tf1.setVisible(true);
							b4.setVisible(true);
							b1.setVisible(false);
						}
						else if(e.getSource()==b2)
						{
							i*=3;
							j=2;
							JOptionPane.showMessageDialog(null,"Click.Drag.Release. Simple Nigga, Smart Nigga");
							//repaint();
						}
						else if(e.getSource()==b3)
						{
							i*=5;
							j=3;
							JOptionPane.showMessageDialog(null,"Black Man clicks. Ellipse appears magically!");
							//repaint();
						}
						else if(e.getSource()==b4)
						{
							i*=2;
							j=1;
							n=Integer.parseInt(tf1.getText());
							JOptionPane.showMessageDialog(null,"Niggh' should click where he wants his damn polygon");
							//repaint();
						}
				}
			};
	b1.addActionListener(al);
	this.add(b1);
	b2.addActionListener(al);
	this.add(b2);
	b3.addActionListener(al);
	this.add(b3);
	b4.addActionListener(al);
	this.add(b4);
	this.add(tf1);
	b4.setVisible(false);
	tf1.setVisible(false);
	JOptionPane.showMessageDialog(null,"Welcome to reCAD where every Niggh' can draw lines and shapes and enjoy living on Planet Earth!");
	}
	
	
}

class MyFrame extends JFrame
{
	public MyFrame()
	{
		setTitle("reCAD");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setSize (screenWidth/2,screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				}
				);
		Container contentPane = getContentPane();
		MyPanel p = new MyPanel();
		p.setBackground(Color.BLACK);
		contentPane.add(p);
		
	}
}

public class rohan
{
	public static void main(String[] args)
	{
		JFrame frame = new MyFrame();
		frame.setVisible(true);
	}
}

