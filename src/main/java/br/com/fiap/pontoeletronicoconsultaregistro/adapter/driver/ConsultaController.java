package br.com.fiap.pontoeletronicoconsultaregistro.adapter.driver;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.service.ConsultaService;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RegistroPonto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService){
        this.consultaService = consultaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RegistroPonto>> buscarRegistroPontoPorFuncionario(@PathVariable UUID id){
        return this.consultaService.consultarRegistro(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
