package com.dhimantalapatra.app.notification.desktop;

public class NotificationMessage {

    private String msgId;
    private String consumerId;
    private String action1Url;
    private String action2Url;
    private String information;

    public NotificationMessage(String msgId, String consumerId, String action1Url, String action2Url, String information) {
        this.msgId = msgId;
        this.consumerId = consumerId;
        this.action1Url = action1Url;
        this.action2Url = action2Url;
        this.information = information;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public String getAction1Url() {
        return action1Url;
    }

    public String getAction2Url() {
        return action2Url;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "msgId='" + msgId + '\'' +
                ", consumerId='" + consumerId + '\'' +
                ", action1Url='" + action1Url + '\'' +
                ", action2Url='" + action2Url + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
