package umc.spring.service.categoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Category;
import umc.spring.repository.CategoryRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Optional<Category> findCategory(Long id) {
        return categoryRepository.findById(id);
    }
}
