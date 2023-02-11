package com.dhimantalapatra.app.notification.desktop;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Component
public class NotificationGUI {
    private JFrame mainContainer = null;
    private final Map<String,JPanel> notificationPanels = new HashMap<>();
    private void showNotificationGUI(NotificationMessage notificationMessage) throws IllegalAccessException {

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#DDDDDD"));
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5, 5));
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2, 2));
        actionPanel.setLayout(new GridLayout(0,2));
        actionPanel.setOpaque(false);

        JButton action1Btn = new JButton("Action One");
        JButton action2Btn = new JButton("Action Two");

        action1Btn.addActionListener(new ButtonActionListener(
                ButtonActionListener.ACTION_ONE,this,notificationMessage));
        action2Btn.addActionListener(new ButtonActionListener(
                ButtonActionListener.ACTION_TWO,this,notificationMessage));

        JLabel infoLabel = new JLabel(notificationMessage.getInformation());
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(infoLabel);
        mainPanel.add(actionPanel);
        actionPanel.add(action1Btn);
        actionPanel.add(action2Btn);

        mainContainer.add(mainPanel);
        notificationPanels.put(notificationMessage.getMsgId(),mainPanel);
        resize();
    }

    private void setLocationToTopRight(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        int x = bounds.x + bounds.width - insets.right - frame.getWidth();
        int y = bounds.y + insets.bottom;
        frame.setLocation(x, y);
    }

    public void showNotification(NotificationMessage notificationMessage) throws IllegalAccessException {
        if(null == mainContainer){
            mainContainer = new JFrame();
            mainContainer.setTitle("Notification");
            mainContainer.setAlwaysOnTop( true );
            mainContainer.pack();
            mainContainer.setVisible(true);
            mainContainer.setLayout(new FlowLayout());
            mainContainer.setSize(280,100);
            //mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainContainer.setResizable(false);
            setLocationToTopRight(mainContainer);
        }
        if(!mainContainer.isVisible()){
            mainContainer.setVisible(true);
        }
        showNotificationGUI(notificationMessage);
    }

    public void removeNotification(String msgId){
        mainContainer.remove(notificationPanels.get(msgId));
        notificationPanels.remove(msgId);
        resize();
    }

    private void resize(){
        if(notificationPanels.size()>1){
            mainContainer.setSize(280,95*notificationPanels.size());
        } else {
            mainContainer.setSize(280,130);
        }
        System.out.println("Size "+notificationPanels.size());
        if(notificationPanels.size() == 0){
            mainContainer.setVisible(false);
            mainContainer = null;
        } else {
            System.out.println("re painting");
            mainContainer.repaint();
        }

    }

    public static void main(String[] args) {
        NotificationMessage notification1 = new NotificationMessage(UUID.randomUUID().toString(),"Dummy",
                "https://www.google.com","https://www.bing.com","Notification 1");
        NotificationMessage notification2 = new NotificationMessage(UUID.randomUUID().toString(),"Dummy",
                "https://www.google.com","https://www.bing.com","Notification 2");
        NotificationMessage notification3 = new NotificationMessage(UUID.randomUUID().toString(),"Dummy",
                "https://www.google.com","https://www.bing.com","Notification 3");
        try {
            NotificationGUI notificationGUI = new NotificationGUI();
            notificationGUI.showNotification(notification1);
            notificationGUI.showNotification(notification2);
            Thread.sleep(10000);
            notificationGUI.showNotification(notification3);

        } catch (IllegalAccessException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

