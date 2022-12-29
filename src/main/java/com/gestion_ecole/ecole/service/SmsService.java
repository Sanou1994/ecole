package com.gestion_ecole.ecole.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gestion_ecole.ecole.entities.SmsMessage;

@Service
public class SmsService {

	    static final String  clientId="ENh5xjblqvCX5OzxRLPPCIGc1bvjBIqp";
	    static final String clientSecret="JiGDA0Ij7UuYCKIR";
	    static final String urlBase="https://api.orange.com/";
	    static final String urlToken="https://api.orange.com/oauth/v3/token";

	    @Autowired
	    RestTemplate restTemplate;
   
	public void sendMessage(SmsMessage smsMessage) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(smsMessage);
		System.out.println(" MESSAGE :"+json);
		String apiUrl =urlBase+"smsmessaging/v1/outbound/"+smsMessage.getOutboundSMSMessageRequest().getAddress()+"/requests";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
       	System.out.println("Authorization Ccode------" +getTokenOrangeSms() );
        headers.set("Authorization","Bearer "+getTokenOrangeSms());

		HttpEntity<String> request = new HttpEntity<String>(json,headers);
		System.out.println("Access Token Response ---------" +new RestTemplate().exchange(apiUrl, HttpMethod.POST, request, String.class));
	
	}

  public String getTokenOrangeSms() { MultiValueMap<String, String>
  parametersMap = new LinkedMultiValueMap<String, String>();
  parametersMap.add("grant_type", "client_credentials");
  parametersMap.add("client_id",clientId);
  parametersMap.add("client_secret",clientSecret);
  parametersMap.add("audience",urlToken);
  DefaultOAuth2AccessToken token = ( new RestTemplate()).postForObject(urlToken, parametersMap, DefaultOAuth2AccessToken.class);
  return token.getValue(); } }
 
