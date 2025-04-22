package zoo.infrastructure.storage;

import org.springframework.stereotype.Repository;
import zoo.domain.model.Enclosure;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.repository.EnclosureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryEnclosureRepository implements EnclosureRepository {
    private final Map<EnclosureId, Enclosure> enclosures = new ConcurrentHashMap<>();

    @Override
    public Optional<Enclosure> findById(EnclosureId id) {
        return Optional.ofNullable(enclosures.get(id));
    }

    @Override
    public List<Enclosure> findAll() {
        return new ArrayList<>(enclosures.values());
    }

    @Override
    public Enclosure save(Enclosure enclosure) {
        enclosures.put(enclosure.getId(), enclosure);
        return enclosure;
    }

    @Override
    public void deleteById(EnclosureId id) {
        enclosures.remove(id);
    }

    @Override
    public long count() {
        return enclosures.size();
    }

    @Override
    public List<Enclosure> findByCurrentAnimalsLessThanMaxCapacity() {
        return enclosures.values().stream()
                .filter(e -> e.getCurrentAnimals() < e.getMaxCapacity())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> countByType() {
        return enclosures.values().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getType().name(),
                        Collectors.counting()
                ));
    }
}