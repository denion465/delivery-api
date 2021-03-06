package backend.deliveryapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.deliveryapi.domain.exception.EntidadeNaoEncontradaException;
import backend.deliveryapi.domain.model.Restaurante;
import backend.deliveryapi.domain.repository.RestauranteRepository;
import backend.deliveryapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
			@RequestBody Restaurante restaurante) {
		try {
			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

			if (restauranteAtual.isPresent()) {
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id",
          "formasPagamento", "endereco", "dataCadastro"
        );

				Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restauranteSalvo);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}

// 	@PatchMapping("/{restauranteId}")
// 	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
// 			@RequestBody Map<String, Object> campos) {
// 		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

// 		if (restauranteAtual.isPresent()) {
// 			return ResponseEntity.notFound().build();
// 		}

// 		merge(campos, restauranteAtual);

// 		return atualizar(restauranteId, restauranteAtual);
// 	}

// 	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

// 		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
// 			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
// 			field.setAccessible(true);

// 			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

// //			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

// 			ReflectionUtils.setField(field, restauranteDestino, novoValor);
// 		});
// 	}

}
