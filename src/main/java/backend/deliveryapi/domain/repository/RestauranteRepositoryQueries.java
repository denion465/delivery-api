package backend.deliveryapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import backend.deliveryapi.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

  List<Restaurante> find(
    String nome,
    BigDecimal taxaFreteInicial,
    BigDecimal taxaFreteFinal
  );

}
