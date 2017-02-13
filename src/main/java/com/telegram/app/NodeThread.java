package com.telegram.app;

import java.net.*;
import java.io.*;


public class NodeThread extends Thread
{
  private DataInputStream  messageIn  =  null;
  private DataOutputStream messageOut = null;
  private Node node;
  private Socket socket;


  public NodeThread(Node node, Socket socket){
    this.node = node;
    this.socket = socket;
  }

  public void run(){
    String message = "";
    while (message.isEmpty()){

    }
  }

  public void sendMessage(Message msg){
    char filler = '\u25CE';
    String message = String.join(Character.toString(filler), msg.originalSender, (msg.timestamp).toString(), msg.messageContent);
    // send the message via DataOutputStream
  }
}
