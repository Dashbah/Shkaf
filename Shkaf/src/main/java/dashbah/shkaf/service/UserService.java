package dashbah.shkaf.service;

import dashbah.shkaf.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService { // security
    boolean save(UserDTO userDTO);
}
