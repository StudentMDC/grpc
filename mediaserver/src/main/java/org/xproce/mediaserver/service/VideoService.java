package org.xproce.mediaserver.service;

import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xproce.lab.VideoServiceGrpc;
import org.xproce.mediaserver.mapper.VideoMapper;
import io.grpc.stub.StreamObserver;

import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;


@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        Video video = videoMapper.fromUploadVideoRequestToVideo(request);
        responseObserver.onNext(video);
        responseObserver.onCompleted();
    }
}
