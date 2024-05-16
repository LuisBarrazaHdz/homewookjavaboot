package expeditors.backend.db;

import expeditors.backend.adoption.AdopterNew;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        AdopterDAO<AdopterNew> adopterDao = new AdopterDAOImp();

        System.out.println(">>>>  create  <<<<");

        LocalDate nowDate = LocalDate.now();
        AdopterNew adopterNew = new AdopterNew(0, "Prueba","210-121-010", nowDate);
        adopterNew = adopterDao.create(adopterNew);
        System.out.println(adopterNew);

        System.out.println(">>>>  findById  <<<<");

        Optional<AdopterNew> optAdopter = adopterDao.findById(1);
        if(optAdopter.isPresent()) {
            AdopterNew adopter = optAdopter.get();

            System.out.println("Id: " + adopter.getIdAdopter());
            System.out.println("Name: " + adopter.getName());
            System.out.println("Phone: " + adopter.getPhoneNumber());
       }

        System.out.println(">>>>  findAll  <<<<");

        List<AdopterNew> adopters = adopterDao.findAll();

        for (AdopterNew adopter : adopters) {
            System.out.println("Id: " + adopter.getIdAdopter());
            System.out.println("Name: " + adopter.getName());
            System.out.println("Phone: " + adopter.getPhoneNumber());
        }


    }
}
