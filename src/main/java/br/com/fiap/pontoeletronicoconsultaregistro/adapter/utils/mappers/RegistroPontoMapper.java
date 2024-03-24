package br.com.fiap.pontoeletronicoconsultaregistro.adapter.utils.mappers;

import br.com.fiap.pontoeletronicoconsultaregistro.adapter.driven.infra.entity.RegistroPontoEntity;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RegistroPonto;

import java.util.List;

public class RegistroPontoMapper {

    public static RegistroPonto toRegistroPonto(RegistroPontoEntity registroPontoEntity) {
        return RegistroPonto.builder()
                .id(registroPontoEntity.getId())
                .funcionarioId(registroPontoEntity.getFuncionarioId())
                .dataHoraRegistro(registroPontoEntity.getDataHoraRegistro())
                .tipoRegistro(registroPontoEntity.getTipoRegistro())
                .build();
    }

    public static List<RegistroPonto> toListRegistroPonto (List<RegistroPontoEntity> registroPontoEntities) {
        return registroPontoEntities.stream()
                .map(RegistroPontoMapper::toRegistroPonto)
                .toList();
    }
}
