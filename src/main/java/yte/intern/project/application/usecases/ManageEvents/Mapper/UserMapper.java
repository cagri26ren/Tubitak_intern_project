package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.AdminDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Admin;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {
    AdminDto mapToDto(Admin admin);

    Admin mapToEntity(AdminDto adminDto);

    List<AdminDto> mapToDto(List<Admin> admins);

    List<Admin> mapToEntity(List<AdminDto> adminDtos);
}
