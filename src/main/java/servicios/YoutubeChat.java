package servicios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.kusaanko.youtubelivechat.ChatItem;
import com.github.kusaanko.youtubelivechat.IdType;
import com.github.kusaanko.youtubelivechat.YouTubeLiveChat;

import ytChat.YtFrame;

public class YoutubeChat implements Runnable{
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public YouTubeLiveChat chat;
	public String mensaje ;
	YtFrame ytf;
	public String link;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public void run() {
		getChatList();
	}
	public YoutubeChat(){
		
	}
	public YoutubeChat(String lnk) {
		this.link = lnk;
	}
	public void getChat(String Link) {
	
	try {
		chat = new YouTubeLiveChat(Link, true, IdType.VIDEO);
	
	int liveStatusCheckCycle = 0;

	while (true) {
	    chat.update();
	    for (ChatItem item : chat.getChatItems()) {
	        System.out.println(format.format(new Date(item.getTimestamp() / 1000)) + " " + item.getType() + "[" + item.getAuthorName() + "]" + item.getAuthorType() + " " + item.getMessage());
	    }
	    liveStatusCheckCycle++;
	    if(liveStatusCheckCycle >= 10) {
	        // Calling this method frequently, cpu usage and network usage become higher because this method requests a http request.
	        if(!chat.getBroadcastInfo().isLiveNow) {//.isLiveEnd
	            break;
	        }
	        liveStatusCheckCycle = 0;
	    }
	    
	}} catch (IOException e1) {
		e1.printStackTrace();
	}
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	public void getChatList() {
		
		
		try {
			chat = new YouTubeLiveChat(link, true, IdType.VIDEO);
			
			
		int liveStatusCheckCycle = 0;
				
		while (true) {
		    chat.update();
		    for (ChatItem item : chat.getChatItems()) {
		        System.out.println(format.format(new Date(item.getTimestamp() / 1000)) + " " + item.getType() + "[" + item.getAuthorName() + "]" + item.getAuthorType() + " " + item.getMessage());
		        mensaje = format.format(new Date(item.getTimestamp() / 1000)) + " " + item.getType() + "[" + item.getAuthorName() + "]" + item.getAuthorType() + " " + item.getMessage();

		        ytf.modeloLista.addElement(item.getAuthorName()+": "+item.getMessage());
		        
		    }
		    liveStatusCheckCycle++;
		    if(liveStatusCheckCycle >= 10) {
		        // Calling this method frequently, cpu usage and network usage become higher because this method requests a http request.
		        if(!chat.getBroadcastInfo().isLiveNow) {//.isLiveEnd
		            break;
		        }
		        liveStatusCheckCycle = 0;
		    }
		    
		}} catch (IOException e1) {
			e1.printStackTrace();
		}
		    try {
		        Thread.sleep(1000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	
	
	
	
}

