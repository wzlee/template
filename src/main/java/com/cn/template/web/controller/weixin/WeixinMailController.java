package com.cn.template.web.controller.weixin;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.template.entity.mail.EmailContent;
import com.cn.template.service.mail.EmailAttachmentService;
import com.cn.template.service.mail.EmailContentService;
import com.cn.template.xutil.Constants;
import com.cn.template.xutil.Utils;
import com.cn.template.xutil.web.Servlets;
import com.google.common.collect.Maps;

/**
 * 与微信接入业务处理的代理类.
 * @author Libra
 *
 */
@Controller
@RequestMapping(value = "/wxmail")
public class WeixinMailController {

	private static Logger logger=LoggerFactory.getLogger(WeixinMailController.class);
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("subject", "标题");
	}
	
	/** 邮件内容的业务处理 */
	@Autowired
	private EmailContentService emailContentService;
	
	/** 邮件附件的业务处理 */
	@Autowired
	private EmailAttachmentService emailAttachmentService;
	
	/**
	 * 邮件列表.
	 * @param openid 微信用户的标识
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{openid}",method = RequestMethod.GET)
	public String list(@PathVariable("openid") Long openid,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Constants.PAGE_SIZE_3) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
	
		Page<EmailContent> emailContents = emailContentService.getUserEmailContent(openid, searchParams, pageNumber, pageSize, sortType);

		model.addAttribute("emailContents", emailContents);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "wxmail/mail_list";
	}
	
	@RequestMapping(value="/jsonValue")
	@ResponseBody
	public Map<String,String[]> getValue(){
		Map<String,String[]> map=Maps.newHashMap();
		String[] aa=new String[10];
		for(int i=0;i<aa.length;i++){
			aa[i]="<li><a href=\"#\">新华网深圳3月23日电（记者 赵瑞西）23日，深圳市南山区西里医院的大楼</a><span>羽Libra &emsp;&emsp; 2014年5月28日</span></li>";
		}
		map.put("html", aa);
		return map;
	}
}
