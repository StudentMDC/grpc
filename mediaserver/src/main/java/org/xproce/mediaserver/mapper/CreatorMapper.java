package org.xproce.mediaserver.mapper;

import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.mediaserver.dao.entities.CreatorDao;

@Component
public class CreatorMapper {
    public Creator fromCreatorDaoToCreator(CreatorDao entity) {
        return Creator.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();
    }
}
