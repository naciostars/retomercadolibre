package retomercadolibre.mutantes.apiRESTmutantes.beans;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class DnaTest {
    @Test
    void testSetDna() {
        String[] expected;
        String[] actual;
        Dna dna= new Dna();
        
        dna.setDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
        expected = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        actual = dna.getDna();
        assertArrayEquals(expected, actual);
        
        dna= new Dna(new String[]{"AAAAAA","CCCCCC","TTTTTT","GGGGGG","AAAAAA","CCCCCC"});
        expected = new String[]{"AAAAAA","CCCCCC","TTTTTT","GGGGGG","AAAAAA","CCCCCC"};
        actual = dna.getDna();
        assertArrayEquals(expected, actual);
    
    }
}
