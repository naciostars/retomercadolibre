package retomercadolibre.mutantes.apiRESTmutantes.beans;

import java.io.Serializable;
import java.util.Arrays;

public class DnaArray implements Serializable {

    private String[][] dna;

    public String[][] getDna() {
        return this.dna;
    }

    public void setDna(String[][] dna) {
        this.dna = dna;
    }

    public DnaArray() {

    }

    public DnaArray(String[][] dna) {
        this.dna = dna;
    }

    public int validateInfDna(Dna dnaString) {
        
        int flag = 0, acc = 0, acc2 = 0, lencontrol = 0, error = 0;
        String valstring[] = { "A", "T", "G", "C" };
        for (String dnaTmp : dnaString.getDna()) {
            if (flag == 0) {
                this.dna = new String[dnaString.getDna().length][dnaTmp.length()];
                lencontrol = dnaTmp.length();
                flag = 1;
            }
            if (lencontrol != dnaTmp.length()) {
                error = 1;
                break;
            }
            for (acc2 = 0; acc2 <= dnaTmp.length() - 1; acc2++) {
                if (!Arrays.asList(valstring).contains(dnaTmp.substring(acc2, acc2 + 1))) {
                    error = 2;
                    break;
                }

                this.dna[acc][acc2] = dnaTmp.substring(acc2, acc2 + 1);
            }
            acc++;
        }
        return error;
    }

    public boolean isMutant() {
        int acc = 0, acc2 = 0, cantMutant = 4;
        Boolean resp = false;
        valid: for (acc = 0; acc < this.dna.length; acc++) {
            for (acc2 = 0; acc2 < this.dna[0].length; acc2++) {
                if (valHorizontal(acc, acc2, cantMutant)) {
                    resp = true;
                    break valid;
                }

                if (valVertical(acc, acc2, cantMutant)) {
                    resp = true;
                    break valid;
                }

                if (valObliRDown(acc, acc2, cantMutant)) {
                    resp = true;
                    break valid;
                }

                if (valObliRUp(acc, acc2, cantMutant)) {
                    resp = true;
                    break valid;
                }
            }
        }
        return resp;
    }

    private boolean valHorizontal(int row, int col, int cantMutant) {
        boolean resp = false, flag = true;
        int acc = 0;
        if (col + cantMutant <= this.dna[0].length) {
            for (acc = 0; acc < cantMutant; acc++) {
                if (!this.dna[row][col].equals(this.dna[row][col + acc])) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                resp = true;
            }
        }
        return resp;
    }

    private boolean valVertical(int row, int col, int cantMutant) {
        boolean resp = false, flag = true;
        int acc = 0;
        if (row + cantMutant <= this.dna.length) {
            for (acc = 0; acc < cantMutant; acc++) {
                if (!this.dna[row][col].equals(this.dna[row + acc][col])) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                resp = true;
            }
        }
        return resp;
    }

    private boolean valObliRDown(int row, int col, int cantMutant) {
        boolean resp = false, flag = true;
        int acc = 0;
        if ((col + cantMutant <= this.dna[0].length) && (row + cantMutant <= this.dna.length)) {
            for (acc = 0; acc < cantMutant; acc++) {
                if (!this.dna[row][col].equals(this.dna[row + acc][col + acc])) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                resp = true;
            }
        }
        return resp;
    }

    private boolean valObliRUp(int row, int col, int cantMutant) {
        boolean resp = false, flag = true;
        int acc = 0;
        if ((col + 1 - cantMutant >= 0) && (row + cantMutant <= this.dna.length)) {
            for (acc = 0; acc < cantMutant; acc++) {
                if (!this.dna[row][col].equals(this.dna[row + acc][col - acc])) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                resp = true;
            }
        }
        return resp;
    }

}
