package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.domains.vo.entities.UserEntity;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import com.motafelipe.api.backoffice.models.pagination.PageRequestModel;
import com.motafelipe.api.backoffice.models.user.UserModelRequest;
import com.motafelipe.api.backoffice.repositories.UserRepository;
import com.motafelipe.api.backoffice.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save (UserModelRequest userModelRequest){
        String hash = HashUtil.getSecureHash(userModelRequest.getPassword());
        userModelRequest.setPassword(hash);
        var user = UserModelRequest.toEntity(userModelRequest);
        user.setPassword(hash);
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user){
        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);
        return userRepository.save(user);
    }

    public UserEntity getById(Long id){
        Optional<UserEntity> result = userRepository.findById(id);
        return result.orElseThrow(
            () -> new NotFoundException("There anre not user with id = "+ id)
        );
    }

    public List<UserEntity> listAll(){
        return userRepository.findAll();
    }

    public PageModel<UserEntity> listAllOnLazyMode (PageRequestModel pr) {
        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
        Page<UserEntity> page = userRepository.findAll(pageable);
        return new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    }

    public UserEntity login(String email, String password){
        password = HashUtil.getSecureHash(password);
        Optional<UserEntity> result = userRepository.login(email, password);
        result.orElseThrow(
            () -> new NotFoundException("There anre not user with email = "+ email)
        );
        return result.get();
    }

    public int updateRole(UserEntity user){
        return userRepository.updateRole(user.getIdUser(), user.getRole());
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional <UserEntity> result = userRepository.findByEmail(username);
        if(result.isEmpty())
            throw new UsernameNotFoundException("Does not exists user with e-mail = "+username);
        UserEntity user = result.get();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ " + user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
    }
}
