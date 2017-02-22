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
  public InetAddress        localAddr;


  public Node(int port) {
    this.name = "Anonymous";
    try {
      this.serve = new ServerSocket(port);
      this.localAddr = this.serve.getInetAddress();
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
      this.listener.messageReceived(new Message(null, "Connected to " + addr + ":"+ port, "connected"));
    } catch (UnknownHostException uhe) {
      this.listener.messageReceived(new Message(null, "Unable to connect to: " + uhe.getMessage(), "err"));
    } catch (IOException ioe) {
      this.listener.messageReceived(new Message(null, "Error: " + ioe.getMessage(), "err"));
    }
  }

  public void newThread(Socket socket) {
    connections[connectionCount] = new NodeThread(this, socket, connectionCount);
    try {
      connections[connectionCount].open();
      connections[connectionCount].start();
      connectionCount++;
    } catch (IOException ioe) {
      this.listener.messageReceived(new Message(null, "Error: " + ioe.getMessage(), "err"));
    }
    System.out.println("Node started: " + socket);
  }

  public void receiveMessage(int id, Message message) {
    System.out.println(message.messageContent);
    this.listener.messageReceived(message);
    int i = 0;
    while (i < connectionCount) {
      if (connections[i].id != id && connections[i] != null) {
        connections[i].sendMessage(message);
      }
      i++;
    }
  }

  public Message generateMessage(String input) {
    if(input.contains("/bye")){
      leave();
      return null;
    }
    else{
      Message newMessage = new Message(name, input, "chat");
      int i = 0;
      while (i < connectionCount) {
        if(connections[i] != null) connections[i++].sendMessage(newMessage);
      }
      return newMessage;
    }
  }

  public void swap(int id, String addr, String port){
    connections[id].stopit();
    connections[id] = null;
    if(!this.localAddr.toString().equals(addr)){
      try{
        Socket socket = new Socket(addr, Integer.parseInt(port));
        connections[id] = new NodeThread(this, socket, id);
        connections[id].open();
        connections[id].start();
      } catch (UnknownHostException uhe) {
        this.listener.messageReceived(new Message(null, "Unable to connect to: " + uhe.getMessage(), "err"));
      } catch (IOException ioe) {
        this.listener.messageReceived(new Message(null, "Error: " + ioe.getMessage(), "err"));
      }
    }
  }

  public void leave() {
    // getting the replacement node socket info
    NodeThread replacement = connections[0];
    Socket replacementSocket = replacement.getSocket();
    int replacementPort = replacementSocket.getPort();
    InetAddress replacementAddress = replacementSocket.getInetAddress();

    // creating message to send to connected nodes to know who to connect to
    String mess = replacementAddress.toString() + Character.toString('\u25CE') + Integer.toString(replacementPort);
    Message leaveMessage = new Message(name, mess, "leave");
    int i = 1;
    while (i < connectionCount){
      connections[i].sendMessage(leaveMessage);
      connections[i].stopit();
      connections[i++] = null;
    }
  }

}
