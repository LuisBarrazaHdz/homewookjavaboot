package expeditors.backend.application;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.FilterDTO;
import expeditors.backend.adoption.TypeFilter;
import expeditors.backend.adoption.TypePet;
import expeditors.backend.config.AppConfig;
import expeditors.backend.service.AdopterService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //initializeWeek2();
        //initializeWeek3();
        //toStringMethod();
        //Week4And5();

        // >>>>> Week 6 <<<<<
        //Week6();
    }

    private static void initializeWeek2() {
        //Week 2
        /*
        LocalDate nowDate = LocalDate.now();

        Adopter adopterFirst = new Adopter();
        adopterFirst.setId(1);
        adopterFirst.setName("Pedro Hdz");
        adopterFirst.setPhoneNumber("167-102-0457");
        adopterFirst.setDateAdoption(nowDate);
        adopterFirst.setTypePet(TypePet.Dog);
        adopterFirst.setNamePet("Pelusa");
        adopterFirst.setBreedPet("Chihuahua");
        System.out.println(adopterFirst);

        Adopter adopterSecond = new Adopter();
        adopterSecond.setId(2);
        adopterSecond.setName("Alejandra Lopez");
        adopterSecond.setPhoneNumber("167-015-0522");
        adopterSecond.setDateAdoption(nowDate);
        adopterSecond.setTypePet(TypePet.Turtle);
        adopterSecond.setNamePet("Luna");
        adopterSecond.setBreedPet("Salt water");
        System.out.println(adopterSecond);*/
    }

    private static void initializeWeek3() {
        LocalDate nowDate = LocalDate.now();
        Adopter adopter = new Adopter(1,"Luis", "106-150-11",null, TypePet.Turtle);
        System.out.println(adopter);
    }

    /***
     * Week 3. Change your application class to use the Constructors to create a List of 2 Adopters and
     * print them out using a toString method.
     */
    private static void toStringMethod(){
        LocalDate nowDate = LocalDate.now();
        List<Adopter> listAdopter =  new ArrayList<Adopter>();
        Adopter adopterItem1 = new Adopter(1,"Luis", "106-150-11",nowDate,TypePet.Dog);
        Adopter adopterItem2 = new Adopter(2,"Lucia", "556-661-44",nowDate,TypePet.Cat, "Pelusa","Gato persa");
        listAdopter.add(adopterItem1);
        listAdopter.add(adopterItem2);
        System.out.println(listAdopter);
        /*
        for (Adopter item: listAdopter){
            System.out.println(item.toString());
        }
        System.out.println(Arrays.toString(listAdopter.toArray()));
        */
    }

    /*
    private  static  void Week4And5(){
        AdopterService adopterService = new AdopterService();

        LocalDate today = LocalDate.now();

        // INSERT
        System.out.println("### Insert ###");
        adopterService.addAdopter(new Adopter.AdopterBuilder()
                .name("Luis")
                .phoneNumber("106-150-11")
                .typePet(TypePet.Turtle)
                .namePet("Rocky")
                .breedPet("NA")
                .build());

        Adopter adopter2 = new Adopter(1,"Alejandra Lopez", "551-004-66",today.plusDays(10), TypePet.Cat, "Pelusa", "NA");
        adopterService.addAdopter(adopter2);

        Adopter adopter3 = new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(2), TypePet.Dog);
        adopterService.addAdopter(adopter3);

        Adopter adopter4 = new Adopter(1,"Melisa Rodriguez", "100-000-66",today.plusDays(3), TypePet.Turtle);
        adopterService.addAdopter(adopter4);
        adopterService.getAllAdopters().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //DELETE
        System.out.println("### Delete idAdopter=4 ###");
        adopterService.deleteAdopter(4);
        adopterService.getAllAdopters().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //UPDATE
        System.out.println("### Update idAdopter=3 ###");
        Adopter adopterUpdate = new Adopter(3,"Brenda Lopez", "111-004-00",null, TypePet.Dog);
        adopterService.updateAdopter(adopterUpdate);
        adopterService.getAllAdopters().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //Add functionality to your service to retrieve Adopters by name.
        System.out.println("### Filter by name 'Lopez'###");
        adopterService.getAdopterFilterByName("lopez").forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        System.out.println("### Retrieve Adopters by name ###");
        adopterService.getAdopterByName().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //Make your Adopter class sortable by “natural order”.
        System.out.println("### Order by name ###");
        adopterService.getOrderByName().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //Try and sort your Adopters by some other criteria, e.g. by date of adoption
        System.out.println("### Order by date adopter ###");
        adopterService.getOrderByDate().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //Optional -- Write a method called findBy, which will allow
        //you to search for Adopters by some user supplied criteria.
        System.out.println("### Method findBy ###");
        List<FilterDTO> filterDTOList = new ArrayList<>();
        filterDTOList.add(new FilterDTO(TypeFilter.NAME, "Luis"));
        //filterDTOList.add(new FilterDTO(TypeFilter.PHONENUMBER, "00"));
        filterDTOList.add(new FilterDTO(TypeFilter.TYPEPET, TypePet.Dog));

        adopterService.findBy(filterDTOList).forEach(System.out::println);
    }

*/
    private static void Week6(){
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.getEnvironment().setActiveProfiles("dev");
        appContext.register(AppConfig.class);
        appContext.refresh();

        AdopterService service = appContext.getBean("adopterService", AdopterService.class);

        service.addAdopter(new Adopter.AdopterBuilder()
                .name("Luis")
                .phoneNumber("106-150-11")
                .typePet(TypePet.Turtle)
                .namePet("Rocky")
                .breedPet("NA")
                .build());

        LocalDate today = LocalDate.now();
        service.addAdopter(new Adopter.AdopterBuilder()
                .name("Ana Ramirez")
                .dateAdoption(today.plusDays(5))
                .phoneNumber("106-150-11")
                .typePet(TypePet.Cat)
                .namePet("Pelusa")
                .breedPet("NA")
                .build());

        service.getAllAdopters().forEach(System.out::println);

        appContext.close();
    }
}
