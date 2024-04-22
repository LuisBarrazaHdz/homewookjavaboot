package expeditors.backend.dao.jpa;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.dao.AdopterDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Profile("prod")
public class JpaAdopterDAO implements AdopterDAO {
    private Map<Integer, Adopter> adopters = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);
    @Override
    public Adopter insert(Adopter adopter) {
        adopter.setId(nextId.getAndIncrement());
        adopter.setName("Jpa: " + adopter.getName());
        this.adopters.put(adopter.getId(), adopter);
        return adopter;
    }

    @Override
    public boolean delete(int idAdopter) {
        return this.adopters.remove(idAdopter) != null;
    }

    @Override
    public boolean update(Adopter adopter) {
        return this.adopters.replace(adopter.getId(), adopter) != null;
    }

    @Override
    public Adopter findById(int idAdopter) {
        return this.adopters.get(idAdopter);
    }

    @Override
    public List<Adopter> findAll() {
        return new ArrayList<>(adopters.values());
    }
}
