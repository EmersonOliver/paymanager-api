package com.paymanager.api.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.paymanager.api.domain.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_transactions")
@Table(name = "tb_transacions")
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private UserEntity sendUser;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private UserEntity receiveUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime timestamp;
	
	
}
