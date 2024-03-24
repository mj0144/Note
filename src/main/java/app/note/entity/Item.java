package app.note.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Long price;
    private String category;
    private int count;
}
