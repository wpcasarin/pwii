package br.edu.ifto.pwii.controller;

import br.edu.ifto.pwii.dto.CreateUserDTO;
import br.edu.ifto.pwii.dto.EditUserDTO;
import br.edu.ifto.pwii.dto.UserDTO;
import br.edu.ifto.pwii.model.User;
import br.edu.ifto.pwii.model.UserB2B;
import br.edu.ifto.pwii.model.UserB2C;
import br.edu.ifto.pwii.model.UserType;
import br.edu.ifto.pwii.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;


    @GetMapping
    public String getAllPage(Model model) {
        List<User> userList = userService.findAll();
        List<UserDTO> userDtos = userList.stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .toList();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        model.addAttribute("editUser", new EditUserDTO());
        model.addAttribute("createUser", new CreateUserDTO());
        return "views/user";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("createUser") CreateUserDTO createUser) {
        if (UserType.B2B.equals(createUser.getType())) {
            UserB2B user = mapper.map(createUser, UserB2B.class);
            userService.create(user);
        } else if (UserType.B2C.equals(createUser.getType())) {
            UserB2C user = mapper.map(createUser, UserB2C.class);
            userService.create(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("editUser") EditUserDTO editUser) {
        var existingUser = userService.findById(id).orElseThrow(() -> new RuntimeException("Invalid user ID: " + id));

        if (existingUser instanceof UserB2B) {
            UserB2B user = mapper.map(editUser, UserB2B.class);
            userService.update(id, existingUser, user);
        } else if (existingUser instanceof UserB2C) {
            UserB2C user = mapper.map(editUser, UserB2C.class);
            userService.update(id, existingUser, user);
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
