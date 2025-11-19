package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {
}
