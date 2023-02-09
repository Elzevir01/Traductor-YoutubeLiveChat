package ytChat;

import ytChat.YtFrame;


public class UiIdioma {
	YtFrame ytf;
	
	///ESPAÑOL///
	public void es() {
		///LABELS///
		ytf.lblServidor.setText("Servidor:");
		ytf.lblUIIdioma.setText("UI Idioma:");
		//ytf.lblYoutubeLink.setText(null);
		ytf.lblIdiomaOrigen.setText("Idioma Origen:");
		ytf.lblIdiomaTraduccion.setText("Idioma Traduccion:");
		///NUTTONS///
		ytf.btnIniciar.setText("Iniciar");
		ytf.btnDetener.setText("Detener");
		///RADIO BUTTONS///
		ytf.rdbTraducirSeleccion.setText("Chat Original");
		ytf.rdbTraducirTodo.setText("Chat Traducido(Lento)");
	}
	///INGLES///
	public void en() {
		///LABELS///
		ytf.lblServidor.setText("Server:");
		ytf.lblUIIdioma.setText("UI language");
		//ytf.lblYoutubeLink.setText(null);
		ytf.lblIdiomaOrigen.setText("Source Language:");
		ytf.lblIdiomaTraduccion.setText("Language Translation:");
		///NUTTONS///
		ytf.btnIniciar.setText("Start");
		ytf.btnDetener.setText("Stop");
		///RADIO BUTTONS///
		ytf.rdbTraducirSeleccion.setText("Original Chat");
		ytf.rdbTraducirTodo.setText("Translated Chat");
	}
	///JAPONES///
	public void ja() {
		///LABELS///
				ytf.lblServidor.setText("サーバ");
				ytf.lblUIIdioma.setText("UI言語");
				//ytf.lblYoutubeLink.setText(null);
				ytf.lblIdiomaOrigen.setText("ソース言語：");
				ytf.lblIdiomaTraduccion.setText("言語翻訳:");
				///NUTTONS///
				ytf.btnIniciar.setText("始める");
				ytf.btnDetener.setText("逮捕");
				///RADIO BUTTONS///
				ytf.rdbTraducirSeleccion.setText("オリジナルチャット");
				ytf.rdbTraducirTodo.setText("翻訳されたチャット (遅い)");
	}
	///CHINO///
	public void zh() {
		///LABELS///
				ytf.lblServidor.setText("服务器");
				ytf.lblUIIdioma.setText("界面语言");
				//ytf.lblYoutubeLink.setText(null);
				ytf.lblIdiomaOrigen.setText("源语言：");
				ytf.lblIdiomaTraduccion.setText("语言翻译：");
				///NUTTONS///
				ytf.btnIniciar.setText("开始");
				ytf.btnDetener.setText("逮捕");
				///RADIO BUTTONS///
				ytf.rdbTraducirSeleccion.setText("原始聊天");
				ytf.rdbTraducirTodo.setText("翻译聊天（慢）");
	}
	
	public void seleccionIdioma() {
		switch (ytf.cmbUIIdioma.getSelectedIndex()) {
		case 0:
			es();
			break;
		case 1:
			en();
			break;
		case 2:
			ja();
			break;
		case 3:
			zh();
			break;
		default:
			es();
			break;
		}
	}
}

