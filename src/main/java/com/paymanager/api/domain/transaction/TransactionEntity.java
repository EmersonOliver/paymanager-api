package com.paymanager.api.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paymanager.api.domain.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_transactions")
@Table(name = "tb_transacions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private UserEntity sendUser;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private UserEntity receiveUser;
	
	private LocalDateTime timestamp;
	
	
}
