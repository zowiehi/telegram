package com.telegram.app;

import java.net.Socket;

public class Node{

  Socket sock;
  String name;
  Node[] connections;

  private boolean connect(Node){
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
