package expeditors.backend.service;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.FilterDTO;
import expeditors.backend.adoption.TypeFilter;
import expeditors.backend.dao.AdopterDAO;
import expeditors.backend.dao.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AdopterService {

    private AdopterDAO adopterDAO;

    public AdopterService(AdopterDAO adopterDAO) {
        this.adopterDAO = adopterDAO;
    }

    /*
    public AdopterService() {
        adopterDAO = DAOFactory.adopterDAO();
    }*/

    public Adopter addAdopter(Adopter adopter) {
        return adopterDAO.insert(adopter);
    }

    public boolean deleteAdopter(int idAdopter) {
        return adopterDAO.delete(idAdopter);
    }

    public boolean updateAdopter(Adopter adopter) {
        return adopterDAO.update(adopter);
    }

    public void setAdopterDAO(AdopterDAO adopterDAO){
        this.adopterDAO = adopterDAO;
    }

    public Adopter getAdopterById(int idAdopter){
        return adopterDAO.findById(idAdopter);
    }

    public List<Adopter> getAllAdopters() {
        return adopterDAO.findAll();
    }

    public List<Adopter> getAdopterFilterByName(String nameAdopter){
        return this.adopterDAO.findAll().stream().filter(adopter -> adopter.getName().toLowerCase().contains((nameAdopter).toLowerCase())).collect(Collectors.toList());
    }

    public List<String> getAdopterByName(){
        return this.adopterDAO.findAll().stream().map(Adopter::getName).collect(Collectors.toList());
    }

    public List<Adopter> getOrderByName(){
        return this.adopterDAO.findAll().stream().sorted(Comparator.comparing(Adopter::getName)).collect(Collectors.toList());
    }

    public List<Adopter> getOrderByDate(){
        return this.adopterDAO.findAll().stream().sorted(Comparator.comparing(Adopter::getDateAdoption)).collect(Collectors.toList());
    }


    private List<Predicate<Adopter>> getPredicate(List<FilterDTO> filterDTOList){
        List<Predicate<Adopter>> listPredicates = new ArrayList<>();
        filterDTOList.forEach(filter ->
        {
            switch (filter.getColumnName()) {
                case TypeFilter.NAME:
                    Predicate<Adopter> p1 = (f) -> f.getName().contains(filter.getColumnValue().toString());
                    listPredicates.add(p1);
                    break;
                case TypeFilter.PHONENUMBER:
                    Predicate<Adopter> p2 = (f) -> f.getPhoneNumber().contains(filter.getColumnValue().toString());
                    listPredicates.add(p2);
                    break;
                case TypeFilter.DATE:
                    Predicate<Adopter> p3 = (f) -> f.getDateAdoption().equals(filter.getColumnValue());
                    listPredicates.add(p3);
                    break;
                case TypeFilter.TYPEPET:
                    Predicate<Adopter> p4 = (f) -> f.getTypePet().equals(filter.getColumnValue());
                    listPredicates.add(p4);
                    break;
                case TypeFilter.NAMEPET:
                    Predicate<Adopter> p5 = (f) -> f.getNamePet().contains(filter.getColumnValue().toString());
                    listPredicates.add(p5);
                    break;
                case TypeFilter.BREEDPET:
                    Predicate<Adopter> p6 = (f) -> f.getBreedPet().contains(filter.getColumnValue().toString());
                    listPredicates.add(p6);
                    break;
            }
        });
        return listPredicates;
    }

    public List<Adopter> findBy(List<FilterDTO> filterDTO){
        List<Predicate<Adopter>> listPredicates = getPredicate(filterDTO);
        return this.adopterDAO.findAll().stream().filter(listPredicates.stream().reduce(x->false,Predicate::or)).collect(Collectors.toList());
    }

}
