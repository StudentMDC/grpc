package org.xproce.mediaserver.service;

import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xproce.lab.VideoIdRequest;
import org.xproce.lab.VideoServiceGrpc;
import org.xproce.mediaserver.dao.repositories.VideoRepository;
import org.xproce.mediaserver.mapper.VideoMapper;
import io.grpc.stub.StreamObserver;

import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;

import java.util.NoSuchElementException;


@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        var videoEntity = videoMapper.fromUploadVideoRequestToVideoDao(request);
        videoEntity = videoRepository.save(videoEntity);
        Video video = videoMapper.fromVideoDaoToVideo(videoEntity);

        responseObserver.onNext(video);
        responseObserver.onCompleted();
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        var videoEntity = videoRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Video not found with id: " + request.getId()));
        Video video = videoMapper.fromVideoDaoToVideo(videoEntity);

        responseObserver.onNext(video);
        responseObserver.onCompleted();
    }
}

