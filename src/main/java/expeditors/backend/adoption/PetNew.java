package expeditors.backend.adoption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PetNew {
    private int idPet;
    private int idAdopter;
    private int idTypePet;
    private String namePet;
    private String breedPet;

    public PetNew(int idPet, int idAdopter, int idTypePet, String namePet, String breedPet) {
        this.idPet = idPet;
        this.idAdopter = idAdopter;
        this.idTypePet = idTypePet;
        this.namePet = namePet;
        this.breedPet = breedPet;
    }

    @Override
    public String toString() {
        return "PetNew{" +
                "idPet=" + idPet +
                ", idAdopter=" + idAdopter +
                ", idTypePet=" + idTypePet +
                ", namePet='" + namePet + '\'' +
                ", breedPet='" + breedPet + '\'' +
                '}';
    }
}
