package backend.deliveryapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import backend.deliveryapi.domain.exception.EntidadeEmUsoException;
import backend.deliveryapi.domain.exception.EntidadeNaoEncontradaException;
import backend.deliveryapi.domain.model.Cidade;
import backend.deliveryapi.domain.model.Estado;
import backend.deliveryapi.domain.repository.CidadeRepository;
import backend.deliveryapi.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoRepository estadoRepository;

  public Cidade salvar(Cidade cidade) {

    Long estadoId = cidade.getEstado().getId();
    Optional<Estado> estado = estadoRepository.findById(estadoId);

    if ( estado.isEmpty()) {
      throw new EntidadeNaoEncontradaException(
        String.format("Não existe cadastro de estado com código %d", estadoId)
      );

    }

    cidade.setEstado(estado.get());

    return cidadeRepository.save(cidade);
  }

  public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cidade com código %d", cidadeId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
		}
	}

}
