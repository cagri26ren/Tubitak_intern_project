package yte.intern.project.application.usecases.ManageEvents.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.application.usecases.ManageEvents.Entity.Admin;
import yte.intern.project.application.usecases.ManageEvents.Repository.UserRepository;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;
import yte.intern.project.application.usecases.common.ENUMS.MessageType;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public MessageResponse login(String username, String password ){
        Admin admin = userRepository.findAByUsername(username);
        if( admin != null) {
            if (admin.getPassword().equals(polynomialRollingHash(password))) {
                return new MessageResponse("Login successful", MessageType.SUCCESS);
            }
        }
        return new MessageResponse("Login failed", MessageType.ERROR );
    }

    public void register( Admin admin){
        admin.setPassword(polynomialRollingHash(admin.getPassword()));
        userRepository.save(admin);
    }

    String polynomialRollingHash( String str)
    {
        // P and M
        int p = 31;
        int m = (int)1e9 + 9;
        Long power_of_p = 1L;
        Long hash_val = 0L;

        // Loop to calculate the hash value
        // by iterating over the elements of string
        for (int i = 0; i < str.length(); i++) {
            hash_val
                    = (hash_val
                    + (str.charAt(i) - 'a' + 1) * power_of_p)
                    % m;
            power_of_p
                    = (power_of_p * p) % m;
        }
        return hash_val + "";
    }



}
