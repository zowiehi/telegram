package com.telegram.app;

import java.net.*;
import java.io.*;

public class Node implements Runnable{

  private ServerSocket  serve;
  private Socket        sock;
  private Thread        thread;
  private String        name;
  private NodeThread    connections[];
  private int           connectionCount;


  public Node(int port){
    try {
      serve = new ServerSocket(port);
      start();
      System.out.println("Node started: " + serve)
    }
    catch(IOException ioe)
      {  System.out.println(ioe.getMessage());
      }
  }

  public void run(){
    while(thread){
      socket = serve.accept();;
      connections[connectionCount] = new NodeThread(this, socket);
    }
  }

  public void start(){
    if(!thread){
      thread = new Thread(this);
      thread.start()
    }
  }

  private boolean connect(Node node){
    // creates a connection to another node
  }

  private Message receiveMessage(){
    // receives messages from other nodes
  }

  private void sendMessage(){
    // create and send a message to connected nodes
  }

  private void leave(){
    // leaves the chat network
  }

}
