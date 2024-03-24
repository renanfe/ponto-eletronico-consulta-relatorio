package br.com.fiap.pontoeletronicoconsultaregistro.core.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class RegistroPonto {

    private UUID id;
    private UUID funcionarioId;
    private LocalDateTime dataHoraRegistro;
    private int tipoRegistro;

}
