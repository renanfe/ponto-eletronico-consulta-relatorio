package br.com.fiap.pontoeletronicoconsultaregistro.core.application.ports;

import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RegistroPonto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistroPontoRepository {
    Optional<List<RegistroPonto>> buscarRegistroPontoPorFuncionarioId(UUID funcionarioId);

    Optional<List<RegistroPonto>> buscarRegistrosPontoPorMes(Integer mes);
}
