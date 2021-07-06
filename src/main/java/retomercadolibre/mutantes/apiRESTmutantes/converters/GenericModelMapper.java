package retomercadolibre.mutantes.apiRESTmutantes.converters;

import org.modelmapper.ModelMapper;
import retomercadolibre.mutantes.apiRESTmutantes.dtos.*;
import retomercadolibre.mutantes.apiRESTmutantes.models.DnaModel;

public class GenericModelMapper {
    private final ModelMapper mapper = new ModelMapper();
    private static GenericModelMapper instance;

    private GenericModelMapper() {

    }

    public static GenericModelMapper singleInstance() {

        if (instance == null) {
            instance = new GenericModelMapper();
        }
        return instance;
    }

    public DnaDTO mapToDnaDTO(DnaModel dna) {
        return mapper.map(dna, DnaDTO.class);
    }

    public DnaModel revertToDna(DnaDTO dna) {
        return mapper.map(dna, DnaModel.class);
    }

}
