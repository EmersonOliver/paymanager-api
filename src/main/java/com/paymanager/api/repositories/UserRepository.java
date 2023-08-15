package com.paymanager.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymanager.api.domain.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

	Optional<UserEntity> findUserEntityByDocument(String document);
	Optional<UserEntity> findUserEntityById(UUID id);
	
}
