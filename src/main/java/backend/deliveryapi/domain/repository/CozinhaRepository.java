package backend.deliveryapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.deliveryapi.domain.model.Cozinha;


public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{}
