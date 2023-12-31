package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.Category;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // ==== 연관관계 메서드 ==== //
    public void setMember(Member member) {
        if(this.member != null)
            member.getMemberCategoryList().remove(this);
        this.member = member;
        member.getMemberCategoryList().add(this);
    }

    public void setFoodCategory(Category category) {
        this.category = category;
    }
}
