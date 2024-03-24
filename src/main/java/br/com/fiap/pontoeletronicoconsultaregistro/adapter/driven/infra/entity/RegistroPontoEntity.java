package br.com.fiap.pontoeletronicoconsultaregistro.adapter.driven.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "registro_ponto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RegistroPontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "funcionario_id")
    private UUID funcionarioId;

    @Column(name = "data_hora_registro")
    private LocalDateTime dataHoraRegistro;

    @Column(name = "tipo_registro")
    private Integer tipoRegistro;

}
