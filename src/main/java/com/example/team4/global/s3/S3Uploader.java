package com.example.team4.global.s3;


import static org.springframework.web.util.UriUtils.extractFileExtension;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.team4.domain.entity.S3Info;
import com.example.team4.global.error.AppException;
import com.example.team4.global.error.ErrorCode;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private static final String CONTENT_TYPE = "multipart/formed-data";
    private final AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3Info uploadFiles(MultipartFile multipartFile, String folderName) throws AppException {
        log.info("{} 사진 업로드", multipartFile.getOriginalFilename());
        File localUploadFile = convert(multipartFile);

        return uploadFiles(localUploadFile, folderName);
    }

    public S3Info uploadFiles(File file, String folderName) {
        String fileName = folderName + "/" + file.getName();
        String uploadUrl = putS3(file, fileName);
        file.delete();

        return S3Info.builder()
                .folderName(folderName)
                .fileName(file.getName())
                .url(uploadUrl)
                .build();
    }

    public void deleteFile(S3Info s3Info) {
        String fileName = s3Info.getFolderName() + "/" + s3Info.getFileName();

        log.info("{} 사진 삭제", fileName);
        amazonS3.deleteObject(bucket, fileName);
    }

    private String putS3(File uploadFile, String fileName) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType(CONTENT_TYPE);
        amazonS3.putObject(putObjectRequest);

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public static File convert(MultipartFile file) throws AppException {
        String fileExtension = extractFileExtension(file);
        File convertFile = new File(System.getProperty("user.dir") + "/" + UUID.randomUUID() + "." + fileExtension);

        try {
            FileOutputStream fos = new FileOutputStream(convertFile);
            fos.write(file.getBytes());
            fos.close();

            return convertFile;
        } catch (IOException e) {
            throw new AppException(ErrorCode.IMAGE_PROCESSING_FAIL);
        }
    }

    public static String extractFileExtension(MultipartFile file) throws AppException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new AppException(ErrorCode.IMAGE_PROCESSING_FAIL);
        }

        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }
}

