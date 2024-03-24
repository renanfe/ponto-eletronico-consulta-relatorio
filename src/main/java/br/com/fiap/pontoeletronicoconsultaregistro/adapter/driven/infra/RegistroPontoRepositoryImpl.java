package br.com.fiap.pontoeletronicoconsultaregistro.adapter.driven.infra;

import br.com.fiap.pontoeletronicoconsultaregistro.adapter.driven.infra.jpa.RegistroPontoJpa;
import br.com.fiap.pontoeletronicoconsultaregistro.adapter.utils.mappers.RegistroPontoMapper;
import br.com.fiap.pontoeletronicoconsultaregistro.core.application.ports.RegistroPontoRepository;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RegistroPonto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RegistroPontoRepositoryImpl implements RegistroPontoRepository {

    private final RegistroPontoJpa registroPontoJpaJpa;

    public RegistroPontoRepositoryImpl(final RegistroPontoJpa registroPontoJpa) {
        this.registroPontoJpaJpa = registroPontoJpa;
    }

    @Override
    public Optional<List<RegistroPonto>> buscarRegistroPontoPorFuncionarioId (UUID funcionarioId) {
        return this.registroPontoJpaJpa.findAllByFuncionarioId(funcionarioId)
                .map(RegistroPontoMapper::toListRegistroPonto);
    }

    @Override
    public Optional<List<RegistroPonto>> buscarRegistrosPontoPorMes (Integer mes) {
        LocalDateTime dataComecoDoMes = LocalDateTime.of(LocalDate.now().getYear(), mes, 1, 0, 0);
        LocalDateTime dataFinalDoMes = dataComecoDoMes.plusMonths(1).minusSeconds(1);
        return this.registroPontoJpaJpa.findAllByDataHoraRegistroBetween(dataComecoDoMes, dataFinalDoMes)
                .map(RegistroPontoMapper::toListRegistroPonto);
    }


}
