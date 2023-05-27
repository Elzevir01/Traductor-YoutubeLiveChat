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
	public String miembro;
	public String imagenUrl;
	
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
		    	//if(!ytf.modeloLista.contains(item.getAuthorName()+": "+item.getMessage())) {
		    		//item.isAuthorModerator();
		    		//item.getMemberBadgeIconURL();
		    		//item.getPurchaseAmount()
		    		//item.getAuthorType()
		    		//item.getType();
			    	 //mensaje = item.getMessage();
		    		//[NORMAL, MODERATOR]
		    	
	    			tipoMiembro(item.getAuthorType().toString());
	    			setImage(item.getAuthorIconURL());
	    			
			    	mensaje = miembro+item.getAuthorName()+": "+item.getMessage();
		    		//System.out.println(item.getAuthorType());
			    	//System.out.println(imagenUrl+" ::: "+mensaje);
			        //String[] items = {imagenUrl+" "+mensaje};

			    	 //ytf.modeloLista.addElement(miembro+item.getAuthorName()+": "+item.getMessage());
			    	//ytf.addNewListItem(imagenUrl, mensaje);
			    	
			    		///////////ELIMINA EXEDENTE DE 20
					/*if (ytf.modeloLista.getSize() >= 50) {
						ytf.modeloLista.remove(0);
					}*/
			        ytf.modeloLista.addElement(imagenUrl+":::"+mensaje);///+" ::: "+mensaje
			        
		    	}
		    
		    liveStatusCheckCycle++;
		    if(liveStatusCheckCycle ==100) {//10 //liveStatusCheckCycle >= 10
		    	//System.out.println(liveStatusCheckCycle);
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
		    	
		    	tipoMiembro(item.getAuthorType().toString());
    			setImage(item.getAuthorIconURL());
		    	try {
		    		
		        ytf.modeloLista.addElement(imagenUrl+":::"+miembro+item.getAuthorName()+": "+aws.traducirTodo(mensaje));
		    	}catch(Exception e) {
		    		System.out.println(e);
		    	}
		        
		    }
		    liveStatusCheckCycle++;
		    if(liveStatusCheckCycle >= 100) {
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
	public String tipoMiembro(String tipo) {
		
		if(tipo.toString().contains("MEMBER")){
			miembro = "[M]";
		}
		else if(tipo.toString().contains("MODERATOR")){
			miembro = "[MOD]";
		}else {
			miembro = "[N]";
		}
		return miembro;
		}
	public void setImage(String imageAutor) {
		if(imageAutor != null) {
			imagenUrl = imageAutor;
		}else {
			imagenUrl = "https://yt4.ggpht.com/yR98qzW2WEGLSfsNqFU5b0nsDYS2DivE4cGTDS0hJLm1cMp5NSzLFgrm4GefsTXNIAPc2Zu_ag=s64-c-k-c0x00ffffff-no-rj";
		}
	}
	
}

