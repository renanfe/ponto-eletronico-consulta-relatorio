package br.com.fiap.pontoeletronicoconsultaregistro.core.domain;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class RelatorioPontoEntradaSaida {

    private LocalDate data;
    private UUID funcionarioId;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    @Builder.Default
    private Integer totalHorasTrabalhadas = 0;

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        this.totalHorasTrabalhadas = calcularHorasTrabalhadasFuncionario(this.horaEntrada, this.horaSaida).toHoursPart();
    }

    public Duration calcularHorasTrabalhadasFuncionario(LocalDateTime horaEntrada, LocalDateTime horaSaida) {
        if (horaEntrada != null && horaSaida != null) {
            return Duration.between(horaEntrada, horaSaida);
        } else {
            return Duration.ZERO;
        }
    }

}
