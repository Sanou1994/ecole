package com.gestion_ecole.ecole.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.entities.SmsMessage;

@Service
public class SmsService
{


    static final String  clientId="Ux8BPAk4wnXN0leW4a92iVHXeDl9ZXAf";
    static final String clientSecret="X0ikbHQaZQProhhB";
    static final String urlBase="https://api.orange.com/";
    static final String urlToken="https://api.orange.com/oauth/v3/token";

    @Autowired
    RestTemplate restTemplate;

public Reponse sendMessage(SmsMessage smsMessage)  {
	Reponse reponse = new Reponse();
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json;
	try {
		json = ow.writeValueAsString(smsMessage);
		String apiUrl =urlBase+"smsmessaging/v1/outbound/"+smsMessage.getOutboundSMSMessageRequest().getAddress()+"/requests";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer "+getTokenOrangeSms());

		HttpEntity<String> request = new HttpEntity<String>(json,headers);
		ResponseEntity<String> responseEntity =new RestTemplate().exchange(apiUrl, HttpMethod.POST, request, String.class);
		if(responseEntity.getStatusCodeValue() == 201)
	    {
	    	reponse.setCode(200);
	    	reponse.setMessage("Le message sms a été envoyé");
	    	reponse.setResult(true);		    	
	    }
	    else
	    {
	    	reponse.setCode(500);
	    	reponse.setMessage(" Erreur est survenue lors de l'envoi du message sms ");
	    	reponse.setResult(false);		
	    }
		
	} catch (JsonProcessingException e) {
		reponse.setCode(500);
    	reponse.setMessage(" Erreur est survenue interne est survenue ");
    	reponse.setResult(e.getMessage());
	}
	    return reponse;

}

public String getTokenOrangeSms() { MultiValueMap<String, String>
parametersMap = new LinkedMultiValueMap<String, String>();
parametersMap.add("grant_type", "client_credentials");
parametersMap.add("client_id",clientId);
parametersMap.add("client_secret",clientSecret);
parametersMap.add("audience",urlToken);
DefaultOAuth2AccessToken token = ( new RestTemplate()).postForObject(urlToken, parametersMap, DefaultOAuth2AccessToken.class);
return token.getValue(); }


}
 
