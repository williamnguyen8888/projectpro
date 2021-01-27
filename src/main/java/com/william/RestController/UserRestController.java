package com.william.RestController;

import com.william.entity.LoginEntity;
import com.william.entity.Response;
import com.william.entity.ResponseStatus;
import com.william.entity.UsersEntity;
import com.william.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "user",consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    @Autowired
    private IUserService userService;

    Response response = new Response();

    @GetMapping
    public Response getAll() {
        Iterable<UsersEntity> usersEntities = userService.findAll();
        response.setData(usersEntities);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @PostMapping
    public Response create(@RequestBody UsersEntity user) {
        userService.save(user);
        UsersEntity usersEntity = userService.findUsersEntityByUsername(user.getUsername());
        response.setData(usersEntity);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @DeleteMapping
    public Response delete(@RequestParam int UserId) {
        try{
            userService.deleteById(UserId);
            response.setData(null);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
        }catch (Exception ex){
            response.setData(null);
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("ERROR");
        }
        return response;
    }

    @PutMapping
    public Response update(@RequestBody UsersEntity user) {
        userService.save(user);
        response.setData(user);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }
    @GetMapping("/find")
    public Response find(@RequestParam int UserId){
        Optional<UsersEntity> usersEntity = userService.findById(UserId);
        try {
            if (!usersEntity.isPresent()){
                response.setData(null);
                response.setStatus(ResponseStatus.ERROR);
                response.setMessage("ERROR");
                return response;
            }
            response.setData(usersEntity.get());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
            return response;
        }catch (Exception ex){

        }
        return response;
    }
    @PostMapping("/login")
    public Response checkLogin(@RequestBody LoginEntity login){
        boolean checkLogin = userService.existsUsersEntitiesByUsernameAndPassword(login.getUsername(),login.getPassword());
        if (checkLogin){
            UsersEntity user = userService.findUsersEntityByUsername(login.getUsername());
            response.setData(user);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("SUCCESS");
            return response;
        }
        response.setData(null);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }
}
