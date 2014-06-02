package com.cn.template.web.controller.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.template.xutil.Constants;
import com.cn.template.xutil.ueditor.ActionEnter;
import com.cn.template.xutil.ueditor.define.AppInfo;
import com.cn.template.xutil.ueditor.define.BaseState;


/**
 * 富文本编辑器相关处理的业务代理.
 * 
 * @author Libra
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController {
	
	private static Logger logger=LoggerFactory.getLogger(UeditorController.class);
	/**
	 * UEditor服务器统一请求接口路径.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String controller(HttpServletRequest request,HttpServletResponse response){
		try{
			logger.info("进入UEditor处理---------------------------");
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type" , "text/html");
			return new ActionEnter(request,Constants.WEBROOT).exec();
		}catch(Exception e){
			e.printStackTrace();
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
		}
	}
}
