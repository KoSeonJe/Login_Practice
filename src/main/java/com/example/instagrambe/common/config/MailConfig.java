package com.example.instagrambe.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Value("${mail.host}")
  private String host;

  @Value("${mail.port}")
  private int port;

  @Value("${mail.username}")
  private String username;

  @Value("${mail.password}")
  private String password;

  @Value("${mail.properties.mail.smtp.auth}")
  private boolean auth;

  @Value("${mail.properties.mail.smtp.starttls.enable}")
  private boolean starttlsEnable;

  @Value("${mail.properties.mail.smtp.starttls.required}")
  private boolean starttlsRequired;

  @Value("${mail.properties.mail.smtp.connection-timeout}")
  private int connectionTimeout;

  @Value("${mail.properties.mail.smtp.timeout}")
  private int timeout;

  @Value("${mail.properties.mail.smtp.write timeout}")
  private int writeTimeout;

  private static final String ENCODING_TYPE = "UTF-8";

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(host);
    mailSender.setPort(port);
    mailSender.setUsername(username);
    mailSender.setPassword(password);
    mailSender.setDefaultEncoding(ENCODING_TYPE);
    mailSender.setJavaMailProperties(getMailProperties());

    return mailSender;
  }

  private Properties getMailProperties() {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", auth);
    properties.put("mail.smtp.starttls.enable", starttlsEnable);
    properties.put("mail.smtp.starttls.required", starttlsRequired);
    properties.put("mail.smtp.connection-timeout", connectionTimeout);
    properties.put("mail.smtp.timeout", timeout);
    properties.put("mail.smtp.write timeout", writeTimeout);

    return properties;
  }
}