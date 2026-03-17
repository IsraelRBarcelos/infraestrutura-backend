package com.agenciacapsula.infraestrutura.domain.ports.out;

public interface PhotoStoragePort {
    String store(byte[] fileBytes, String fileName);
}