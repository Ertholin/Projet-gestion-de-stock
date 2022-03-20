package com.ertho.gestiondestosck.services.impl;

import com.ertho.gestiondestosck.dto.CategoryDto;
import com.ertho.gestiondestosck.exception.EntityNotFoundException;
import com.ertho.gestiondestosck.exception.ErrorCodes;
import com.ertho.gestiondestosck.exception.InvalidEntityException;
import com.ertho.gestiondestosck.repository.CategoryRepository;
import com.ertho.gestiondestosck.services.CategoryService;
import com.ertho.gestiondestosck.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Category is not Valid {}", dto);
            throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(dto))
        );
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id == null){
            log.error("Category ID is null");
            return null;
        }
        return categoryRepository.findById(id)
             .map(CategoryDto::fromEntity)
             .orElseThrow(()-> new EntityNotFoundException(
                  "Aucune Category avec l'ID " + id + " n'a pas ete trouvee dans la BDD",
                   ErrorCodes.CATEGORY_NOT_FOUND)
             );
    }

    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {
        if(StringUtils.hasLength(codeCategory)){
            log.error("Category CODE is null");
            return null;
        }
        return categoryRepository.findCategoryByCode(codeCategory)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Category avec le code " + codeCategory + "n'a pas ete trouvee dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);

    }
}
