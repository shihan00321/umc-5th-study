package umc.spring.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Category;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Town;
import umc.spring.repository.CategoryRepository;
import umc.spring.repository.RestaurantRepository;
import umc.spring.repository.TownRepository;
import umc.spring.web.dto.RestaurantRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;
    private final TownRepository townRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Restaurant registerRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO) {
        Category category = categoryRepository.findById(registerDTO.getCategoryId()).orElseThrow(() -> new RestaurantHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        Town town = townRepository.findById(registerDTO.getTownId()).orElseThrow(() -> new RestaurantHandler(ErrorStatus.TOWN_NOT_FOUND));
        Restaurant restaurant = RestaurantConverter.toRestaurant(registerDTO, category, town);
        return restaurantRepository.save(restaurant);
    }
}
