package retomercadolibre.mutantes.apiRESTmutantes.beans;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DnaArrayTest {
    @Test
    void testIsMutant() {
        DnaArray dnaArray = new DnaArray();
        Dna dna= new Dna();
        int expected;
        int actual;
        
        dna.setDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
        expected=0;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(true, dnaArray.isMutant());

        dna.setDna(new String[]{"ATGCGA","CCGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
        expected=0;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(true, dnaArray.isMutant());

        
        dna.setDna(new String[]{"ATGCGA","CCGTGC","TTATGT","AGAATG","CCCCTA","TCACTG"});
        expected=0;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(true, dnaArray.isMutant());

        dna.setDna(new String[]{"ATGCGA","CCCTGC","TCATGT","CGAATG","CCCATA","TCACTG"});
        expected=0;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(true, dnaArray.isMutant());

        dna.setDna(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"});
        expected=0;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(false, dnaArray.isMutant());

        dna.setDna(new String[]{"ATGCGA","CAGT","TTATGT","AGAAGG","CCCCTA","TCACTG"});
        expected=1;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);
        
        dna.setDna(new String[]{"ATGCGA","CAGTGC","TZATGT","AGAAGG","CCCCTA","TCACTG"});
        expected=2;
        actual=dnaArray.validateInfDna(dna);
        Assertions.assertEquals(expected, actual);

        dnaArray.setDna(new String[][]{{"A","T","G","C","G","A"},
                                       {"C","A","G","T","G","C"},
                                       {"T","Z","A","T","G","T"},
                                       {"A","G","A","A","G","G"},
                                       {"C","C","C","C","T","A"},
                                       {"T","C","A","C","T","G"}});        
        assertArrayEquals(new String[][]{{"A","T","G","C","G","A"},
                                         {"C","A","G","T","G","C"},
                                         {"T","Z","A","T","G","T"},
                                         {"A","G","A","A","G","G"},
                                         {"C","C","C","C","T","A"},
                                         {"T","C","A","C","T","G"}}, dnaArray.getDna());
    

        DnaArray dnaArrayAux = new DnaArray( new String[][]{{"A","T","G","C","G","A"},
                                                            {"C","A","G","T","G","C"},
                                                            {"T","Z","A","T","G","T"},
                                                            {"A","G","A","A","G","G"},
                                                            {"C","C","C","C","T","A"},
                                                            {"T","C","A","C","T","G"}});
                           assertArrayEquals(new String[][]{{"A","T","G","C","G","A"},
                                                            {"C","A","G","T","G","C"},
                                                            {"T","Z","A","T","G","T"},
                                                            {"A","G","A","A","G","G"},
                                                            {"C","C","C","C","T","A"},
                                                            {"T","C","A","C","T","G"}}, dnaArrayAux.getDna());
        
    }
}
