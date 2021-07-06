package retomercadolibre.mutantes.apiRESTmutantes.models;

import java.util.UUID;
import javax.persistence.*;
import com.google.gson.Gson;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;

@Entity
@Table(name = "dna")
public class DnaModel {

    // unique id for every adn procesed
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private UUID id;

    // field that stores DNA information
    @Column(columnDefinition = "VARCHAR(500)")
    private String dna;

    // field that identifies if it is a human or mutant DNA
    @Column(columnDefinition = "CHAR(1)")
    private String type;

    // getters and// // getters and setters section

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public DnaModel() {

    }

    public DnaModel(Dna dna, String type) {
        this.dna = new Gson().toJson(dna);
        this.type = type;
    }
}
