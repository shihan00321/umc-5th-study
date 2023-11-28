package umc.spring.converter;

import umc.spring.domain.Category;
import umc.spring.domain.mapping.MemberCategory;

import java.util.List;
import java.util.stream.Collectors;

public class MemberCategoryConverter {
    public static List<MemberCategory> toMemberCategoryList(List<Category> categoryList) {
        return categoryList.stream()
                .map(category ->
                    MemberCategory.builder()
                            .category(category)
                            .build()
                ).collect(Collectors.toList());
    }
}
