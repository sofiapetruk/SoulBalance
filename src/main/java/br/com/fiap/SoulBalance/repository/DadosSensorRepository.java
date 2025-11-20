package br.com.fiap.SoulBalance.repository;

import br.com.fiap.SoulBalance.entity.DadosSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DadosSensorRepository extends JpaRepository<DadosSensorEntity, Long> {

    List<DadosSensorEntity> findByUsuarioIdAndTimeBetween(
            Long usuarioId,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

}
