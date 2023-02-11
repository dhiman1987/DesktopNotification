package com.dhimantalapatra.app.notification.desktop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  @Autowired
  private NotificationGUI notificationGUI;

  //@JmsListener(destination = "messageServer", containerFactory = "myFactory", selector = "consumer = 'consumer1'")
  @JmsListener(destination = "messageServer", containerFactory = "myFactory")
  public void receiveMessage(String msgString) {
    try {
      Gson gson =  new Gson();

      NotificationMessage notificationMessage = gson.fromJson(msgString,NotificationMessage.class);
      System.out.println("Received <" +notificationMessage+ ">");
      notificationGUI.showNotification(notificationMessage);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

}