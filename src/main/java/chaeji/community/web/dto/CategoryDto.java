package chaeji.community.web.dto;

import chaeji.community.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class CategoryDto {

    private Long cno;

    private String name;

    public CategoryDto(Category category) {
        this.cno = category.getCno();
        this.name = category.getName();
    }
}
