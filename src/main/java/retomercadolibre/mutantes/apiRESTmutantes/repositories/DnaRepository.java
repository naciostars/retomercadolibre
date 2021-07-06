package retomercadolibre.mutantes.apiRESTmutantes.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import retomercadolibre.mutantes.apiRESTmutantes.models.DnaModel;

@Repository
public interface DnaRepository extends CrudRepository<DnaModel, UUID> {

    long countByType(String type);

    long countByDna(String Dna);
}
