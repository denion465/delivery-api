package backend.deliveryapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.deliveryapi.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
