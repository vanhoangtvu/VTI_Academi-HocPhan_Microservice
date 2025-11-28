package vn.vti.dtn2504.shipmentservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    @Value("${queue.shipment.queue}")
    private String queueName;
    @Value("${queue.shipment.exchange}")
    private String exchangeName;
    @Value("${queue.shipment.routingKey}")
    private String routingKey;
    
    @Bean
    public Queue shipmentQueue() {
        return new Queue(queueName, true);
    }
    @Bean
    public DirectExchange shipmentExchange() {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Binding binding(Queue shipmentQueue, DirectExchange shipmentExchange) {
        return BindingBuilder.bind(shipmentQueue).to(shipmentExchange).with(routingKey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
