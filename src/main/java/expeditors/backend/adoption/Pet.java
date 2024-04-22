package expeditors.backend.adoption;

public class Pet {
    private TypePet typePet;
    private String namePet;
    private String breedPet;
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

    public Pet(TypePet typePet, String namePet, String breedPet){
        setTypePet(typePet);
        setNamePet(namePet);
        setBreedPet(breedPet);
    }

    public static PetBuilder builder(TypePet type) {
        return new PetBuilder().type(type);
    }

    public static PetBuilder builder(TypePet type, String name, String breed) {
        return new PetBuilder().type(type).name(name).breed(breed);
    }

    public static class PetBuilder {
        private TypePet type;
        private String name;
        private String breed;

        public PetBuilder type(TypePet type) {
            this.type = type;
            return this;
        }

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }
        public PetBuilder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public Pet build() {
            if(this.type == null) {
                throw new RuntimeException("Type is REQUIRED");
            }
            return new Pet(type, name, breed);
        }
    }

}
