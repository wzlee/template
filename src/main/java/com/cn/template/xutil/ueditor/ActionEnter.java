package com.cn.template.xutil.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.template.xutil.Constants;
import com.cn.template.xutil.ueditor.define.ActionMap;
import com.cn.template.xutil.ueditor.define.AppInfo;
import com.cn.template.xutil.ueditor.define.BaseState;
import com.cn.template.xutil.ueditor.define.State;
import com.cn.template.xutil.ueditor.hunter.FileManager;
import com.cn.template.xutil.ueditor.hunter.ImageHunter;
import com.cn.template.xutil.ueditor.upload.Uploader;

public class ActionEnter {
	private static Logger logger=LoggerFactory.getLogger(ActionEnter.class);
	
	private HttpServletRequest request = null;
	
	private String rootPath = null;
	//private String contextPath = null;
	
	private String actionType = null;
	
	private ConfigManager configManager = null;

	public ActionEnter ( HttpServletRequest request, String rootPath ) {
		
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter("action");
		//this.contextPath = request.getContextPath();
//		Map<String,String[]> map = request.getParameterMap();
//		for(String str :map.keySet()){
//			logger.info("key:{},value:{}",str,map.get(str));
//		}
//		
//		logger.info("rootPath:{},uri:{}",rootPath,request.getRequestURI());
		this.configManager = ConfigManager.getInstance(this.rootPath, Constants.UEDITOR_CONFIG_PATH);
		
	}
	
	public String exec () throws JSONException {
		//actionType为空，或者在ActionMap中没有匹配的对象
		if ( actionType == null || !ActionMap.mapping.containsKey( actionType ) ) {
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
		}
		
		//读取配置文件config.json失败
		if ( this.configManager == null || !this.configManager.valid() ) {
			return new BaseState( false, AppInfo.CONFIG_ERROR ).toJSONString();
		}
		
		
		State state = null;
		
		int actionCode = ActionMap.getType( this.actionType );
		
		Map<String, Object> conf = null;
		
		switch ( actionCode ) {
		
			case ActionMap.CONFIG:
				return this.configManager.getAllConfig().toString();
				
			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				conf = this.configManager.getConfig( actionCode );
				state = new Uploader( request, conf ).doExec();
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig( actionCode );
				String[] list = this.request.getParameterValues( (String)conf.get( "fieldName" ) );
				state = new ImageHunter( conf ).capture( list );
				break;
				
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig( actionCode );
				int start = this.getStartIndex();
				state = new FileManager( conf ).listFile( start );
				break;
				
		}
		logger.info("JSONString :"+state.toJSONString());
		return state.toJSONString();		
	}
	
	public int getStartIndex () {
		
		String start = this.request.getParameter( "start" );
		
		try {
			return Integer.parseInt( start );
		} catch ( Exception e ) {
			return 0;
		}
		
	}
	
}
