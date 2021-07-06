package retomercadolibre.mutantes.apiRESTmutantes.services;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Dna;
import retomercadolibre.mutantes.apiRESTmutantes.beans.DnaArray;
import retomercadolibre.mutantes.apiRESTmutantes.beans.Report;
import retomercadolibre.mutantes.apiRESTmutantes.dtos.DnaDTO;
import retomercadolibre.mutantes.apiRESTmutantes.repositories.DnaRepository;

@Service
public class DnaService implements DnaServiceInterface {
    @Autowired
    DnaRepository dnaRepository;

    
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public ResponseEntity<String> saveDna(Dna dna) {
        Boolean isMutant = false;
        DnaArray dnaArray = new DnaArray();
        ResponseEntity<String> resp;
        DnaDTO dnaDef = new DnaDTO(dna, "H");
        switch (dnaArray.validateInfDna(dna)) {
            case 0: 
                isMutant = dnaArray.isMutant();
                if (isMutant == true) {
                    dnaDef.setType("M");
                }
                dnaDef=dnaDef.save(dnaRepository);
                resp=ResponseEntity.status(403).body(new Gson().toJson(dnaDef));
                if (isMutant == true) {
                    resp=ResponseEntity.status(200).body(new Gson().toJson(dnaDef));
                }
                break;
            case 1:
                // La información enviada como DNA presenta inconsistencias, las tramas no son
                // del mismo tamaño
                dnaDef.setType("E");
                dnaDef.setMessage("La información enviada como DNA presenta inconsistencias, las tramas no son del mismo tamaño");
                resp=ResponseEntity.status(500).body(new Gson().toJson(dnaDef));
                break;
            case 2:
                // La información enviada como DNA presenta inconsistencias, los datos tienen
                // información erronea
                dnaDef.setType("E");
                dnaDef.setMessage("La información enviada como DNA presenta inconsistencias, los datos tienen información erronea");
                resp=ResponseEntity.status(500).body(new Gson().toJson(dnaDef));
                break;
            default:
                // Error no determinado
                dnaDef.setType("E");
                dnaDef.setMessage("Error no determinado");
                resp=ResponseEntity.status(500).body(new Gson().toJson(dnaDef));
                break;
        }
        
        return resp;
    }

    public Report report() {
        DnaDTO dna = new DnaDTO();
        return dna.report(dnaRepository);
    }

}
