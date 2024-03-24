package br.com.fiap.pontoeletronicoconsultaregistro.adapter.driver;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.service.RelatorioService;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RelatorioPontoMes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService){
        this.relatorioService = relatorioService;
    }
    @GetMapping("/{mes}")
    public ResponseEntity<String> gerarRelatorioMes(@PathVariable Integer mes, @RequestParam("email") String email){
        return this.relatorioService.gerarRelatorioPorMes(mes, email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
