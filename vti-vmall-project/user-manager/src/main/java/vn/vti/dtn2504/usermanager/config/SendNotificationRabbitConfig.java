package vn.vti.dtn2504.usermanager.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendNotificationRabbitConfig {
    @Value("${queue.notification.routingKey}")
    private String routingKey;
    @Value("${queue.notification.queue}")
    private String queueName;
    @Value("${queue.notification.exchange")
    private String exchangeName;

    @Bean
    DirectExchange SendEmailDirectExchange() {return new  DirectExchange(exchangeName);}
    @Bean
    Queue SendEmailQueue() {return QueueBuilder.durable(queueName).build();}
    @Bean
    Binding bpEventdetailCmpBinding(Queue SendEmailQueue, DirectExchange SendEmailDirectExchange) {
        return BindingBuilder.bind(SendEmailQueue()).to(SendEmailDirectExchange).with(routingKey);
    }
}
