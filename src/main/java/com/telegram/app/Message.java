package com.telegram.app;

import java.util.*;

public class Message{

  public String   author;
  public String   messageContent;
  public Date     timestamp;

  public Message(String author, String message) {
    this.author = author;
    this.messageContent = message;
    this.timestamp = new Date();
  }

}
