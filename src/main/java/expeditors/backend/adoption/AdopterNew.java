package expeditors.backend.adoption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@Builder
public class AdopterNew {
    private int idAdopter;
    private String name;
    private String phoneNumber;
    private LocalDate dateAdoption;
    //private Pet pet;

    public AdopterNew() {
    }

    public AdopterNew(int idAdopter, String name, String phoneNumber, LocalDate dateAdoption) {
        this.idAdopter = idAdopter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateAdoption = dateAdoption;
    }

    @Override
    public String toString() {
        return "AdopterNew{" +
                "idAdopter=" + idAdopter +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateAdoption=" + dateAdoption +
                '}';
    }
}
