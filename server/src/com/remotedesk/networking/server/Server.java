package com.remotedesk.networking.server;
import com.remotedesk.networking.server.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.util.*;
public class Server
{
private int port;
private ServerSocket serverSocket;
public Server(ServerSocket socket,int port)
{
this.port=port;
try
{
this.serverSocket=socket;
startListening();
}catch(Exception ee)
{
////System.out.println("Unable to start server at port : "+port);
//System.exit(0);
return;
}
}
public void createServer()
{
try
{
Random random=new Random();
int ran=random.nextInt(60000)+55300;
serverSocket=new ServerSocket(ran);
this.port=ran;
}catch(Exception e)
{
createServer();
}
}
public int getPortNumber()
{
return this.port;
}
public void startListening()
{
Socket socket;
InputStream inputStream;
OutputStream outputStream;
QuitStatus quitStatus;
try
{
while(true)
{
socket=serverSocket.accept();
new RequestProcesser(serverSocket,socket);
}
//serverSocket.close();
}catch(Exception e)
{
//System.out.println(e.getMessage());
}
}
}
