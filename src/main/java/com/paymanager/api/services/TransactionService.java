package com.paymanager.api.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.paymanager.api.domain.transaction.TransactionEntity;
import com.paymanager.api.domain.user.UserEntity;
import com.paymanager.api.dtos.TransactionDTO;
import com.paymanager.api.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.auth.url}")
	private String urlAuth;

	public TransactionEntity createTransaction(TransactionDTO transaction) throws Exception {
		UserEntity sender = this.userService.findUserById(transaction.getSenderId());
		UserEntity receiver = this.userService.findUserById(transaction.getReceiverId());
		userService.validateTransaction(sender, transaction.getValue());
		boolean isAuthorized = this.authorizedTransaction(sender, transaction.getValue());
		if(!isAuthorized) {
			throw new Exception("Transacao nao autorizada!");
		}
		
		TransactionEntity newTransaction = new TransactionEntity();
		newTransaction.setAmount(transaction.getValue());
		newTransaction.setReceiveUser(receiver);
		newTransaction.setSendUser(sender);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.getValue()));
		receiver.setBalance(receiver.getBalance().add(transaction.getValue()));
		
		this.transactionRepository.save(newTransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
		this.notificationService.sendNotification(sender, "Transacao Realizada com sucesso!");
		this.notificationService.sendNotification(receiver, "Transacao Recebida com sucesso!");
		return newTransaction;
	}

	public boolean authorizedTransaction(UserEntity sender, BigDecimal value) {
	@SuppressWarnings("rawtypes")
	ResponseEntity<Map> response =	restTemplate.getForEntity(urlAuth, Map.class);
	if(response.getStatusCode() == HttpStatus.OK) {
		String message = (String) response.getBody().get("message");
		return "Autorizado".equalsIgnoreCase(message);
	}
		return false;
	}
}
