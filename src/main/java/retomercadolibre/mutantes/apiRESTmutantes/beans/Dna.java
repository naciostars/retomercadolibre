package retomercadolibre.mutantes.apiRESTmutantes.beans;

import java.io.Serializable;

public class Dna implements Serializable {

    private String[] dna;

    public String[] getDna() {
        return this.dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Dna() {

    }

    public Dna(String[] dna) {
        this.dna = dna;
    }

}
