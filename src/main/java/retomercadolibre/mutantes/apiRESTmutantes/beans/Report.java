package retomercadolibre.mutantes.apiRESTmutantes.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Report implements Serializable {

    private long count_mutant_dna;
    private long count_human_dna;
    private float ratio;

    public long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(long count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public long getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(long count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public Report() {
        this.count_mutant_dna = 0;
        this.count_human_dna = 0;
        this.ratio = 0;
    }

    public Report(long count_mutant_dna, long count_human_dna, float ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public void calRatio() {

        if (this.count_human_dna == 0){
            this.ratio = (float) this.count_mutant_dna;
        }else{
            this.ratio = (float) this.count_mutant_dna / ((float) this.count_human_dna);
            BigDecimal bd = new BigDecimal(this.ratio).setScale(2, RoundingMode.HALF_UP);
            this.ratio = bd.floatValue();
        }
    }
}
