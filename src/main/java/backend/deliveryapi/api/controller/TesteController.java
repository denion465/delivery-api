package backend.deliveryapi.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.deliveryapi.domain.model.Cozinha;
import backend.deliveryapi.domain.model.Restaurante;
import backend.deliveryapi.domain.repository.CozinhaRepository;
import backend.deliveryapi.domain.repository.RestauranteRepository;
import backend.deliveryapi.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import backend.deliveryapi.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;

@RestController
@RequestMapping("/teste")
public class TesteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}

  @GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findByNome(nome);
	}

  @GetMapping("/restaurantes/por-taxa-frete")
  public List<Restaurante> restaurantesPorTaxaFrete(
    BigDecimal taxaInicial, BigDecimal taxaFinal) {
    return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal );
  }

  // @GetMapping("/restaurantes/por-nome")
  // public List<Restaurante> restaurantesPorTaxaFrete(
  //   String nome, Long cozinhaId) {
  //   return restauranteRepository.consultarPorNome(nome, cozinhaId );
  // }

  @GetMapping("/restaurantes/primeiro-por-nome")
  public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
    return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
  }

  @GetMapping("/restaurantes/top2-por-nome")
  public List<Restaurante> restaurantesTop2PorNome(String nome) {
    return restauranteRepository.findTop2ByNomeContaining(nome);
  }

  @GetMapping("/restaurantes/por-nome-e-frete")
  public List<Restaurante> restaurantesPorNomeFrete(String nome,
    BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
      return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

  @GetMapping("/restaurantes/count-por-cozinha")
  public int restaurantesCountPorCozinha(Long cozinhaId) {
    return restauranteRepository.countByCozinhaId(cozinhaId);
  }

  @GetMapping("/restaurantes/com-frete-gratis")
  public List<Restaurante> restaurantesComFreteFratis(String nome) {
    var comFreteGratis = new RestauranteComFreteGratisSpec();
    var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

    return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
  }

}
