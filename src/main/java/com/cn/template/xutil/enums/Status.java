package com.cn.template.xutil.enums;

/**
 * 判断，用于是非的判断.
 * 
 * @author Libra
 *
 */
public enum Status {
	/** 删除 */
	DELETE("删除", 0),
	/** 停用 */
	DISABLE("停用", 1),
	/** 正常 */
	NORMAL("正常",2);

	private String value;
	private int index;

	/**
	 * 构造赋值.
	 * 
	 * @param value
	 * @param index
	 */
	private Status(String value, int index) {
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
		for (Status c : Status.values()) {
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
