package umc.spring.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;
    private final TownRepository townRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Restaurant registerRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO) {
        Category category = categoryRepository.findById(registerDTO.getCategoryId()).orElseThrow(() -> new RestaurantHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        Town town = townRepository.findById(registerDTO.getTownId()).orElseThrow(() -> new RestaurantHandler(ErrorStatus.TOWN_NOT_FOUND));
        Restaurant restaurant = RestaurantConverter.toRestaurant(registerDTO, category, town);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    @Override
    public Review registerReview(ReviewRequestDTO.WriteReviewDTO reviewDTO, Long restaurantId) {
        Review review = ReviewConverter.toReview(reviewDTO);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        review.setRestaurant(restaurant);
        Member member = memberRepository.findById(reviewDTO.getMemberId()).get();
        review.setMember(member);
        return reviewRepository.save(review);
    }
}
