package com.paymanager.api.dtos;

import java.math.BigDecimal;

import com.paymanager.api.domain.user.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String firstname;
	private String lastname;
	private String document;
	private String email;
	private String password;
	private BigDecimal balance;
	private UserType userType;

}
