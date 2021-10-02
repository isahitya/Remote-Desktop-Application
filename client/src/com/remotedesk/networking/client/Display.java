package com.remotedesk.networking.client;
import com.remotedesk.networking.client.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
public class Display extends Canvas implements MouseMotionListener,MouseListener,KeyListener
{
private BufferedImage currentFrame;
private String server;
private int port;
private int height,remoteHeight;
private int width,remoteWidth;
public Display(String server,int port,Frame frame)
{
this.server=server;
this.port=port;
addMouseMotionListener(this);
addMouseListener(this);
addKeyListener(this);
setFocusTraversalKeysEnabled(false);
repaint();
}
public void setCanvasSize(int width,int height)
{
this.width=width;
this.height=height;
this.setSize(width,height);
}
public void setImageSize(int RemoteWidth,int RemoteHeight)
{
this.remoteWidth=RemoteWidth;
this.remoteHeight=RemoteHeight;
}
public Dimension getPreferredSize() 
{
return new Dimension(width,height);
}
public void paint(Graphics g) 
{
super.paint(g);
if (currentFrame != null) 
{
Graphics2D g2d = (Graphics2D) g.create();
int x = (getWidth() - currentFrame.getWidth(null)) / 2;
int y = (getHeight() - currentFrame.getHeight(null)) / 2;
g2d.drawImage(currentFrame, x, y, this);
}
}
public void drawAgain(BufferedImage img)
{
/*Image image=(Image)img;
Image newImage=image.getScaledInstance(width,height,Image.SCALE_DEFAULT);*/
BufferedImage onCanvas=new BufferedImage(width,height,img.getType());
Graphics2D g2d = onCanvas.createGraphics();
g2d.drawImage(img,0,0,width,height,null);
g2d.dispose();
currentFrame=onCanvas;
repaint();
}
public void mouseClicked(MouseEvent ev)
{
//System.out.println("Clicked");
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(70);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte(ev.getButton());
outputStream.write(b);
outputStream.flush();
}catch(Exception ee)
{
//System.out.println(ee.getMessage());
}
}
public void mousePressed(MouseEvent ev)
{
//System.out.println("PRESSED");
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(80);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte(ev.getButton());
outputStream.write(b);
outputStream.flush();
socket.close();
}catch(Exception ee)
{
//System.out.println(ee.getMessage());
}
}
public void mouseReleased(MouseEvent ev)
{
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(90);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte(ev.getButton());
outputStream.write(b);
outputStream.flush();
socket.close();
}catch(Exception ee)
{
//System.out.println(ee.getMessage());
}
}
public void mouseEntered(MouseEvent ev)
{
}
public void mouseExited(MouseEvent ev)
{
}
public void mouseDragged(MouseEvent ev)
{
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(60);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
//System.out.println(getWidth());
b=Convert.intToByte((int)(ev.getX()*((double)remoteWidth/(double)width)));
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte((int)((double)ev.getY()*((double)remoteHeight/(double)height)));
outputStream.write(b);
outputStream.flush();
}catch(Exception ee)
{
//System.out.println(ee.getMessage());
}
}
public void mouseMoved(MouseEvent ev)
{
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(60);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
//System.out.println(getWidth());
b=Convert.intToByte((int)(ev.getX()*((double)remoteWidth/(double)width)));
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte((int)((double)ev.getY()*((double)remoteHeight/(double)height)));
outputStream.write(b);
outputStream.flush();
}catch(Exception e)
{
//System.out.println("Error "+e.getMessage());
}
}
public void keyTyped(KeyEvent ev)
{
}
public void keyPressed(KeyEvent ev)
{
//System.out.println("Key PRESSED");
//System.out.println("key : "+ev.getKeyChar()+"Code : "+ev.getKeyCode());
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(110);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte(ev.getKeyCode());
outputStream.write(b);
outputStream.flush();
socket.close();
}catch(Exception ee)
{
//System.out.println(ee.getMessage());
}
}
public void keyReleased(KeyEvent ev)
{
//System.out.println("Key RELEASE");
//System.out.println("key : "+ev.getKeyChar()+"Code : "+ev.getKeyCode());
try
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
byte[] b=new byte[4];
b=Convert.intToByte(120);
socket=new Socket(server,port);
outputStream=socket.getOutputStream();
outputStream.write(b);
outputStream.flush();
b=Convert.intToByte(ev.getKeyCode());
outputStream.write(b);
outputStream.flush();
socket.close();
}catch(Exception ee)
{
//System.out.println(ee.getMessage());
}
}
}
