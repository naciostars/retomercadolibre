package retomercadolibre.mutantes.apiRESTmutantes.services;

import org.springframework.http.ResponseEntity;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Report;

public interface DnaServiceInterface {
    ResponseEntity<String> saveDna(Dna dna); 
    Report report();
}
