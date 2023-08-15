package com.paymanager.api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroHandler {

	private String mensagem;
	private String statusCode;
	private LocalDateTime timestamp = LocalDateTime.now();

	public ErroHandler(String mensagem, String statusCode) {
		super();
		this.mensagem = mensagem;
		this.statusCode = statusCode;
	}

}
