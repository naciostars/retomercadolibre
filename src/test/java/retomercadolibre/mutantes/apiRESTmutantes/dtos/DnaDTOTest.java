package retomercadolibre.mutantes.apiRESTmutantes.dtos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Report;
import retomercadolibre.mutantes.apiRESTmutantes.repositories.DnaRepository;
import retomercadolibre.mutantes.apiRESTmutantes.services.DnaService;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class DnaDTOTest {
    DnaRepository dnaRepository;
    DnaService dnaService;
  
    @BeforeEach
    void setup() {
        dnaRepository = mock(DnaRepository.class);
        dnaService = new DnaService(dnaRepository);
    }

    @Test
    void contextLoad() {
    
        when(dnaRepository.countByType("H")).thenReturn(100L);
        when(dnaRepository.countByType("M")).thenReturn(40L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")).thenReturn(1L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGG\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")).thenReturn(1L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGG\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TTACTG\"]}")).thenReturn(0L);
        when(dnaRepository.countByDna("{\"dna\":[\"ATGCGG\",\"CAGTGC\",\"TTGTGT\",\"AGAATG\",\"CCCTTA\",\"TTACTT\"]}")).thenReturn(0L);
        
        DnaDTO dna = new DnaDTO();
        dna.setDna(new Dna (new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}));
        dna.setType("M");
        assertArrayEquals((new Dna (new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"})).getDna(), dna.getDna().getDna());
        DnaDTO dnaActual = new DnaDTO();
        dnaActual=dna.save(dnaRepository);
        Assertions.assertEquals(true,dnaActual.getExist());
        Assertions.assertEquals("El DNA es mutante, pero no se guardó en la BD debido a que ya esta registrado", dnaActual.getMessage());
        
        dna = new DnaDTO(new Dna (new String[]{"ATGCGG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}),"M");
        assertArrayEquals((new Dna (new String[]{"ATGCGG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"})).getDna(), dna.getDna().getDna());
        dnaActual = new DnaDTO();
        dnaActual=dna.save(dnaRepository);
        Assertions.assertEquals(true, dnaActual.getExist());
        Assertions.assertEquals("El DNA es mutante, pero no se guardó en la BD debido a que ya esta registrado",dnaActual.getMessage());

        dna = new DnaDTO(new Dna (new String[]{"ATGCGG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TTACTG"}),"M");
        assertArrayEquals((new Dna (new String[]{"ATGCGG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TTACTG"})).getDna(), dna.getDna().getDna());
        dnaActual = new DnaDTO();
        dnaActual=dna.save(dnaRepository);
        Assertions.assertEquals(false,dnaActual.getExist());
        Assertions.assertEquals("El DNA es mutante, es nuevo y se guardó satisfactoriamente en la BD",dnaActual.getMessage());

        dna = new DnaDTO(new Dna (new String[]{"ATGCGG","CAGTGC","TTGTGT","AGAATG","CCCTTA","TTACTT"}),"H");
        assertArrayEquals((new Dna (new String[]{"ATGCGG","CAGTGC","TTGTGT","AGAATG","CCCTTA","TTACTT"})).getDna(), dna.getDna().getDna());
        dnaActual = new DnaDTO();
        dnaActual=dna.save(dnaRepository);
        Assertions.assertEquals(dnaActual.getExist(), false);
        Assertions.assertEquals("El DNA es humano, es nuevo y se guardó satisfactoriamente en la BD",dnaActual.getMessage());

        dna = new DnaDTO();
        dna.setExist(true);
        dna.setMessage("Este es un mensaje de prueba");
        Assertions.assertEquals("Este es un mensaje de prueba", dna.getMessage());
        Assertions.assertEquals(true, dna.getExist());
        
        dna = new DnaDTO();
        Report report = dna.report(dnaRepository);
        Assertions.assertEquals(40L, report.getCount_mutant_dna());
        Assertions.assertEquals(100L, report.getCount_human_dna());
        Assertions.assertEquals(0.4F, report.getRatio());

    }
}
