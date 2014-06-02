package com.cn.template.xutil.mail;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;


/**
 * 通过JavaMail读取邮件.
 * 
 * @author Libra
 * 
 */
public class ImapReadMail {

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.put("mail.imap.host", "mail.ggec.gd");
			props.put("mail.store.protocol", "imap"); 
			Session session = Session.getDefaultInstance(props, null);  
	        //URLName urln = new URLName("imap", "imap.163.com", 110, null,"ggec_line@163.com", "ggecit2014");  
	        IMAPStore store = (IMAPStore)session.getStore("imap");  
	        store.connect("lzr@ggec.gd", "pass"); 
	        IMAPFolder  folder = (IMAPFolder)store.getFolder("INBOX");  
	        folder.open(Folder.READ_WRITE);
	        //读取收件箱中的邮件.
	        //Message message[] = folder.getMessages();  
	        
	        // 搜索未读邮件
	        //SearchTerm st =  new FlagTerm(new Flags(Flags.Flag.SEEN),false);
	        
	        //搜索发件人
	        //SearchTerm st =  new FromStringTerm("lzr@ggec.gd");
	        
	        //搜索发件人为 lzr@ggec.gd 和主题为"测试邮件"的邮件(模糊搜索)  
	        //SearchTerm st = new AndTerm(new FromStringTerm("lzr@ggec.gd"),new SubjectTerm("测试邮件")); 
	        
	        //以时间条件搜索邮件
	        Calendar calendar = Calendar.getInstance(); 
	        calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK - (Calendar.DAY_OF_WEEK - 1)) - 1); 
	        Date mondayDate = calendar.getTime(); 
	        SearchTerm comparisonTermGe = new SentDateTerm(ComparisonTerm.GE, mondayDate); 
	        SearchTerm comparisonTermLe = new SentDateTerm(ComparisonTerm.LE, new Date()); 
	        
	        SearchTerm st = new AndTerm(comparisonTermGe, comparisonTermLe); 
	        
	        Message message[] = folder.search(st);

	        ReciveOneMail pmm = null;  
	        for (int i = 0; i < message.length; i++) { 
	        	pmm = new ReciveOneMail((MimeMessage) message[i]);
	        	System.out.println("Message " + i + " subject: " + pmm.getSubject());
	        	
	        	Flags flags = message[i].getFlags();
	        	if(flags.contains(Flags.Flag.SEEN)){
	        		System.out.println("--------------邮件已读------");
	        	}else{
	        		System.out.println("--------------邮件未读------");
	        	}
	        	
	        	readContent((Part)message[i]);
	        	
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readContent(Part part) throws Exception{
		if(part.isMimeType("text/plain")){
    		System.out.println("内容："+part.getContent());
    		//System.out.println("------------------------------  text/plain");
    	}else if(part.isMimeType("text/html")){
    		//System.out.println(part.getContent());
    		//System.out.println("------------------------------  text/html");
    	}else if(part.isMimeType("multipart/*")){
    		Multipart multipart =(Multipart)part.getContent();
    		for(int j =0;j<multipart.getCount();j++){
    			BodyPart bodyPart = multipart.getBodyPart(j);
    			System.out.println("-getContentType----"+bodyPart.getContentType());
    			readContent(multipart.getBodyPart(j));
    			String disposition = bodyPart.getDisposition();   
    			if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
    				String fileName=bodyPart.getFileName();
    				 System.out.println("文件名 ："+fileName);
    				 String filename= new String(fileName.getBytes("ISO-8859-1"),"gb2312");
    				 System.out.println(filename);
    				 
    				 fileName = MimeUtility.decodeText(fileName); 
    				 System.out.println("文件名 ："+fileName);
    			}else{
    				String fileName=bodyPart.getFileName();
    				if(fileName!=null){
    				String filename= new String("您好".getBytes("ISO-8859-1"),"gb2312");
   				 System.out.println(filename);
    				}
    				 System.out.println("文件名 ："+fileName);
    			}
    			
    		}
    		
    		//System.out.println("------------------------------  multipart/*");
    	}
	}
	
}
