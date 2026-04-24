package SwaggerPetstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Pet {
    private long id;
    private String name;
    private String status;

    // Pusty konstruktor (wymagany przez biblioteki do JSONa)
    public Pet() {}

    // Pełny konstruktor
    public Pet(long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Gettery i Settery
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}