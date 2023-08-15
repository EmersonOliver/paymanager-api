package com.paymanager.api.domain.user;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_users")
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

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

	
}
