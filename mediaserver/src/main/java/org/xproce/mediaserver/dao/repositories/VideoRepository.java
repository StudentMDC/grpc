package org.xproce.mediaserver.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.mediaserver.dao.entities.VideoDao;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoDao, String> {
    List<VideoDao> findByCreatorId(String creatorId);

}
