package expeditors.backend.adoption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class AdopterPet {
    private int idAdopter;
    private String name;
    private String phoneNumber;
    private LocalDate dateAdoption;
    private int idPet;
    private int idTypePet;
    private String namePet;
    private String breedPet;

    public AdopterPet(int idAdopter, String name, String phoneNumber, LocalDate dateAdoption, int idPet, int idTypePet, String namePet, String breedPet) {
        this.idAdopter = idAdopter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateAdoption = dateAdoption;
        this.idPet = idPet;
        this.idTypePet = idTypePet;
        this.namePet = namePet;
        this.breedPet = breedPet;
    }

    @Override
    public String toString() {
        return "AdopterPet{" +
                "idAdopter=" + idAdopter +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateAdoption=" + dateAdoption +
                ", idPet=" + idPet +
                ", idTypePet=" + idTypePet +
                ", namePet='" + namePet + '\'' +
                ", breedPet='" + breedPet + '\'' +
                '}';
    }
}
