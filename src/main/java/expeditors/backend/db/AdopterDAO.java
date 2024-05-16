package expeditors.backend.db;

import java.util.List;
import java.util.Optional;

public interface AdopterDAO<T> {
    Optional<T> findById(long id);

    List<T> findAll();

    T create(T t);

    T update(T t);

    int[] update (List<T> t);

    int delete(T t);
}
