package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.AdminDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Admin;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-07T16:16:23+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public AdminDto mapToDto(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminDto adminDto = new AdminDto();

        adminDto.setUsername( admin.getUsername() );
        adminDto.setPassword( admin.getPassword() );

        return adminDto;
    }

    @Override
    public Admin mapToEntity(AdminDto adminDto) {
        if ( adminDto == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setUsername( adminDto.getUsername() );
        admin.setPassword( adminDto.getPassword() );

        return admin;
    }

    @Override
    public List<AdminDto> mapToDto(List<Admin> admins) {
        if ( admins == null ) {
            return null;
        }

        List<AdminDto> list = new ArrayList<AdminDto>( admins.size() );
        for ( Admin admin : admins ) {
            list.add( mapToDto( admin ) );
        }

        return list;
    }

    @Override
    public List<Admin> mapToEntity(List<AdminDto> adminDtos) {
        if ( adminDtos == null ) {
            return null;
        }

        List<Admin> list = new ArrayList<Admin>( adminDtos.size() );
        for ( AdminDto adminDto : adminDtos ) {
            list.add( mapToEntity( adminDto ) );
        }

        return list;
    }
}
