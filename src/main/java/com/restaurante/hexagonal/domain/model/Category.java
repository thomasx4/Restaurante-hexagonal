package  com.restaurante.hexagonal.domain.model;
import lombok.Data;

@Data
public class Category {

    private Long id;
    private String name;
    private String description;

}