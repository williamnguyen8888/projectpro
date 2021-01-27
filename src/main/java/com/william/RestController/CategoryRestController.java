package com.william.RestController;

import com.william.entity.CategoryEntity;
import com.william.entity.Response;
import com.william.entity.ResponseStatus;
import com.william.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/category")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;
    Response response = new Response();

    @GetMapping
    public Response getAll() {
        response.setData(categoryService.findAll());
        response.setMessage("SUCCESS");
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping
    public Response create(@RequestBody CategoryEntity category) {
        categoryService.save(category);
        CategoryEntity categoryEntity = categoryService.findCategoryEntityByName(category.getName());
        response.setData(categoryEntity);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @DeleteMapping
    public Response delete(@RequestParam int idCategory) {
        try {
            categoryService.findById(idCategory);
            response.setData(null);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
        } catch (Exception ex) {
            response.setData(null);
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("ERROR");
        }
        return response;
    }

    @PutMapping
    public Response update(@RequestBody CategoryEntity categoryEntity) {
        categoryService.save(categoryEntity);
        response.setData(categoryService.findCategoryEntityByName(categoryEntity.getName()));
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @GetMapping("/find")
    public Response find(@RequestParam int idCategory) {
        try {
            Optional<CategoryEntity> categoryEntity = categoryService.findById(idCategory);
            if (!categoryEntity.isPresent()) {
                throw new Exception();
            }
            response.setData(categoryEntity);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
        } catch (Exception ex) {
            response.setData(null);
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("ERROR");
            return response;
        }
        return response;
    }
}
