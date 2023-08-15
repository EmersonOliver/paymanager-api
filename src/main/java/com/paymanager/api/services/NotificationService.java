package com.paymanager.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.paymanager.api.domain.user.UserEntity;
import com.paymanager.api.dtos.NotificationDTO;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.notification.url}")
	private String urlNotification;

	public void sendNotification(UserEntity user, String message) throws Exception {
		String email = user.getEmail();
		// o4d9z.mocklab.io/notify

		NotificationDTO notificationRequest = new NotificationDTO(email, message);

		ResponseEntity<String> response = restTemplate.postForEntity(this.urlNotification, notificationRequest, String.class);
		
		if(!(response.getStatusCode() == HttpStatus.OK)) {
			System.out.println("Falha ao enviar notificacao");
			throw new Exception("Servico de notificacao indisponivel");
		}

	}

}
