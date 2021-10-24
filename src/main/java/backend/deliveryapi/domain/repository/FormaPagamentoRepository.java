package backend.deliveryapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.deliveryapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
}
