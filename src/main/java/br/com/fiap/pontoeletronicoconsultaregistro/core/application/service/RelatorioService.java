package br.com.fiap.pontoeletronicoconsultaregistro.core.application.service;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.ports.RegistroPontoRepository;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RegistroPonto;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RelatorioPontoMes;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    private final RegistroPontoRepository registroPontoRepository;

    private final EmailService emailService;

    public RelatorioService(RegistroPontoRepository registroPontoRepository, EmailService emailService) {
        this.registroPontoRepository = registroPontoRepository;
        this.emailService = emailService;
    }

    public Optional<String> gerarRelatorioPorMes (Integer mes, String email) {
        return registroPontoRepository.buscarRegistrosPontoPorMes(mes)
                .map(this::gerarRelatorio)
                .map(relatorio -> publicarRelatorio(relatorio, email))
                .map(relatorioPontoMes -> "Você irá receber um email com o relatório solicitado" );
    }

    public RelatorioPontoMes gerarRelatorio (List<RegistroPonto> registrosPontos) {
        return RelatorioPontoMes.builder()
                .registrosPontos(registrosPontos)
                .build()
                .calcular();
    }

    public RelatorioPontoMes publicarRelatorio (RelatorioPontoMes relatorioPontoMes, String emailTo) {
        try {
            this.emailService.sendEmail(emailTo, "Relatório de registro ponto", relatorioPontoMes);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return relatorioPontoMes;
    }
}
