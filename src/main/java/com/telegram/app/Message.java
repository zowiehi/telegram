package com.telegram.app;

import java.util.*;

public class Message{

  public String   author;
  public String   messageContent;
  public Date     timestamp;
  public String   type;

  public Message(String author, String message, String type) {
    this.author = author;
    this.messageContent = message;
    this.type = type;
    this.timestamp = new Date();
  }

}
