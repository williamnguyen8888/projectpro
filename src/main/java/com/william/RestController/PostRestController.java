package com.william.RestController;

import com.william.entity.PostEntity;
import com.william.entity.Response;
import com.william.entity.ResponseStatus;
import com.william.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/post")
public class PostRestController {
    @Autowired
    private IPostService postService;
    Response response = new Response();

    @GetMapping
    public Response getAll() {
        response.setData(postService.findAll());
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @PostMapping
    public Response create(@RequestBody PostEntity postEntity) {
        postEntity.setCreatetime(Timestamp.valueOf(LocalDateTime.now()));
        postService.save(postEntity);
        response.setData(postService.findPostEntityByTitle(postEntity.getTitle()));
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @DeleteMapping
    public Response delete(@RequestParam int idPost) {
        try {
            postService.deleteById(idPost);
            response.setData(null);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
        } catch (EmptyResultDataAccessException ex) {
            response.setData(null);
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("ERROR");
        }
        return response;
    }

    @PutMapping
    public Response update(@RequestBody PostEntity postEntity) {
        postService.save(postEntity);
        response.setData(postService.findPostEntityByTitle(postEntity.getTitle()));
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @GetMapping("/find")
    public Response find(@RequestParam int idPost) {
        Optional<PostEntity> postEntity = postService.findById(idPost);

        try {
            if (!postEntity.isPresent()){
                response.setData(null);
                response.setStatus(ResponseStatus.ERROR);
                response.setMessage("ERROR");
                return response;
            }
            response.setData(postEntity);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
        } catch (Exception ex) {

        }
        return response;
    }

    @GetMapping("Last9Record")
    public Response Last9Record() {
        Page<PostEntity> page = postService.findPostEntitiesByOrderByIdDesc(PageRequest.of(0, 9, Sort.by("id").descending()));
        List<PostEntity> topUsersList = page.getContent();
        response.setData(topUsersList);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

}
