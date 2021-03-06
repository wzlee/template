package com.cn.template.xutil.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeUtility;
import javax.mail.search.AndTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.cn.template.modules.test.category.UnStable;
import com.cn.template.xutil.Constants;
import com.cn.template.xutil.Utils;
import com.cn.template.xutil.enums.AttachmentType;
import com.google.common.collect.Maps;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

/**
 * 邮件下载的测试类. 邮件按天下载到html/email文件夹.
 * 
 * @author Libra
 * 
 */
@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class DownLoadMail {

	private static Logger logger=LoggerFactory.getLogger(DownLoadMail.class);
	
	private String USER = "lzr@ggec.gd";
	private String PASSWORD = "pass";

	@Test
	public void downLoad() {
		try {
			// 通过Imap的方式收取邮件.
			Properties props = System.getProperties();
			props.put("mail.imap.host", "mail.ggec.gd");
			props.put("mail.store.protocol", "imap");
			Session session = Session.getDefaultInstance(props, null);
			IMAPStore store = (IMAPStore) session.getStore("imap");
			store.connect(USER, PASSWORD);
			IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			
			//搜索邮件[初定以时间作为搜索条件，如果从未收取的，默认收取一周内邮件；如果收取的，从最近的一次收取时间开始]
			
			//此处测试，先指定收取一份邮件
			//搜索发件人为 lzr@ggec.gd 和主题为"测试邮件"的邮件(模糊搜索)  
	        SearchTerm st = new AndTerm(new FromStringTerm("lzr@ggec.gd"),new SubjectTerm("测试邮件")); 
	        
	        //循环解析获得的邮件
	        for (Message message : folder.search(st)) { 
	        	logger.info("邮件主题:{}",message.getSubject());
	        	if(message.getFlags().contains(Flags.Flag.SEEN)){
	        		logger.info("邮件已读");
	        	}else{
	        		logger.info("邮件未读");
	        	}
	        	Map<String,Object> map=Maps.newHashMap();
	        	map.put("TEXT", "");
	        	map.put("HTML", "");
	        	map.put("ATTACHMENT", new ArrayList<Map<String,Object>>());
	        	getMailContent(map,message);
	        	
	        	String html=map.get("HTML").toString();
	        	List<Map<String,Object>> attachments=(List<Map<String, Object>>) map.get("ATTACHMENT");
	        	for(Map<String,Object> attachment:attachments){
	        		Integer fileType = Integer.parseInt(attachment.get("fileType").toString());
	        		if(fileType.equals(AttachmentType.ATTACHMEN.ordinal())){
	        			
	        		}else{
	        			int site = html.indexOf("cid:"+attachment.get("fileName").toString());
	        			logger.info("位置1：{}",site);
	        			logger.info("位置2：{}",html.indexOf(">", site));
	        			
	        			html=html.substring(0,site)+attachment.get("fileUrl").toString()+"\""+html.substring(html.indexOf(">", site));
	        			
	        		}
	        	}
	        	
	        	logger.info("HTML : {}",html);
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 解析邮件的主体内容.
	 * 如果邮件中有内嵌资源（附件、HTML、图片等等）,需要通过递归方式进行获取.
	 * @param part
	 * @throws Exception
	 */
	private void getMailContent(Map<String,Object> map,Part part) throws Exception{
		if(part.isMimeType("text/plain")){
			logger.info("文本内容：{}",part.getContent());
			map.put("TEXT", map.get("TEXT").toString()+part.getContent());
			
    	}else if(part.isMimeType("text/html")){
    		logger.info("HTML内容：{}",part.getContent());
    		map.put("HTML", map.get("HTML").toString()+part.getContent());
    		
    	}else if(part.isMimeType("multipart/*")){
    		//邮件有内嵌资源
    		Multipart multipart =(Multipart)part.getContent();
    		for(int i=0;i<multipart.getCount();i++){
    			BodyPart bodyPart = multipart.getBodyPart(i);
    			//递归读取邮件资源.
    			getMailContent(map,bodyPart);
    			String fileName=bodyPart.getFileName();
    			if(fileName!=null){
    				Map<String,Object> fileMap=Maps.newHashMap();
    				fileName=new String(fileName.getBytes("ISO-8859-1"),"gb2312");
    				if(fileName.toLowerCase().startsWith("=?gb")){
    					 fileName = MimeUtility.decodeText(fileName); 
    				}
    				
    				fileMap.put("fileName", fileName);
    				
    				String url = saveFile(bodyPart.getInputStream(),fileName.substring(fileName.lastIndexOf(".")),"D:"+"/html/email/");
    				
    				fileMap.put("fileUrl", url);
    				
    				if(bodyPart.getDisposition()!=null&&(bodyPart.getDisposition().equals(Part.ATTACHMENT)||bodyPart.getDisposition().equals(Part.INLINE))){
    					logger.info("{} : 这是附件！ 路径：{}",fileName,url);
    					fileMap.put("fileType", AttachmentType.ATTACHMEN.ordinal());
    				}else{
    					logger.info("{} : 这是图片！ 路径：{}",fileName,url);
    					fileMap.put("fileType", AttachmentType.PICTURE.ordinal());
    				}
    				List list = (List) map.get("ATTACHMENT");
    				list.add(fileMap);
    				map.put("ATTACHMENT",list);
    			}
    		}
    	}
	}
	
	/**
	 * 保存邮件附件,返回保存路径.
	 * @param inputStream
	 * @return
	 */
	private String saveFile(InputStream inputStream, String suffix, String path) throws Exception {
		String folder = Utils.datef(new Date(), Constants.DATE_FORMAT_SHORT);
		path = path + folder;
		// 判断文件夹是否存在，如果不存在，则创建.
		File localFile = new File(path);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}

		String fileName = new Date().getTime() + suffix;

		File file = new File(path + "/" + fileName);

		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bis = new BufferedInputStream(inputStream);
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bos.close();
			bis.close();
		}

		return folder + "/" + fileName;
	}
	
	
	
}
