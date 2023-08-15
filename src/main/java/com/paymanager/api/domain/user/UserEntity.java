package com.paymanager.api.domain.user;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paymanager.api.dtos.UserDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_users")
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstname;

	private String lastname;

	@Column(unique = true)
	private String document;

	@Column(unique = true)
	private String email;

	private String password;

	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	
	public void user(UserDTO data) {
		
	}


	public UserEntity(UserDTO data) {
		this.firstname = data.getFirstname();
		this.lastname = data.getLastname();
		this.balance = data.getBalance();
		this.document= data.getDocument();
		this.email = data.getEmail();
		this.userType = data.getUserType();
		this.password = data.getPassword(); 
	}
}
