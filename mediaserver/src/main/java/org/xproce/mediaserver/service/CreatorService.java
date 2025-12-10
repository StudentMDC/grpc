package org.xproce.mediaserver.service;

import org.xproce.lab.Creator;
import org.xproce.lab.CreatorIdRequest;
import org.xproce.lab.CreatorServiceGrpc;
import org.xproce.lab.Video;
import org.xproce.lab.VideoStream;
import org.xproce.mediaserver.dao.entities.VideoDao;
import org.xproce.mediaserver.mapper.CreatorMapper;
import org.xproce.mediaserver.mapper.VideoMapper;
import org.xproce.mediaserver.dao.repositories.VideoRepository;
import org.xproce.mediaserver.dao.repositories.CreatorRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@GrpcService
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void getCreator(CreatorIdRequest request, StreamObserver<Creator> responseObserver) {
        var creatorEntity = creatorRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Creator not found with id: " + request.getId()));
        Creator creator = creatorMapper.fromCreatorDaoToCreator(creatorEntity);

        responseObserver.onNext(creator);
        responseObserver.onCompleted();
    }

    @Override
    public void getCreatorVideos(CreatorIdRequest request, StreamObserver<VideoStream> responseObserver) {
        List<VideoDao> videoEntities = videoRepository.findByCreatorId(request.getId());

        List<Video> videos = videoEntities.stream()
                .map(videoMapper::fromVideoDaoToVideo)
                .collect(Collectors.toList());

        VideoStream videoStream = VideoStream.newBuilder()
                .addAllVideos(videos)
                .build();

        responseObserver.onNext(videoStream);
        responseObserver.onCompleted();
    }
}

