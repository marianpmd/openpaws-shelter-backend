package com.piug.piugbackend.entity;

import com.piug.piugbackend.common.AnimalStatus;
import com.piug.piugbackend.common.AnimalType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    private int age;

    private String description;

    @Enumerated(EnumType.STRING)
    private AnimalStatus status = AnimalStatus.IN_CUSTODY;

    @OneToMany(mappedBy = "animalEntity")
    @ToString.Exclude
    private List<ActionEntity> actionEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalEntity that = (AnimalEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
