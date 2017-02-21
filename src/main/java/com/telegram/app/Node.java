package com.telegram.app;

import java.net.*;
import java.io.*;

public class Node implements Runnable {

  private ServerSocket      serve;
  private Thread            thread;
  private String            name;
  private NodeThread[]      connections = new NodeThread[20];
  private DataInputStream   messageIn  =  null;
  private DataOutputStream  messageOut = null;
  private int               connectionCount = 0;
  public GUI                listener;


  public Node(int port) {
    this.name = "Anonymous";
    try {
      this.serve = new ServerSocket(port);
      start();
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void run() {
    while (thread != null) {
      try {
        Socket socket = serve.accept();
        newThread(socket);
      } catch (IOException ioe) {
        System.out.println(ioe.getMessage());
      }
    }
  }

  public void start() {
    if (thread == null) {
      thread = new Thread(this);
      thread.start();
      System.out.println("Node started: " + serve);
    }
  }

  public void connect(String addr, int port) {
    try {
      Socket socket = new Socket(addr, port);
      newThread(socket);
    } catch (UnknownHostException uhe) {
      System.out.println(uhe.getMessage());
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    System.out.println("Connected to " + addr + ":"+ port);
  }

  public void newThread(Socket socket) {
    connections[connectionCount] = new NodeThread(this, socket, connectionCount);
    try {
      connections[connectionCount].open();
      connections[connectionCount].start();
      connectionCount++;
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    System.out.println("Node started: " + socket);
  }

  public void receiveMessage(int id, Message message) {
    this.listener.messageReceived(message);
    int i = 0;
    while (i < connectionCount) {
      if (connections[i].id != id) {
        connections[i].sendMessage(message);
      }
      i++;
    }
  }

  public void generateMessage(String input) {
    Message newMessage = new Message(name, input, "chat");
    int i = 0;
    while (i < connectionCount) {
      connections[i++].sendMessage(newMessage);
    }
  }

  private void leave() {
    // leaves the chat network
  }

}
