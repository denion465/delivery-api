package backend.deliveryapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.deliveryapi.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
