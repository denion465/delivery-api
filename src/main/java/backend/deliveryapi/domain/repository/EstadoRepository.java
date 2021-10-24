package backend.deliveryapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.deliveryapi.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
