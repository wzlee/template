package com.cn.template.xutil.enums;

/**
 * 性别
 * 
 * @author Libra
 *
 */
public enum Gender {
	/** 未知 */
	UNKNOW("未知", 0),
	/** 男性 */
	MAN("男性", 1),
	/** 女性 */
	WOMAN("女性", 2);

	private String value;
	private int index;

	/**
	 * 构造赋值.
	 * 
	 * @param value
	 * @param index
	 */
	private Gender(String value, int index) {
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
		for (Gender c : Gender.values()) {
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
