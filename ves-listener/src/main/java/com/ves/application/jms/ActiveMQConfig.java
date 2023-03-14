
package com.ves.application.jms;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {

    @Value("${active-mq.broker-url}")
    private String brokerUrl;
    @Value("${active-mq.username}")
    private String userName;
    @Value("${active-mq.password}")
    private String password;

    @Bean
    public JmsTemplate jmsTemplate() {
        final JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(getConnectionFactory());
        return jmsTemplate;
    }

    private ConnectionFactory getConnectionFactory() {
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(userName);
        factory.setPassword(password);
        factory.setTrustedPackages(Arrays.asList("com.ves.application.jms.model"));
        return factory;
    }
}
