import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gui
{
	public static void main( String[] args)
	{
		JFrame frame = new JFrame("GUI");
		frame.add( new GuiComponent());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //to close when exit button is pressed
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			//set screen with given size
		frame.setVisible(true);  								 //to show the screen
	}
}

class GuiComponent extends JComponent
	implements MouseListener, ActionListener
{
	private static final long serialVersionUID = 3088121316923015183L;
	String msg,msg1,msg2;
	String[] hey= {"Draw Polygon","Triangle","Square","Pentagon","Hexagon"};
	int i=0,j=0,operation=0,n=0;
	int[] arrx=new int[3];
	int[] arry=new int[3];
	JButton button1,button2,button3,button4,button5,saveButton,openButton,triangle,square,rectangle,custom;
	JComboBox<String> cb=new JComboBox<String>(hey);
	
	private BufferedImage canvas;
	public GuiComponent()
	{
		msg = "Select Points : ";
		msg1 = "Enter the number of lines : ";
		msg2 = "The Draw Circle button has been pressed";
		//Image img = Toolkit.getDefaultToolkit().getImage("line3.png");
		button1 = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("line.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); //button for line
		button1.setBounds(0,0,20,20); 
		button1.setToolTipText("Draw Line");
		button3 = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("oval.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); //button for line
		button3.setBounds(30,0,20,20);
		button3.setToolTipText("Draw Circle");
		button2 = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("polygon.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); 
		button2.setBounds(60,0,20,20);
		button2.setToolTipText("Draw Polygon");
		
		triangle = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("triangle.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); 
		triangle.setBounds(80,0,20,20);
		triangle.setToolTipText("Draw Triangle");
		
		square = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("square.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); 
		square.setBounds(100,0,20,20);
		square.setToolTipText("Draw Square");
		
		rectangle = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("rectangle.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); 
		rectangle.setBounds(120,0,20,20);
		rectangle.setToolTipText("Draw Rectangle");
		
		custom = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("custom.png").getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH))); 
		custom.setBounds(140,0,20,20);
		custom.setToolTipText("Draw Custom");
		
		JTextArea tf1=new JTextArea("Enter First Coordinate : "); 
        tf1.setBounds(0,700,2000,50);  
        JTextArea tf2=new JTextArea();  
        tf2.setBounds(0,770,2000,50);
        openButton = new JButton("Open");
        openButton.setBounds(1320, 0, 100, 20);
        saveButton = new JButton("Save");
        saveButton.setBounds(1440, 0, 100, 20);
		add(button1);
		
		//add(button2);			Added the combo box instead of this
        add(button2);
		add(button3);
		add(saveButton);
		add(openButton);
		add(tf1);
		add(tf2);
		add(triangle);
		add(rectangle);
		add(square);
		add(custom);
		triangle.addActionListener(this);
		square.addActionListener(this);
		rectangle.addActionListener(this);
		custom.addActionListener(this);
		triangle.setVisible(false);
		rectangle.setVisible(false);
		square.setVisible(false);
		custom.setVisible(false);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button2.addMouseListener(this);
		button3.addActionListener(this);
		saveButton.addActionListener(this);
		openButton.addActionListener(this);
		cb.addActionListener(this);
		addMouseListener(this);
		canvas = new BufferedImage(1700,1000, BufferedImage.TYPE_INT_ARGB);
		fillCanvas(Color.BLACK);
	}
	
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas,null,null);
        //g.setColor(Color.BLUE);
        //g.fillOval(150, 50, 500, 100);
        //g.drawOval(150, 50, 500, 100);
    }
	
	public void fillCanvas(Color c)
	{
		int color = c.getRGB();
		for(int x=0;x<1700;x++)
		{
			for(int y=0;y<1000;y++)
			{
				canvas.setRGB(x, y, color);
			}
		}
		repaint();
	}
	public void actionPerformed( ActionEvent e )
	{
		if(e.getSource() == button1)
		{
			JOptionPane.showMessageDialog(null, msg);
			operation=1;
		}
		else if(e.getSource()==button2)
		{
			triangle.setVisible(true);
			rectangle.setVisible(true);
			square.setVisible(true);
			custom.setVisible(true);
		}
		else if(e.getSource()==triangle)
		{
			triangle.setVisible(false);
			rectangle.setVisible(false);
			square.setVisible(false);
			custom.setVisible(false);
			
			operation = 4;
		    JOptionPane.showMessageDialog(null,"Select 3 points");		    
		    
		}
		else if(e.getSource() == button3)
		{
			JOptionPane.showMessageDialog(null, msg2);
			operation=3;
		}
		
		else if(e.getSource() == cb)
		{
			String s = (String)cb.getSelectedItem();
			System.out.println(1);
			if(s.matches("Draw Polygon"))
			{
				System.out.println(2);
				String txt=JOptionPane.showInputDialog(null, msg1);
				if(txt==null)
					n=0;
				else
					n=Integer.parseInt(txt);
				operation=2;
				JOptionPane.showMessageDialog(null, msg);
			}
		}
		else if(e.getSource() == saveButton)
		{
			try {
			    File outputfile = new File("saved.png");
			    ImageIO.write(canvas, "png", outputfile);
			    JOptionPane.showMessageDialog(null,"SAVED");
			} catch (IOException io) {
			    JOptionPane.showMessageDialog(null, "Couldn't Save");
			}
		}
		else if(e.getSource() == openButton)
		{
			File f=null;
			try {
				f = new File("saved.png");
				canvas = ImageIO.read(f);
				JOptionPane.showMessageDialog(null,"OPENED");
				Graphics g = getGraphics();
		        g.drawImage(canvas, 0,0,null);
		        g.dispose();

			}catch(IOException io)
			{
				JOptionPane.showMessageDialog(null, "Couldn't open");
			}
		}
	}

	public void drawTriangle()			//only horizontal and vertical base at present
	{
		
	}
	public void drawLine(Color c,int x1, int y1, int x2, int y2)
	{
			int i,x,y,dx,dy,intchg,e,s1,s2,temp;
		  	int d = c.getRGB();
		    x=x1;
		    y=y1;
		    dx=Math.abs(x2-x1);
		    dy=Math.abs(y2-y1);
		    s1=sign(x2,x1);
		    s2=sign(y2,y1);
		    if(dy>dx)
		    {
		        temp=dx;
		        dx=dy;
		        dy=temp;
		        intchg=1;
		    }
		    else
		    {
		        intchg=0;
		    }
		    e=2*dy-dx;
		    for(i=1;i<=dx;i++)
		    {
		        canvas.setRGB(x,y,d);
		        while(e>0)
		        {
		            if(intchg==1)
		            {
		                x=x+s1;
		            }
		            else
		            {
		                y=y+s2;
		            }
		            e=e-2*dx;
		        }
		        if(intchg==1)
		        {
		            y=y+s2;
		        }
		        else
		        {
		            x=x+s1;
		        }
		        e=e+2*dy;
		    }
		    repaint();
	}
	
	private static int sign(int x,int y)
	{
	    if(x>y)
	    {
	        return 1;
	    }	
	    else if(x==y)
	    {
	        return 0;
	    }
	    else
	    {
	        return -1;
	    }
	}
	
	public void mouseClicked( MouseEvent e) {
		if(operation==1)
		{
			arrx[i]=e.getX();	
			arry[i]=e.getY();
			i++;
			if(i==2)
			{
				drawLine(Color.YELLOW,arrx[0],arry[0],arrx[1],arry[1]);
				i=0;
				operation=0;
			}
		}
		else if(operation==2)
		{
			if(j<n)
			{
				arrx[i]=e.getX();
				arry[i]=e.getY();
				if(i==0)
				{
					arrx[2]=arrx[0];
					arry[2]=arry[0];
				}
				i++;
				if(i==2)
				{
					drawLine(Color.YELLOW,arrx[0],arry[0],arrx[1],arry[1]);
					arrx[0]=arrx[1];
					arry[0]=arry[1];
					i=1;
				}
				j++;
			}
			if(j==n)
			{
				drawLine(Color.YELLOW,arrx[i],arry[i],arrx[2],arry[2]);
				j=0;
				i=0;
				operation=0;
			}
		}
		else if(operation == 4)		//for triangle
		{
			arrx[i]=e.getX();	
			arry[i]=e.getY();
			i++;
			if(i==2)
			{
				arry[i-1] = arry[i-2];
				drawLine(Color.YELLOW,arrx[0],arry[0],arrx[1],arry[1]);
//				operation=0;
			}
			if(i == 3)
			{
				if(arry[i-1] < arry[i-2])
				{
					arrx[i-1] = (arrx[i-2] + arrx[i-3])/2;
					arry[i-1] = (int) (arry[i-1] - Math.sqrt(3)/2*(arrx[i-2] - arrx[i-3]));
				}
				else
				{
					arrx[i-1] = (arrx[i-2] + arrx[i-3])/2;
					arry[i-1] = (int) (arry[i-1] + Math.sqrt(3)/2*(arrx[i-2] - arrx[i-3]));
				}
				i = 0;
				drawLine(Color.YELLOW,arrx[0],arry[0],arrx[2],arry[2]);
				drawLine(Color.YELLOW,arrx[1],arry[1],arrx[2],arry[2]);
				operation=0;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//To make other buttons visible when mouse enters polygon button
		if(e.getSource()==button2 || e.getSource()==square|| e.getSource()==triangle || e.getSource()==rectangle || e.getSource()==custom)
		{
			triangle.setVisible(true);
			rectangle.setVisible(true);
			square.setVisible(true);
			custom.setVisible(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button2 && e.getSource()==square &&  e.getSource()==triangle && e.getSource()==rectangle && e.getSource()==custom)
		{
			triangle.setVisible(false);
			rectangle.setVisible(false);
			square.setVisible(false);
			custom.setVisible(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
