package zoo.domain.repository;

import zoo.domain.model.Enclosure;
import zoo.domain.model.value_objects.EnclosureId;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EnclosureRepository {
    Optional<Enclosure> findById(EnclosureId id);
    List<Enclosure> findAll();
    Enclosure save(Enclosure enclosure);
    void deleteById(EnclosureId id);
    long count();
    List<Enclosure> findByCurrentAnimalsLessThanMaxCapacity();
    Map<String, Long> countByType();
}
