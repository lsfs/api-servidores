package com.seplag.processoseletivo.infra.storage;

import com.seplag.processoseletivo.domain.dto.FotoPessoaUpload;
import com.seplag.processoseletivo.domain.services.FotoStorageService;
import com.seplag.processoseletivo.infra.config.MinioPropertiesConfig;
import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemException;

@Service
public class MinioStorageService implements FotoStorageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucket;

    @Value("${minio.host-publico}")
    private String hostPublico;

    @Value("${minio.path-publico}")
    private String pathPublico;

    @Value("${minio.port-publico}")
    private int portPublico;

    private final int EXPIRE_SECONDS = 300;

    private final MinioPropertiesConfig minioProperties;

    public MinioStorageService(MinioClient minioClient, MinioPropertiesConfig minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    //:TODO - Acesso as fotos externamente

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
            String linkInterno = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(nomeArquivo)
                            .expiry(EXPIRE_SECONDS)
                            .build());

            URI inputURI = new URI(linkInterno);

            String pathOriginal = inputURI.getPath();
            String pathNovo = pathPublico + (pathOriginal.startsWith("/") ? pathOriginal : "/" + pathOriginal);

            return new URI(
                    inputURI.getScheme(),
                    inputURI.getUserInfo(),
                    hostPublico,
                    portPublico,
                    pathNovo,
                    inputURI.getQuery(),
                    inputURI.getFragment()
            ).toString();

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
