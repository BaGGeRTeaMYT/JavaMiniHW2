package zoo.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zoo.application.dto.EnclosureDTO;
import zoo.domain.model.Enclosure;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.repository.EnclosureRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {
    private final EnclosureRepository enclosureRepository;

    public EnclosureController(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    @GetMapping
    public List<EnclosureDTO> getAllEnclosures() {
        return enclosureRepository.findAll().stream()
                .map(EnclosureDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnclosureDTO> getEnclosureById(@PathVariable String id) {
        return enclosureRepository.findById(new EnclosureId(id))
                .map(EnclosureDTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnclosureDTO addEnclosure(@RequestBody EnclosureDTO enclosureDTO) {
        Enclosure enclosure = enclosureDTO.toDomain();
        enclosure = enclosureRepository.save(enclosure);
        return EnclosureDTO.fromDomain(enclosure);
    }

    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable String id) {
        enclosureRepository.deleteById(new EnclosureId(id));
    }

    @PostMapping("/{id}/clean")
    public void cleanEnclosure(@PathVariable String id) {
        Enclosure enclosure = enclosureRepository.findById(new EnclosureId(id))
                .orElseThrow(() -> new IllegalArgumentException("Enclosure not found"));
        enclosure.clean();
        enclosureRepository.save(enclosure);
    }
}
