package retomercadolibre.mutantes.apiRESTmutantes.services;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Report;
import retomercadolibre.mutantes.apiRESTmutantes.models.DnaModel;
import retomercadolibre.mutantes.apiRESTmutantes.repositories.DnaRepository;

public class DnaServiceTest {
    @Autowired
    DnaRepository dnaRepository;
    
    @BeforeEach
    void setup() {
        dnaRepository = mock(DnaRepository.class);   
    }

    @Test
    void testSaveDna() {
        when(dnaRepository.countByType("H")).thenReturn(100L);
        when(dnaRepository.countByType("M")).thenReturn(40L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")).thenReturn(1L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGG\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")).thenReturn(1L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGG\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TTACTG\"]}")).thenReturn(0L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGG\",\"CAGTGC\",\"TTGTGT\",\"AGAATG\",\"CCCTTA\",\"TTACTT\"]}")).thenReturn(0L);
        when(dnaRepository.save(new DnaModel(new Dna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}),"M"))).thenReturn(new DnaModel(new Dna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}),"M"));
        when(dnaRepository.save(new DnaModel(new Dna(new String[]{"ATGCGG","CAGTGC","TTGTGT","AGAATG","CCCTTA","TTACTT"}),"M"))).thenReturn(new DnaModel(new Dna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}),"M"));
        
        DnaService dnaService = new DnaService(dnaRepository);
        ResponseEntity<String> respActual;
        respActual=dnaService.saveDna(new Dna (new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}));
        Assertions.assertEquals(200, respActual.getStatusCodeValue());

        dnaService = new DnaService(dnaRepository);
        respActual=dnaService.saveDna(new Dna (new String[]{"ATGCGG","CAGTGC","TTGTGT","AGAATG","CCCTTA","TTACTT"}));
        Assertions.assertEquals(403, respActual.getStatusCodeValue());
        
        dnaService = new DnaService(dnaRepository);
        respActual=dnaService.saveDna(new Dna (new String[]{"ATGCGG","CAGTC","TTGTGT","AGAATG","CCCTTA","TTACTT"}));
        Assertions.assertEquals(500, respActual.getStatusCodeValue());
        
        dnaService = new DnaService(dnaRepository);
        respActual=dnaService.saveDna(new Dna (new String[]{"ATGCGG","CRAGTC","TTGTGT","AGAATG","CCCTTA","TTACTT"}));
        Assertions.assertEquals(500, respActual.getStatusCodeValue());

        dnaService = new DnaService(dnaRepository);
        Report report = new Report();
        report=dnaService.report();
        Assertions.assertEquals(40L, report.getCount_mutant_dna());
        Assertions.assertEquals(100L, report.getCount_human_dna());
        Assertions.assertEquals(0.4F, report.getRatio());

        
    }
}
