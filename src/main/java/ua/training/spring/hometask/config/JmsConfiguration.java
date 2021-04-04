package ua.training.spring.hometask.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import ua.training.spring.hometask.jms.receiver.EventMessageListener;
import ua.training.spring.hometask.jms.receiver.UserMessageListener;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableJms
public class JmsConfiguration {

    private static final String USER_QUEUE_NAME = "user-queue";
    private static final String USER_TOPIC_EXCHANGE_NAME = "user-exchange";
    private static final String EVENT_QUE_NAME = "event-queue";
    private static final String EVENT_TOPICEXCHANGE_NAME = "event-exchange";
    private static final String ROUTING_KEY = "foo.bar.#";

    @Bean
    Queue userQueue() {
        return new Queue(USER_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(USER_TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding userBinding() {
        return BindingBuilder.bind(userQueue()).to(exchange()).with(ROUTING_KEY);
    }


    @Bean
    Queue eventQueue() {
        return new Queue(EVENT_QUE_NAME, false);
    }

    @Bean
    TopicExchange eventExchange() {
        return new TopicExchange(EVENT_TOPICEXCHANGE_NAME);
    }


    @Bean
    Binding eventBinding() {
        return BindingBuilder.bind(eventQueue()).to(eventExchange()).with(ROUTING_KEY);
    }


    @Bean
    public ConnectionFactory connectionFactory() throws URISyntaxException {
        String uri = "amqp://guest:guest@localhost";
        URI url = new URI(uri);

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(url.getHost());
        connectionFactory.setUsername(url.getUserInfo().split(":")[0]);
        connectionFactory.setPassword(url.getUserInfo().split(":")[1]);
        if (StringUtils.isNotBlank(url.getPath())) {
            connectionFactory.setVirtualHost(url.getPath().replace("/", ""));
        }
        connectionFactory.setConnectionTimeout(3000);
        connectionFactory.setRequestedHeartBeat(30);

        return connectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer userContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(USER_QUEUE_NAME);
        container.setMessageListener(userListenerAdapter());

        return container;
    }

    @Bean
    SimpleMessageListenerContainer eventContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(EVENT_QUE_NAME);
        container.setMessageListener(eventListenerAdapter());

        return container;
    }

    @Bean
    MessageListenerAdapter userListenerAdapter() {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(userMessageListener());
        messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter());

        return messageListenerAdapter;
    }

    @Bean
    MessageListenerAdapter eventListenerAdapter() {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(eventMessageListener());
        messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter());

        return messageListenerAdapter;
    }

    @Bean
    public MessageListener userMessageListener() {
        return new UserMessageListener();
    }

    @Bean
    public MessageListener eventMessageListener() {
        return new EventMessageListener();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws URISyntaxException {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(jackson2JsonMessageConverter());

        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(mapper);
        return jackson2JsonMessageConverter;
    }
}
