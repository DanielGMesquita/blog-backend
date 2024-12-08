package com.danielmesquita.blogapi.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Value("${blog-api.rabbitmq.exchange}")
  String exchange;

  @Value("${blog-api.rabbitmq.queue}")
  String queue;

  @Value("${blog-api.rabbitmq.routingKey}")
  String routingKey;

  @Bean
  Queue queue() {
    return new Queue(queue, false);
  }

  @Bean
  DirectExchange directExchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding binding(Queue queue, DirectExchange directExchange) {
    return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jsonMessageConverter());
    return template;
  }
}
