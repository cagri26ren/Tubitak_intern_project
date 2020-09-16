package yte.intern.project.application.usecases.ManageEvents.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.project.application.usecases.ManageEvents.DTO.AdminDto;
import yte.intern.project.application.usecases.ManageEvents.Mapper.UserMapper;
import yte.intern.project.application.usecases.ManageEvents.Service.AdminService;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserMapper userMapper;
    private final AdminService adminService;

    @PostMapping("/login")
    public MessageResponse login(@RequestBody @Valid AdminDto adminDto){
        return adminService.login(adminDto.getUsername(), adminDto.getPassword());
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid AdminDto adminDto){
        adminService.register(userMapper.mapToEntity(adminDto));
    }

}
