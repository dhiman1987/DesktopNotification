package com.dhimantalapatra.app.notification.desktop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ButtonActionListener implements ActionListener {

    public static final int ACTION_ONE = 1;
    public static final int ACTION_TWO = 2;

    private int action = 0;
    private final NotificationGUI notificationGUI;
    private final NotificationMessage notificationMessage;
    public ButtonActionListener(int action,
                                NotificationGUI notificationGUI,
                                NotificationMessage notificationMessage) throws IllegalAccessException {
        this.notificationMessage = notificationMessage;
        if(action != 1 && action != 2){
            throw new IllegalAccessException("Action not supported");
        }
        this.action = action;
        this.notificationGUI = notificationGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        URI actionUri = null;
        try {
            if(action == ACTION_ONE){
              System.out.println("Action "+notificationMessage.getAction1Url()+" will be executed");
                actionUri =  new URI(notificationMessage.getAction1Url());
            } else if(action == ACTION_TWO){
              System.out.println("Action "+notificationMessage.getAction2Url()+" will be executed");
                actionUri =  new URI(notificationMessage.getAction2Url());
            }
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(actionUri);
            }
            notificationGUI.removeNotification(notificationMessage.getMsgId());
        } catch (URISyntaxException | IOException ex ) {
            ex.printStackTrace();
        }

    }

}
