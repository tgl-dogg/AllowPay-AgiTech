package br.com.allowpay.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.spring.cache.HazelcastCacheManager;

import br.com.allowpay.canonical.Balance;
import br.com.allowpay.listener.BalanceNotificationEventListener;

@Configuration
@EnableCaching
public class HazelcastConfiguration {

	@Autowired
	private BalanceNotificationEventListener balanceNotificationEventListener;
	
	@Bean
	public CacheManager cacheManager() {
		  return new HazelcastCacheManager(hazelcastInstance());
	}

	@Bean(name="hazelcastInstance", destroyMethod = "shutdown")
	public HazelcastInstance hazelcastInstance() {
		HazelcastInstance hazelcastInstance = Hazelcast.getOrCreateHazelcastInstance(config());
		return hazelcastInstance;
	}

	@Bean(name="balanceNotificationTopic")
	public ITopic<Balance> balanceNotificationTopic() {
		final ITopic<Balance> balanceNotificationTopic = hazelcastInstance().getTopic("balanceNotificationTopic");
		balanceNotificationTopic.addMessageListener(balanceNotificationEventListener);
		return balanceNotificationTopic;
	}
	
	private Config config() {
		final Config config = new Config("alllowpay");
		config.addTopicConfig(balanceNotificationTopicConfig());

		return config;
	}

	private TopicConfig balanceNotificationTopicConfig() {
		final TopicConfig topicConfig = new TopicConfig("balanceNotificationTopicConfig");
		topicConfig.addMessageListenerConfig(balanceNotificationListenerConfig());
		return topicConfig;
	}

	private ListenerConfig balanceNotificationListenerConfig() {
		final ListenerConfig listenerConfig = new ListenerConfig(balanceNotificationEventListener);
		return listenerConfig;
	}
}
