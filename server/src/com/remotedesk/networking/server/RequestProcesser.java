package com.remotedesk.networking.server;
import com.remotedesk.networking.server.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
public class RequestProcesser extends Thread
{
private Socket socket;
private ServerSocket serverSocket;
public RequestProcesser(ServerSocket serverSocket,Socket socket)
{
this.socket=socket;
this.serverSocket=serverSocket;
start();
}
public void run()
{
OutputStream outputStream;
InputStream inputStream;
try
{
byte b[]=new byte[4];
outputStream=socket.getOutputStream();
inputStream=socket.getInputStream();
inputStream.read(b);
//System.out.println(Convert.byteToInt(b));
int ch;
ch=Convert.byteToInt(b);
if(ch==30) quit();
if(ch==40) sendSize(outputStream); 
if(ch==50) sendImage(outputStream);
if(ch==60) moveMouse(inputStream);
if(ch==70) mouseClicked(inputStream); 
if(ch==80) mousePressed(inputStream); 
if(ch==90) mouseReleased(inputStream); 
if (ch==100) mouseDragged(inputStream); 
if(ch==110) keyPressed(inputStream);
if(ch==120) keyReleased(inputStream);
socket.close();
}catch(Exception e)
{
//System.out.println(e.getMessage());
}
}
public void quit()
{
try
{
serverSocket.close();
//System.out.println("server close");
}catch(Exception e)
{
//System.out.println(e.getMessage());
}
return;
}
public void sendSize(OutputStream outputStream)
{
try
{
/*GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
int width = gd.getDisplayMode().getWidth();
int height = gd.getDisplayMode().getHeight();*/
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
int w = (int)screenSize.getWidth();
int h = (int)screenSize.getHeight();
/*
outputStream.write(Convert.intToByte(height));
outputStream.flush();
outputStream.write(Convert.intToByte(width));
outputStream.flush();*/
outputStream.write(Convert.intToByte(h));
outputStream.flush();
outputStream.write(Convert.intToByte(w));
outputStream.flush();
}catch(Exception e)
{
//System.out.println(e);
return;
}
}
public void sendImage(OutputStream outputStream)
{
try
{
Robot robot=new Robot();
Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
BufferedImage screenFullImage;
screenFullImage=robot.createScreenCapture(screenRect);
ImageIO.write(screenFullImage,"png",outputStream);
outputStream.flush();
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void moveMouse(InputStream inputStream)
{
try
{
////System.out.println("Mouse Move");
byte[] b=new byte[4];
inputStream.read(b);
int x=Convert.byteToInt(b);
inputStream.read(b);
int y=Convert.byteToInt(b);
////System.out.println("X : "+x+", y : "+y);
new Robot().mouseMove(x,y);
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void mouseClicked(InputStream inputStream)
{
try
{
////System.out.println("Mouse Clicked");
byte[] b=new byte[4];
inputStream.read(b);
int button=Convert.byteToInt(b);
new Robot().mousePress(InputEvent.getMaskForButton(button));
new Robot().mouseRelease(InputEvent.getMaskForButton(button));
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void mousePressed(InputStream inputStream)
{
try
{
////System.out.println("Mouse Pressed");
byte[] b=new byte[4];
inputStream.read(b);
int button=Convert.byteToInt(b);
new Robot().mousePress(InputEvent.getMaskForButton(button));
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void mouseReleased(InputStream inputStream)
{
try
{
////System.out.println("Mouse Released");
byte[] b=new byte[4];
inputStream.read(b);
int button=Convert.byteToInt(b);
new Robot().mouseRelease(InputEvent.getMaskForButton(button));
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void mouseDragged(InputStream inputStream)
{
try
{
////System.out.println("Mouse Dragged");
byte[] b=new byte[4];
inputStream.read(b);
int x=Convert.byteToInt(b);
inputStream.read(b);
int y=Convert.byteToInt(b);
new Robot().mouseMove(x,y);
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void keyPressed(InputStream inputStream)
{
try
{
////System.out.println("Key Pressed");
byte[] b=new byte[4];
inputStream.read(b);
int key=Convert.byteToInt(b);
new Robot().keyPress(key);
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
public void keyReleased(InputStream inputStream)
{
try
{
////System.out.println("Key Released");
byte[] b=new byte[4];
inputStream.read(b);
int key=Convert.byteToInt(b);
new Robot().setAutoDelay(50);
new Robot().keyRelease(key);
}catch(Exception e)
{

//System.out.println(e);
return;
}
}
}
