package com.cn.template.xutil.jms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.cn.template.modules.test.category.UnStable;
import com.cn.template.modules.test.log.LogbackListAppender;
import com.cn.template.modules.test.spring.SpringContextTestCase;
import com.cn.template.web.listener.jms.NotifyMessageListener;
import com.cn.template.web.listener.jms.NotifyMessageProducer;
import com.cn.template.xutil.Threads;
import com.cn.template.xutil.enums.EventType;

/**
 * JMS异步消息处理测试.
 * @author Libra
 *
 */
@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class JmsSimpleTest extends SpringContextTestCase {

	@Autowired
	private NotifyMessageProducer notifyMessageProducer;

	@Test
	public void queueMessage() {
		Threads.sleep(1000);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(NotifyMessageListener.class);


		notifyMessageProducer.sendQueue(EventType.SUBSCRIBE.getValue(),"openid-12345");
		logger.info("sended message");

		Threads.sleep(1000);
		assertEquals("EventType:subscribe, OpenID:openid-12345", appender.getFirstMessage());
	}

	@Test
	public void topicMessage() {
		Threads.sleep(1000);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(NotifyMessageListener.class);
		
		notifyMessageProducer.sendTopic(EventType.UNSUBSCRIBE.getValue(),"openid-54321");
		logger.info("sended message");

		Threads.sleep(1000);
		assertEquals("EventType:unsubscribe, OpenID:openid-54321", appender.getFirstMessage());
	}
}
