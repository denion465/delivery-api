package backend.deliveryapi.domain.repository;

import java.util.List;

import backend.deliveryapi.domain.model.Cozinha;

public interface CozinhaRepository {

  List<Cozinha> listar();
  Cozinha buscar(Long id);
  Cozinha salvar(Cozinha cozinha);
  void remover(Cozinha cozinha);

}
