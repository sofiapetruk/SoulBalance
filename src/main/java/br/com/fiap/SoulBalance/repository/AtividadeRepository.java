package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Long> {

    Optional<AtividadeEntity> findByUsuarioIdAndAtividadeId(Long userId, Long atividadeId);

}
