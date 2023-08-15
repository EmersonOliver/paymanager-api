package com.paymanager.api.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymanager.api.domain.user.UserEntity;
import com.paymanager.api.domain.user.UserType;
import com.paymanager.api.dtos.UserDTO;
import com.paymanager.api.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void validateTransaction(UserEntity sender, BigDecimal amount) throws Exception {
		if (sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuario do tipo Lojista nao esta autorizado a realizar transacao!");
		}

		if (sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente!");
		}
	}

	public UserEntity findUserById(Long id) throws Exception {
		return userRepository.findUserEntityById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
	}

	public UserEntity createUser(UserDTO data) {
		UserEntity newUser = new UserEntity(data);
		this.saveUser(newUser);
		return newUser;
	}

	public void saveUser(UserEntity user) {
		this.userRepository.save(user);
	}
	
	public List<UserEntity> getAllUsers(){
		return this.userRepository.findAll();
	}

}
