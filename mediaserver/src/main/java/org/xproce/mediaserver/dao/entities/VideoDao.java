package org.xproce.mediaserver.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDao {
    @Id
    private String id;
    private String title;
    private String description;
    private String url;
    private Integer duration_seconds;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private CreatorDao creator;
}