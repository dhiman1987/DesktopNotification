package com.dhimantalapatra.app.notification.desktop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class DesktopNotificationApplication {
// mvn spring-boot:run -Dspring-boot.run.arguments="--consumer.id=consumer1"
	@Value("${consumer.id:XXX}")
	private String consumerId;
/*	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}*/

/*	@Bean
	public MessageConverter messageConverter() {
		final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}*/

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		if("XXX".equals(consumerId)){
			//System.out.println("user.name "+System.getProperty("user.name"));
			consumerId = System.getProperty("user.name");
		}
		System.out.println("consumerId "+consumerId);
		final String selector = "consumer = '"+consumerId+"'";
		final DefaultJmsListenerContainerFactory factory = new CustomJmsListenerContainerFactory(selector);
		//factory.setMessageConverter(messageConverter());
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DesktopNotificationApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

}
