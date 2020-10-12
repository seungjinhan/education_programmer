package com.radiuslab.study.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(UserInsertDto userInsertDto) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userInsertDto, User.class);

        User newUser = this.userService.save(user);

        URI link = linkTo(UserController.class).slash(newUser.getId()).toUri();

        return ResponseEntity.created( link).build();
    }

    @GetMapping()
    public ResponseEntity< List<User>> userList(){

        return ResponseEntity.ok().body( this.userService.list());
    }
}
