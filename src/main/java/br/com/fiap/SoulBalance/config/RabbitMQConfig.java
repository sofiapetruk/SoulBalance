package br.com.fiap.SoulBalance.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.transaction}")
    private String transactionExchange;

    @Value("${rabbitmq.queue.transaction}")
    private String transactionQueue;

    @Value("${rabbitmq.routingkey.transaction}")
    private String transactionRoutingKey;

    @Bean
    public Queue transactionQueue() {
        return new Queue(transactionQueue, true);
    }

    @Bean
    public DirectExchange transactionExchange() {
        return new DirectExchange(transactionExchange, true, false);
    }

    @Bean
    public Binding transactionBinding(Queue transactionQueue, DirectExchange transactionExchange) {
        return BindingBuilder.bind(transactionQueue)
                .to(transactionExchange)
                .with(transactionRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
