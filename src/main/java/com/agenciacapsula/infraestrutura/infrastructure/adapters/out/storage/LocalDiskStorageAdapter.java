package com.agenciacapsula.infraestrutura.infrastructure.adapters.out.storage;

import com.agenciacapsula.infraestrutura.domain.ports.out.PhotoStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class LocalDiskStorageAdapter implements PhotoStoragePort {

    @Value("${app.storage.local-path:/tmp/fallback-photos}")
    private String uploadDir;

    @Override
    public String store(byte[] fileBytes, String fileName) {
        try {
            Path directory = Paths.get(uploadDir);
            if (!Files.exists(directory)) Files.createDirectories(directory);

            String uniqueName = UUID.randomUUID() + "_" + fileName + ".png";
            Path filePath = directory.resolve(uniqueName);
            Files.write(filePath, fileBytes);

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar arquivo no bucket", e);
        }
    }
}