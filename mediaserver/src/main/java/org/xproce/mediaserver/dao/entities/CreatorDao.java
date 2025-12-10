package org.xproce.mediaserver.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "creators")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDao {
    @Id
    private String id;
    private String name;
    private String email;
}
