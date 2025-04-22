package zoo.application.dto;

import lombok.Getter;
import lombok.Setter;
import zoo.domain.model.Enclosure;
import zoo.domain.model.value_objects.EnclosureId;
import zoo.domain.model.value_objects.EnclosureType;

@Getter
@Setter
public class EnclosureDTO {
    private String id;
    private EnclosureType type;
    private double size;
    private int currentAnimals;
    private int maxCapacity;

    public EnclosureDTO(String id, EnclosureType type, double size,
                        int currentAnimals, int maxCapacity) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.currentAnimals = currentAnimals;
        this.maxCapacity = maxCapacity;
    }

    public static EnclosureDTO fromDomain(Enclosure enclosure) {
        return new EnclosureDTO(
                enclosure.getId().id(),
                enclosure.getType(),
                enclosure.getSize(),
                enclosure.getCurrentAnimals(),
                enclosure.getMaxCapacity()
        );
    }

    public Enclosure toDomain() {
        Enclosure enclosure = new Enclosure();
        enclosure.setId(new EnclosureId(this.id));
        enclosure.setType(this.type);
        enclosure.setSize(this.size);
        enclosure.setCurrentAnimals(this.currentAnimals);
        enclosure.setMaxCapacity(this.maxCapacity);
        return enclosure;
    }

}