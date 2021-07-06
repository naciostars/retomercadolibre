package retomercadolibre.mutantes.apiRESTmutantes.dtos;

import java.io.Serializable;
import com.google.gson.Gson;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Report;
import retomercadolibre.mutantes.apiRESTmutantes.repositories.DnaRepository;
import retomercadolibre.mutantes.apiRESTmutantes.converters.GenericModelMapper;

public class DnaDTO implements Serializable {

    private String dna;
    private String type;
    private Boolean exist;
    private String message;

    public Dna getDna() {
        return new Gson().fromJson(this.dna, Dna.class);
    }

    public void setDna(Dna dna) {
        this.dna = new Gson().toJson(dna);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DnaDTO() {
        this.exist = false;
        this.message = "";
    }

    public DnaDTO(Dna dna, String type) {
        this.dna = new Gson().toJson(dna);
        this.type = type;
        this.exist = false;
        this.message = "";
    }

    public Report report(DnaRepository dnaRepository) {
        Report report = new Report();
        report.setCount_human_dna(dnaRepository.countByType("H"));
        report.setCount_mutant_dna(dnaRepository.countByType("M"));
        report.calRatio();
        return report;
    }

    public DnaDTO save(DnaRepository dnaRepository) {
        try {
            GenericModelMapper genericModelMapper = GenericModelMapper.singleInstance();
            DnaDTO dna = new DnaDTO(new Gson().fromJson(this.dna, Dna.class), this.type);
            String commsg = ", pero no se guardó en la BD debido a que ya esta registrado";
            dna.exist = true;
            dna.message = "El DNA es humano";
            if (this.type == "M") {
                dna.message = "El DNA es mutante";
            }
            if (dnaRepository.countByDna(dna.dna) <= 0) {
                dnaRepository.save(genericModelMapper.revertToDna(dna));
                dna.exist = false;
                commsg = ", es nuevo y se guardó satisfactoriamente en la BD";
            }
            dna.message = dna.message + commsg;
            return dna;
        } catch (Exception e) {
            DnaDTO dna = new DnaDTO();
            dna.type="E";
            dna.setMessage("Se genero una Excepcion: " + e.getMessage());
            return dna;
        }
    }
}
