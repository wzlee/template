package com.cn.template.xutil.enums;

/**
 * 消息类型.
 * 
 * @author Libra
 *
 */
public enum MessageType {
	/** 文本消息 */
	text("text", 0),
	/** 图片消息 */
	image("image", 1),
	/** 语音消息 */
	voice("voice", 2),
	/** 视频消息 */
	video("video", 3),
	/** 音乐消息 */
	music("music", 4),
	/** 图文消息 */
	news("news", 5);

	private String value;
	private int index;

	/**
	 * 构造赋值.
	 * 
	 * @param value
	 * @param index
	 */
	private MessageType(String value, int index) {
		this.value = value;
		this.index = index;
	}

	/**
	 * 取得枚举量的名称.
	 * 
	 * @param index
	 * @return
	 */
	public static String getName(int index) {
		for (MessageType c : MessageType.values()) {
			if (c.getIndex() == index) {
				return c.value;
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
