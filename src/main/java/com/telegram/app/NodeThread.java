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

  public run(){
    String message = "";
    while (message.isempty()){

    }
  }

  public void sendMessage(Message msg){
    String message = msg.originalSender + ''
  }

}
