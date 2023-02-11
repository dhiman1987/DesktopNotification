package com.dhimantalapatra.app.notification.desktop;

import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpoint;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class CustomJmsListenerContainerFactory extends DefaultJmsListenerContainerFactory {

    private final String selector;
    public CustomJmsListenerContainerFactory(String jmsSelector) {
        this.selector = jmsSelector;
    }
    @Override
    public DefaultMessageListenerContainer createListenerContainer(JmsListenerEndpoint endpoint) {
        final DefaultMessageListenerContainer instance = super.createListenerContainer(endpoint);
        instance.setMessageSelector(selector);
        return instance;
    }
}