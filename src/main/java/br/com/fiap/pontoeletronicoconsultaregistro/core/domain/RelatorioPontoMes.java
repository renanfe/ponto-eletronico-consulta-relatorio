package br.com.fiap.pontoeletronicoconsultaregistro.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Builder
public class RelatorioPontoMes {

    private List<RegistroPonto> registrosPontos;
    @Getter
    private Integer totalHorasTrabalhadas;
    @Getter
    private HashMap<UUID, List<RelatorioPontoEntradaSaida>> funcionarioEntradaESaida;

    public RelatorioPontoMes calcular() {
        this.funcionarioEntradaESaida = new HashMap<>();
        this.calcularHoras();
        this.calcularTotalHorasTrabalhadas();
        return this;
    }

    private void calcularHoras() {
        this.registrosPontos.stream()
                .sorted(Comparator.comparing(RegistroPonto::getFuncionarioId))
                .forEachOrdered(r -> {
                    List<RelatorioPontoEntradaSaida> lista = this.funcionarioEntradaESaida.computeIfAbsent(r.getFuncionarioId(), k -> new ArrayList<>());
                    if (r.getTipoRegistro() == 1) {
                        lista.add(RelatorioPontoEntradaSaida.builder()
                                .data(r.getDataHoraRegistro().toLocalDate())
                                .horaEntrada(r.getDataHoraRegistro())
                                .funcionarioId(r.getFuncionarioId())
                                .build());
                    } else {
                        lista.get(lista.size() - 1).setHoraSaida(r.getDataHoraRegistro());
                    }

                });
    }

    private void calcularTotalHorasTrabalhadas() {
        this.totalHorasTrabalhadas = this.funcionarioEntradaESaida.values().stream()
                .flatMap(List::stream)
                .mapToInt(RelatorioPontoEntradaSaida::getTotalHorasTrabalhadas)
                .sum();
    }

    public String createHtmlTable() {
        StringBuilder table = new StringBuilder("<table>");
        this.getFuncionarioEntradaESaida().forEach((k, v) -> {
            table.append(String.format("<tr class='header'><td colspan='3'>FUNCIONÁRIO: %s</td></tr>", k));
            table.append("<tr><th>Data/Hora Entrada</th><th>Data/Hora Saída</th><th>Horas Trabalhadas</th></tr>");
            for (RelatorioPontoEntradaSaida registro : v) {
                table.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                        formatHora(registro.getHoraEntrada()),
                        formatHora(registro.getHoraSaida()),
                        registro.getTotalHorasTrabalhadas()));
            }
        });
        table.append(String.format("<tr class='footer'><td colspan='2'>TOTAL DE HORAS TRABALHADAS</td><td>%s</td></tr></table>", this.getTotalHorasTrabalhadas()));
        return table.toString();
    }

    private String formatHora(LocalDateTime hora) {
        return Optional.ofNullable(hora)
                .map(h -> h.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)))
                .orElse("Sem registro");
    }

}
