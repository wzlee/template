package com.cn.template.web.listener.jms;

import java.util.Map;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import com.google.common.collect.Maps;


/**
 * JMS用户变更消息生产者.
 * 
 * 使用jmsTemplate将用户变更消息分别发送到queue与topic.
 * 
 */
public class NotifyMessageProducer {

	private JmsTemplate jmsTemplate;
	private Destination notifyQueue;
	private Destination notifyTopic;

	/** 队列 */
	public void sendQueue(String event,String openid) {
		sendMessage(event, openid, notifyQueue);
	}
	

	public void sendQueue(String event,String content,String path,String openid) {
		sendMessage(event, content, path, openid, notifyQueue);
	}
	
	public void sendQueue(String event,String eventKey,Long createTime,String ticket,String path,String openid) {
		sendMessage(event, eventKey, createTime,ticket,path, openid, notifyQueue);
	}

	/** 一般规则 */
	public void sendTopic(String event,String openid) {
		sendMessage(event, openid, notifyTopic);
	}

	/**
	 * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
	 */
	private void sendMessage(String event,String openid, Destination destination) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("event", event);
		map.put("openid", openid);
		jmsTemplate.convertAndSend(destination, map);
	}
	
	private void sendMessage(String event,String content,String contextPath,String openid, Destination destination) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("event", event);
		map.put("content", content);
		map.put("contextPath", contextPath);
		map.put("openid", openid);
		jmsTemplate.convertAndSend(destination, map);
	}
	
	private void sendMessage(String event,String eventKey,Long createTime,String ticket,String contextPath,String openid, Destination destination) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("event", event);
		map.put("eventKey", eventKey);
		map.put("createTime", createTime);
		map.put("ticket", ticket);
		map.put("contextPath", contextPath);
		map.put("openid", openid);
		jmsTemplate.convertAndSend(destination, map);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

	public void setNotifyTopic(Destination nodifyTopic) {
		this.notifyTopic = nodifyTopic;
	}
}
