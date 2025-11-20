package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckinManualRepository extends JpaRepository<CheckinManualEntity, Long> {

    List<CheckinManualEntity> findByUsuarioId(
            Long usuarioId
    );

    @Transactional
    @Modifying
    int deleteByUsuarioIdAndChekinId(Long usuarioId, Long chekinId);

}
