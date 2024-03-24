package br.com.fiap.pontoeletronicoconsultaregistro.core.application.service;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.ports.RegistroPontoRepository;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RegistroPonto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultaService {

    private final RegistroPontoRepository registroPontoRepository;

    public ConsultaService(RegistroPontoRepository registroPontoRepository) {
        this.registroPontoRepository = registroPontoRepository;
    }
    public Optional<List<RegistroPonto>> consultarRegistro(UUID funcionarioId) {
        return registroPontoRepository.buscarRegistroPontoPorFuncionarioId(funcionarioId);
    }

}
