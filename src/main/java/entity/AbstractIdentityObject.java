package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@MappedSuperclass
@ToString
public abstract class AbstractIdentityObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    Long id;
}
