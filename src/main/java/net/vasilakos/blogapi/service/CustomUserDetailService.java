package net.vasilakos.blogapi.service;

import net.vasilakos.blogapi.dto.ArticleDTO;
import net.vasilakos.blogapi.dto.UserDTO;
import net.vasilakos.blogapi.model.Article;
import net.vasilakos.blogapi.model.User;
import net.vasilakos.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;
        User user = userRepository.findByUsername(username);
        if (user != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
            //User(user.getUsername(), user.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);

    }

    public User save(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    public ArrayList<UserDTO> getAllUsers(){
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        for(int i=0;i<users.size();i++){
            UserDTO tmp = new UserDTO(users.get(i));
            userDTOS.add(tmp);
        }
        return userDTOS;
    }

}
