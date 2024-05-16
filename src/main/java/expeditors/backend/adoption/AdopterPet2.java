package expeditors.backend.adoption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class AdopterPet2 {
    private int idAdopter;
    private String name;
    private String phoneNumber;
    private LocalDate dateAdoption;
    private List<PetNew> pets = new ArrayList<>();

    public AdopterPet2(int idAdopter, String name, String phoneNumber, LocalDate dateAdoption, List<PetNew> pets) {
        this.idAdopter = idAdopter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateAdoption = dateAdoption;
        this.pets = pets;
    }
}
