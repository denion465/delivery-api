package backend.deliveryapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.deliveryapi.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
