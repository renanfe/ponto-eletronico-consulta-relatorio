package br.com.fiap.pontoeletronicoconsultaregistro.adapter.driven.infra.jpa;

import br.com.fiap.pontoeletronicoconsultaregistro.adapter.driven.infra.entity.RegistroPontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistroPontoJpa extends JpaRepository<RegistroPontoEntity, UUID> {

    Optional<List<RegistroPontoEntity>> findAllByFuncionarioId(UUID funcionarioId);

    Optional<List<RegistroPontoEntity>> findAllByDataHoraRegistroBetween (LocalDateTime dataComecoDoMes, LocalDateTime dataFinalDoMes);
}
