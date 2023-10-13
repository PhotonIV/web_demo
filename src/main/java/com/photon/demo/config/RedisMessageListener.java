package com.photon.demo.config;

/**
 * @author liuzaoxin
 * @description redis监听配置
 * @date 2023/05/31/ 13:51
 */
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

//@Component
public class RedisMessageListener {

    private final RedisMessageSubscriber messageSubscriber;

    public RedisMessageListener(RedisMessageSubscriber messageSubscriber) {
        this.messageSubscriber = messageSubscriber;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(messageSubscriber, "handleMessage");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter, new ChannelTopic("jslPushDadaChannel"));
        return container;
    }
}
