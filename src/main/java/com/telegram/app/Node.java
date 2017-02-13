package com.telegram.app;

import java.net.*;
import java.io.*;

public class Node implements Runnable{

  private ServerSocket  serve;
  private Thread        thread;
  private String        name;
  private NodeThread    connections[] = new NodeThread[20];
  private int           connectionCount = 0;


  public Node(int port){
    try {
      serve = new ServerSocket(port);
      start();
      System.out.println("Node started: " + serve);
    }
    catch(IOException ioe)
      {  System.out.println(ioe.getMessage());
      }
  }

  public void run(){
    while(thread != null){
      try {
      Socket socket = serve.accept();
      newThread(socket);
      }
      catch(IOException ioe)
        {  System.out.println(ioe.getMessage());
        }

    }
    return;
  }

  public void start(){
    if(thread == null){
      thread = new Thread(this);
      thread.start();
    }
  }

  public void connect(String addr, int port){
    try{
      Socket sock = new Socket(addr, port);
    }
    catch(UnknownHostException uhe){
      System.out.println(uhe.getMessage());
    }
    catch(IOException ioe)
      {
        System.out.println(ioe.getMessage());
      }
    return;

  }

  public void newThread(Socket socket){
    connections[connectionCount] = new NodeThread(this, socket);
    connectionCount++;
  }

  private void receiveMessage(){/*return value should be Message, just void to pass the linters*/
    return;
  }

  private void sendMessage(){
    // create and send a message to connected nodes
  }

  private void leave(){
    // leaves the chat network
  }

}
