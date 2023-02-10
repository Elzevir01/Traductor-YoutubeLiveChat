package servicios;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

import ytChat.YtFrame;

public class Aws implements Runnable{
	String texto;
	String regionServer = "us-east-1";////Europa eu-central-1//Sur America sa-east-1////Medio Oriente me-central-1////Asia Pacifico ap-northeast-1////Estados Unidos E us-east-1////
	YtFrame ytf;
	AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
	String idioma;
	 
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Aws(String tex) {
		//super();
		this.texto = tex;
	}
	public Aws() {
		
	}
	
	@Override
	public void run() {
		traducirSeleccion();
	}
	public void traducirSeleccion() {
        try {
       AmazonTranslate translate = AmazonTranslateClient.builder()
               .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
               .withRegion(regionServer)
               .build();

       TranslateTextRequest request = new TranslateTextRequest()
               .withText(texto)
               .withSourceLanguageCode("auto")
               .withTargetLanguageCode(idioma());
      
       TranslateTextResult result  = translate.translateText(request);
       texto = result.getTranslatedText();
       
       
       }catch (Exception e) {
       	System.out.println(e.getMessage());
       }
       ytf.txtpnTxptraduccion.setText(texto);
	}
	
public String traducirTodo(String texto) {
		idioma();
        try {
       AmazonTranslate translate = AmazonTranslateClient.builder()
               .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
               .withRegion(regionServer)
               .build();

       TranslateTextRequest request = new TranslateTextRequest()
               .withText(texto)
               .withSourceLanguageCode("auto")
               .withTargetLanguageCode(idioma);
      
       TranslateTextResult result  = translate.translateText(request);
       texto = result.getTranslatedText();
       
       
       }catch (Exception e) {
       	System.out.println(e.getMessage());
       }
       return texto;
	}
	public String idioma() {
		switch (ytf.cmbIdiomaSalida.getSelectedIndex()) {
		case 0:
			idioma = "es";
			break;
		case 1:
			idioma = "en";
			break;
		case 2:
			idioma = "ja";
			break;
		case 3:
			idioma = "zh";
			break;
		default:
			idioma = "es";
			break;
		}
		return idioma;
	}
	public String servidor() {
		
		////seleccion de server "Estados Unidos", "Europa", "Sur America", "Asia"////
		//Europa eu-central-1//Sur America sa-east-1////Medio Oriente me-central-1////Asia Pacifico ap-northeast-1////Estados Unidos E us-east-1////
		switch (ytf.cmbServidor.getSelectedIndex()) {
		case 0:
			//servidor Norte America Este
			regionServer = "us-east-1";
			break;
		case 1:
			//servidor Europa Central
			regionServer = "eu-central-1";
			break;
		case 2:
			//servidor Sur America
			regionServer = "sa-east-1";
			break;
		case 3:
			//Servidor Asia Pacifico
			regionServer = "ap-northeast-1";
			break;
		default:
			regionServer = "us-east-1";
			break;
		}
		System.out.println("Server activo: "+regionServer);
		return regionServer;
	}
}
