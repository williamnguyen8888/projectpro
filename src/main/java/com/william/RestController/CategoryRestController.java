package com.william.RestController;

import com.william.entity.CategoryEntity;
import com.william.entity.Response;
import com.william.entity.ResponseStatus;
import com.william.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/category")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;
    Response response = new Response();
    @GetMapping
    public Response getAll(){
        response.setData(categoryService.findAll());
        response.setMessage("SUCCESS");
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
    @PostMapping
    public Response create(@RequestBody CategoryEntity category){
        categoryService.save(category);
        CategoryEntity categoryEntity = categoryService.findCategoryEntityByName(category.getName());
        response.setData(categoryEntity);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }
}
