package retomercadolibre.mutantes.apiRESTmutantes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;
import retomercadolibre.mutantes.apiRESTmutantes.services.DnaService;

@RestController
@RequestMapping("/mutant")
public class DnaController {
    @Autowired
    DnaService dnaService;

    @PostMapping()
    public ResponseEntity<String> classifyDna(@RequestBody Dna dna) {
        return dnaService.saveDna(dna);
    }

}
