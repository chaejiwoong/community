package chaeji.community.service;

import chaeji.community.domain.Category;
import chaeji.community.repository.CategoryRepository;
import chaeji.community.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(CategoryDto dto) {

        log.info("saveCategoryService...");

        Category entity = Category.builder()
                .name(dto.getName())
                .build();

        return categoryRepository.save(entity).getCno();
    }

    public CategoryDto findOne(Long cno) {
        Category category = categoryRepository.findById(cno).orElseThrow(() ->
                new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        return new CategoryDto(category);
    }

    public List<CategoryDto> findAll() {

        return categoryRepository.findAll().stream().map(CategoryDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(CategoryDto dto) {
        Category category = categoryRepository.findById(dto.getCno()).orElseThrow(() ->
                new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));

        category.changeName(dto.getName());

        return category.getCno();
    }

    @Transactional
    public Long delete(Long cno) {

        categoryRepository.deleteById(cno);

        return cno;
    }
}
