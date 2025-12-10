package org.xproce.mediaserver.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.mediaserver.dao.entities.CreatorDao;

public interface CreatorRepository extends JpaRepository<CreatorDao, String> {
}
