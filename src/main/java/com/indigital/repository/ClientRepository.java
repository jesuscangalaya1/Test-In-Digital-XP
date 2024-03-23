package com.indigital.repository;

import com.indigital.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Page<ClientEntity> findAllByDeletedFalse(Pageable pageable);

    List<ClientEntity> findAllByDeletedFalse();

    Optional<ClientEntity> findByIdAndDeletedFalse(Long id);

    // Consulta para desactivar un cliente por su ID
    @Modifying
    @Query("UPDATE ClientEntity p SET p.deleted = true WHERE p.id = :clientId")
    void desactivarClient(@Param("clientId") Long clientId);
}
