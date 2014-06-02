package com.cn.template.xutil.mail;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;


/**
 * 通过JavaMail读取邮件.
 * 
 * @author Libra
 * 
 */
public class Pop3ReadMail {

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "pop3");    
	        props.setProperty("mail.pop3.host", "mail.ggec.gd");  
	        Session session = Session.getDefaultInstance(props);    
	        Store store = session.getStore("pop3");    
	        store.connect("lzr@ggec.gd", "pass");    
	        Folder folder = store.getFolder("INBOX");    
	        folder.open(Folder.READ_WRITE);  
	        //读取收件箱中的邮件.
	        //Message message[] = folder.getMessages();  
	        
	        // 搜索未读邮件
	        //SearchTerm st =  new FlagTerm(new Flags(Flags.Flag.SEEN),false);
	        
	        //搜索发件人
	        //SearchTerm st =  new FromStringTerm("lzr@ggec.gd");
	        
	        //搜索发件人为 lzr@ggec.gd 和主题为"测试邮件"的邮件(模糊搜索)  
	        SearchTerm st = new AndTerm(new FromStringTerm("lzr@ggec.gd"),new SubjectTerm("测试邮件")); 
	        
	        Message message[] = folder.search(st);
	        
	        System.out.println("Messages's length: " + message.length);  
	        
	        ReciveOneMail pmm = null;  
	        for (int i = 0; i < message.length; i++) { 
	        	pmm = new ReciveOneMail((MimeMessage) message[i]);
	        	System.out.println("Message " + i + " subject: " + pmm.getSubject());
	        	
	        	Flags flags = message[i].getFlags(); 
//	        	System.out.println(flags.contains(Flags.Flag.SEEN));
	        	readContent((Part)message[i]);
	        	
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readContent(Part part) throws Exception{
		if(part.isMimeType("text/plain")){
    		System.out.println(part.getContent());
    		System.out.println("------------------------------  text/plain");
    	}else if(part.isMimeType("text/html")){
    		System.out.println(part.getContent());
    		System.out.println("------------------------------  text/html");
    	}else if(part.isMimeType("multipart/*")){
    		Multipart multipart =(Multipart)part.getContent();
    		for(int j =0;j<multipart.getCount();j++){
    			BodyPart bodyPart = multipart.getBodyPart(j);
    			readContent(multipart.getBodyPart(j));
    			String disposition = bodyPart.getDisposition();   
    			System.out.println("disposition : "+disposition);
    			if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
    				String fileName=bodyPart.getFileName();
    				 System.out.println("文件名 ："+fileName);
    				 System.out.println(fileName.toLowerCase().indexOf("gb2312"));
    				 System.out.println(new String(fileName.getBytes("ISO-8859-1"),"gb2312"));
    				 fileName = MimeUtility.decodeText(fileName); 
    				 System.out.println("文件名 ："+fileName);
    			}else{
    				String fileName=bodyPart.getFileName();
    				 System.out.println("文件名 ："+fileName);
    			}
    			
    		}
    		
    		System.out.println("------------------------------  multipart/*");
    	}
	}
	
}
