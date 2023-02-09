package servicios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.kusaanko.youtubelivechat.ChatItem;
import com.github.kusaanko.youtubelivechat.IdType;
import com.github.kusaanko.youtubelivechat.YouTubeLiveChat;

import ytChat.YtFrame;
import servicios.Aws;

public class YoutubeChat implements Runnable{
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public YouTubeLiveChat chat;
	public String mensaje ;
	YtFrame ytf;
	public String link;
	Aws aws ;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public void run() {
		if(ytf.rdbTraducirSeleccion.isSelected())
			getChatList();
		if(ytf.rdbTraducirTodo.isSelected())
			getTranslatedChatList();
	}
	public YoutubeChat(){
		
	}
	public YoutubeChat(String lnk) {
		this.link = lnk;
	}
	
	public void getChatList() {
		
		try {
			chat = new YouTubeLiveChat(link, true, IdType.VIDEO);
			
			
		int liveStatusCheckCycle = 0;
				
		while (!ytf.btnIniciar.isEnabled()) {
		    chat.update();
		    
		    for (ChatItem item : chat.getChatItems()) {
		    	if(!ytf.modeloLista.contains(item.getAuthorName()+": "+item.getMessage())) {
			    	 mensaje = item.getMessage();
			         ytf.modeloLista.addElement(item.getAuthorName()+": "+item.getMessage());
		    	}
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
		
public void getTranslatedChatList() {
		aws = new Aws();
		
		try {
			chat = new YouTubeLiveChat(link, true, IdType.VIDEO);
			
			
		int liveStatusCheckCycle = 0;
				
		while (!ytf.btnIniciar.isEnabled()) {
		    chat.update();
		    for (ChatItem item : chat.getChatItems()) {
		    	mensaje = item.getMessage();
		        ytf.modeloLista.addElement(item.getAuthorName()+": "+aws.traducirTodo(mensaje));
		        
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
		    
		}
	
	
	
}

