package com.telegram.app;

import java.util.*;

public class Message{

  public String   originalSender;
  public String   messageContent;
  public Date     timestamp;

  public void Message(String originalSender, String message){
    this.originalSender = originalSender;
    this.messageContent = message;
    this.timestamp = new Date();
  }

}
