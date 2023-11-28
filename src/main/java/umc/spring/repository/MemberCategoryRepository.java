package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.MemberCategory;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {
}
