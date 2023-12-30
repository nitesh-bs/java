package com.nitesh.springRedisMessagingPubSub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.nitesh.springRedisMessagingPubSub.subscriber.Receiver;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Bean
	public JedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("localhost");
		configuration.setPort(6379);
		return new JedisConnectionFactory(configuration);
	}
	

	

	@Bean
	public RedisTemplate<String, Object> template(){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}
	
	@Bean
	public ChannelTopic topic() {
		return new ChannelTopic("pubsub:nitesh-channel");
	}
	
	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
		return new MessageListenerAdapter(new Receiver());
	}
	
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.addMessageListener(messageListenerAdapter(), topic());
		return container;
	}
	
}