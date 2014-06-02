package com.cn.template.xutil.freemarker;

import java.io.IOException;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 读取FreeMarker模板.
 * @author Libra
 *
 */
public class ReadTemplate {
	private static final String DEFAULT_ENCODING = "utf-8";
	
	public static void main(String[] args) {
		System.out.println("In Here ......");
		try {
		Map<String,String> map=Maps.newHashMap();
		map.put("userName", "Libra");
		
		
		FreeMarkerConfigurationFactory freeMarkerConfigurationFactory=new FreeMarkerConfigurationFactory();
		freeMarkerConfigurationFactory.setTemplateLoaderPath("classpath:/freemarker");
		
		Configuration configuration=freeMarkerConfigurationFactory.createConfiguration();
		Template template=configuration.getTemplate("mailTemplate.ftl", DEFAULT_ENCODING);
		
		System.out.println(FreeMarkerTemplateUtils.processTemplateIntoString(template, map));
		} catch (IOException e) {
			System.out.println("生成邮件内容失败, FreeMarker模板不存在");
			e.printStackTrace();
		} catch (TemplateException e) {
			System.out.println("生成邮件内容失败, FreeMarker处理失败");
			e.printStackTrace();
		}
	}
	
}
