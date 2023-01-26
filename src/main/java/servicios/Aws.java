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
	String REGION = "us-east-1";
	YtFrame ytf;
	AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
	 
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
		traductor();
	}
	public void traductor() {
		
        try {
       AmazonTranslate translate = AmazonTranslateClient.builder()
               .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
               .withRegion(REGION)
               .build();

       TranslateTextRequest request = new TranslateTextRequest()
               .withText(texto)
               .withSourceLanguageCode("ja")
               .withTargetLanguageCode("es");
      
       TranslateTextResult result  = translate.translateText(request);
       texto = result.getTranslatedText();
       
       
       }catch (Exception e) {
       	System.out.println(e.getMessage());
       }
       ytf.txtpnTxptraduccion.setText(texto);
	}

	
}
