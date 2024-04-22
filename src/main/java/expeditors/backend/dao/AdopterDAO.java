package expeditors.backend.dao;

import expeditors.backend.adoption.Adopter;

import java.util.List;

public interface AdopterDAO
{
    Adopter insert(Adopter adopter);

    boolean delete(int idAdopter);

    boolean update(Adopter adopter);

    Adopter findById(int idAdopter);

    List<Adopter> findAll();
}
