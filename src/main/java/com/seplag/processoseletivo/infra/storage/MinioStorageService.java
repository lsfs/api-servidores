package com.seplag.processoseletivo.infra.storage;

import com.seplag.processoseletivo.domain.dto.FotoPessoaUpload;
import com.seplag.processoseletivo.domain.services.FotoStorageService;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MinioStorageService implements FotoStorageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucket;
    private final int EXPIRE_SECONDS = 300;

    public MinioStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void salvar(FotoPessoaUpload foto) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(foto.nomeArquivo())
                            .stream(foto.inputStream(), -1, 10485760)
                            .contentType(foto.contentType())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar arquivo no MinIO", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(nomeArquivo)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover arquivo no MinIO", e);
        }
    }

    @Override
    public String gerarLinkTemporario(String nomeArquivo) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(nomeArquivo)
                            .expiry(EXPIRE_SECONDS)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar link temporário para imagem armazenada", e);
        }
    }

    @PostConstruct
    public void init() {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucket)
                    .build());

            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucket)
                        .build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar/criar bucket no MinIO", e);
        }
    }
}
