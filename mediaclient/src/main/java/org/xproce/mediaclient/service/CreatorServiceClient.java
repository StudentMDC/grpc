package org.xproce.mediaclient.service;

import org.xproce.lab.Creator;
import org.xproce.lab.CreatorIdRequest;
import org.xproce.lab.CreatorServiceGrpc;
import org.xproce.lab.VideoStream;
import org.xproce.mediaclient.dto.CreatorDto;
import org.xproce.mediaclient.dto.VideoStreamDto;
import org.xproce.mediaclient.mapper.CreatorMapper;
import org.xproce.mediaclient.mapper.VideoMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    CreatorServiceGrpc.CreatorServiceBlockingStub stub;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    public CreatorDto getCreator(String creatorId) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(creatorId)
                .build();
        Creator creator = stub.getCreator(request);
        return creatorMapper.fromCreatorProtoToCreatorDto(creator);
    }

    public VideoStreamDto getCreatorVideos(String creatorId) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(creatorId)
                .build();
        VideoStream videoStream = stub.getCreatorVideos(request);
        return videoMapper.fromVideoStreamProtoToVideoStreamDto(videoStream);
    }
}


