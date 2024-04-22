package expeditors.backend.adoption;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Adopter {

    private int id;
    private String name;
    private String phoneNumber;
    private LocalDate dateAdoption;
    private TypePet typePet;
    private String namePet;
    private String breedPet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else
            throw new IllegalArgumentException("id is not valid");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateAdoption() {
        return dateAdoption;
    }

    public void setDateAdoption(LocalDate dateAdoption) {
        this.dateAdoption = Objects.requireNonNullElseGet(dateAdoption, LocalDate::now);
    }

    public TypePet getTypePet() {
        return typePet;
    }

    public void setTypePet(TypePet typePet) {
        this.typePet = typePet;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getBreedPet() {
        return breedPet;
    }

    public void setBreedPet(String breedPet) {
        this.breedPet = breedPet;
    }

    @Override
    public String toString() {
        return "Adopter{" + "\n" +
                " id=" + id + "\n" +
                " name='" + name + "',\n" +
                " phoneNumber='" + phoneNumber + "',\n" +
                " dateAdoption=" + dateAdoption + "',\n" +
                " typePet=" + typePet + "',\n" +
                " namePet='" + namePet + "',\n" +
                " breedPet='" + breedPet + "',\n" +
                '}';
    }

    public Adopter(String name, String phoneNumber, LocalDate dateAdoption, TypePet typePet){
        setName(Objects.requireNonNull(name, "name is required"));
        setPhoneNumber(Objects.requireNonNull(phoneNumber, "phoneNumber is required"));
        setDateAdoption(dateAdoption);
        setTypePet(Objects.requireNonNull(typePet, "typePet is required"));
    }

    public Adopter(String name, String phoneNumber, LocalDate dateAdoption, TypePet typePet, String namePet, String breedPet){
        this(name,phoneNumber,dateAdoption, typePet);
        setNamePet(Objects.requireNonNull(namePet, "namePet is required"));
        setBreedPet(Objects.requireNonNull(breedPet, "breedPet is required"));
    }

    public Adopter(int id, String name, String phoneNumber, LocalDate dateAdoption, TypePet typePet){
        setId(id);
        setName(Objects.requireNonNull(name, "name is required"));
        setPhoneNumber(Objects.requireNonNull(phoneNumber, "phoneNumber is required"));
        setDateAdoption(dateAdoption);
        setTypePet(Objects.requireNonNull(typePet, "typePet is required"));
    }

    public Adopter(int id, String name, String phoneNumber, LocalDate dateAdoption, TypePet typePet, String namePet, String breedPet){
        this(id,name,phoneNumber,dateAdoption, typePet);
        setNamePet(Objects.requireNonNull(namePet, "namePet is required"));
        setBreedPet(Objects.requireNonNull(breedPet, "breedPet is required"));
    }

    public static class AdopterBuilder {
        private String name;
        private String phoneNumber;
        private LocalDate dateAdoption;
        private TypePet typePet;
        private String namePet;
        private String breedPet;

        public AdopterBuilder name(String name) {
            this.name = name;
            return this;
        }
        public AdopterBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public AdopterBuilder dateAdoption(LocalDate dateAdoption) {
            this.dateAdoption = dateAdoption;
            return this;
        }
        public AdopterBuilder typePet(TypePet typePet) {
            this.typePet = typePet;
            return this;
        }
        public AdopterBuilder namePet(String namePet) {
            this.namePet = namePet;
            return this;
        }
        public AdopterBuilder breedPet(String breedPet) {
            this.breedPet = breedPet;
            return this;
        }

        public Adopter build() {
            if(this.name == null) {
                throw new RuntimeException("Name is REQUIRED");
            }
            return new Adopter(name, phoneNumber, dateAdoption, typePet, namePet, breedPet);
        }
    }
}
