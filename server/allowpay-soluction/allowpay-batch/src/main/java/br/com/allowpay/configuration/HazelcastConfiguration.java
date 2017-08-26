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
import br.com.allowpay.canonical.ClientChild;
import br.com.allowpay.canonical.ClientDad;
import br.com.allowpay.canonical.Register;
import br.com.allowpay.listener.BalanceNotificationEventListener;
import br.com.allowpay.listener.CardRegisterNotificationEventListerner;
import br.com.allowpay.listener.ChildNotificationEventListener;
import br.com.allowpay.listener.DadNotificationEventListener;

@Configuration
@EnableCaching
public class HazelcastConfiguration {

	@Autowired
	private BalanceNotificationEventListener balanceNotificationEventListener;

	@Autowired
	private CardRegisterNotificationEventListerner cardRegisterNotificationEventListerner;

	@Autowired
	private DadNotificationEventListener dadNotificationEventListener;

	@Autowired
	private ChildNotificationEventListener childNotificationEventListener;

	@Bean
	public CacheManager cacheManager() {
		return new HazelcastCacheManager(hazelcastInstance());
	}

	@Bean(name = "hazelcastInstance", destroyMethod = "shutdown")
	public HazelcastInstance hazelcastInstance() {
		HazelcastInstance hazelcastInstance = Hazelcast.getOrCreateHazelcastInstance(config());
		return hazelcastInstance;
	}

	@Bean(name = "balanceNotificationTopic")
	public ITopic<Balance> balanceNotificationTopic() {
		final ITopic<Balance> balanceNotificationTopic = hazelcastInstance().getTopic("balanceNotificationTopic");
		balanceNotificationTopic.addMessageListener(balanceNotificationEventListener);
		return balanceNotificationTopic;
	}

	@Bean(name = "registerNotificationTopic")
	public ITopic<Register> registerNotificationTopic() {
		final ITopic<Register> registerNotificationTopic = hazelcastInstance().getTopic("registerNotificationTopic");
		registerNotificationTopic.addMessageListener(cardRegisterNotificationEventListerner);
		return registerNotificationTopic;
	}

	@Bean(name = "dadNotificationTopic")
	public ITopic<ClientDad> dadNotificationTopic() {
		final ITopic<ClientDad> dadNotificationTopic = hazelcastInstance().getTopic("dadNotificationTopic");
		dadNotificationTopic.addMessageListener(dadNotificationEventListener);
		return dadNotificationTopic;
	}

	@Bean(name = "childNotificationTopic")
	public ITopic<ClientChild> childNotificationTopic() {
		final ITopic<ClientChild> childNotificationTopic = hazelcastInstance().getTopic("childNotificationTopic");
		childNotificationTopic.addMessageListener(childNotificationEventListener);
		return childNotificationTopic;
	}

	private Config config() {
		final Config config = new Config("alllowpay");
		config.addTopicConfig(balanceNotificationTopicConfig());
		config.addTopicConfig(registerNotificationTopicConfig());
		config.addTopicConfig(dadNotificationTopicConfig());
		config.addTopicConfig(childNotificationTopicConfig());

		return config;
	}

	private TopicConfig balanceNotificationTopicConfig() {
		final TopicConfig topicConfig = new TopicConfig("balanceNotificationTopicConfig");
		topicConfig.addMessageListenerConfig(balanceNotificationListenerConfig());
		return topicConfig;
	}

	private TopicConfig registerNotificationTopicConfig() {
		final TopicConfig topicConfig = new TopicConfig("registerNotificationTopicConfig");
		topicConfig.addMessageListenerConfig(registerNotificationListenerConfig());
		return topicConfig;
	}

	private TopicConfig dadNotificationTopicConfig() {
		final TopicConfig topicConfig = new TopicConfig("dadNotificationTopicConfig");
		topicConfig.addMessageListenerConfig(dadNotificationListenerConfig());
		return topicConfig;
	}

	private TopicConfig childNotificationTopicConfig() {
		final TopicConfig topicConfig = new TopicConfig("childNotificationTopicConfig");
		topicConfig.addMessageListenerConfig(childNotificationListenerConfig());
		return topicConfig;
	}

	private ListenerConfig balanceNotificationListenerConfig() {
		final ListenerConfig listenerConfig = new ListenerConfig(balanceNotificationEventListener);
		return listenerConfig;
	}

	private ListenerConfig registerNotificationListenerConfig() {
		final ListenerConfig listenerConfig = new ListenerConfig(cardRegisterNotificationEventListerner);
		return listenerConfig;
	}

	private ListenerConfig dadNotificationListenerConfig() {
		final ListenerConfig listenerConfig = new ListenerConfig(dadNotificationEventListener);
		return listenerConfig;
	}

	private ListenerConfig childNotificationListenerConfig() {
		final ListenerConfig listenerConfig = new ListenerConfig(childNotificationEventListener);
		return listenerConfig;
	}
}