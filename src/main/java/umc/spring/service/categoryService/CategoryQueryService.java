package umc.spring.service.categoryService;

import umc.spring.domain.Category;

import java.util.Optional;

public interface CategoryQueryService {
    Optional<Category> findCategory(Long id);
}
