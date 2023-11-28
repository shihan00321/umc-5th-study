package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Town;

public interface TownRepository extends JpaRepository<Town, Long> {
}
