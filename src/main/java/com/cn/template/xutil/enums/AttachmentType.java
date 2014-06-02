package com.cn.template.xutil.enums;

/**
 * 邮件附件的类型
 * 
 * @author Libra
 *
 */
public enum AttachmentType {
	/** 附件 （提供下载和预览的图片） */
	ATTACHMEN("附件", 0),
	/** 图片 （在HTML上的图片） */
	PICTURE("图片", 1);

	private String value;
	private int index;

	/**
	 * 构造赋值.
	 * 
	 * @param value
	 * @param index
	 */
	private AttachmentType(String value, int index) {
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
		for (AttachmentType c : AttachmentType.values()) {
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
