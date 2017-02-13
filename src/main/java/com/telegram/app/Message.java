package com.telegram.app;

import java.util.*;

public class Message{

  public String   originalSender;
  public String   messageContent;
  public Date     timestamp;
  public String   author;

  public void Message(String author, String message){
    this.author = author;
    this.messageContent = message;
    this.timestamp = new Date();
  }

}
