package retomercadolibre.mutantes.apiRESTmutantes.controllers;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Report;
import retomercadolibre.mutantes.apiRESTmutantes.services.DnaService;

@RestController
@RequestMapping("/stats")
public class ReportController {
    @Autowired
    DnaService dnaService;

    @GetMapping()
    @Transactional
    public Report getDnaStatistics() {
        return dnaService.report();
    }

}
