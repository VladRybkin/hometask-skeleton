package ua.training.spring.hometask.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import ua.training.spring.hometask.jms.receiver.MyJmsReceiver;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableJms
public class JmsConfiguration {

    private static final String topicExchangeName = "spring-boot-exchange";

    private static final String queueName = "spring-boot";


    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    public ConnectionFactory connectionFactory() throws URISyntaxException {
        String uri = "amqp://guest:guest@localhost";
        URI url = new URI(uri);

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(url.getHost());
        connectionFactory.setUsername(url.getUserInfo().split(":")[0]);
        connectionFactory.setPassword(url.getUserInfo().split(":")[1]);
        if (StringUtils.isNotBlank(url.getPath()))
            connectionFactory.setVirtualHost(url.getPath().replace("/", ""));
        connectionFactory.setConnectionTimeout(3000);
        connectionFactory.setRequestedHeartBeat(30);

        return connectionFactory;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MyJmsReceiver receiver) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver);
        messageListenerAdapter.setDefaultListenerMethod("receiveMessage");

        return messageListenerAdapter;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);

        return container;
    }

}
