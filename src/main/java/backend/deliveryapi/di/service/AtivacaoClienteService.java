package backend.deliveryapi.di.service;

import org.springframework.stereotype.Component;

import backend.deliveryapi.di.modelo.Cliente;
import backend.deliveryapi.di.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {

  private NotificadorEmail notificador;

  public void ativar(Cliente cliente) {
    cliente.ativar();

    notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
  }

}
