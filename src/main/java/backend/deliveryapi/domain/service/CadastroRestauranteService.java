package backend.deliveryapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.deliveryapi.domain.exception.EntidadeNaoEncontradaException;
import backend.deliveryapi.domain.model.Cozinha;
import backend.deliveryapi.domain.model.Restaurante;
import backend.deliveryapi.domain.repository.CozinhaRepository;
import backend.deliveryapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  public Restaurante salvar(Restaurante restaurante) {
    Long cozinhaId = restaurante.getCozinha().getId();

    Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
      .orElseThrow(() -> new EntidadeNaoEncontradaException(
        String.format("Não existe cadastro de cozinha com código %d", cozinhaId)
      ));

      restaurante.setCozinha(cozinha);

    return restauranteRepository.save(restaurante);
  }

}
