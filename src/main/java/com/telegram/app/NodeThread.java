package com.telegram.app;

import java.net.*;
import java.io.*;


public class NodeThread extends Thread {
  private DataInputStream  messageIn  =  null;
  private DataOutputStream messageOut = null;
  private Node node;
  private Socket socket;
  public int id;


  public NodeThread(Node node, Socket socket, int id) {
    this.node = node;
    this.socket = socket;
    this.id = id;
    System.out.println("Thread started");
  }

  public void run() {
    while (true) {
      try {
        String msgText = messageIn.readUTF();
        Message message = parseMessage(msgText);
        node.receiveMessage(id, message);
      } catch (IOException ioe) {
        System.out.println(ioe.getMessage());
      }
    }
  }

  public void open() throws IOException {
    messageIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    messageOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
  }


  public void sendMessage(Message msg) {
    char filler = '\u25CE';
    String message = String.join(Character.toString(filler), msg.author, (msg.timestamp).toString(), Character.toString(filler), msg.messageContent);
    try {
      messageOut.writeUTF(message);
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
  }

  private Message parseMessage(String msg) {
    String[] split = msg.split("[\u25CE]");
    Message message = new Message(split[0], split[2]);
    return message;
  }

}
