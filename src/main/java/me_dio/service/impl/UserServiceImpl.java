package me_dio.service.impl;

import me_dio.domain.model.User;
import me_dio.domain.repository.UserRepository;
import me_dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userTocreate) {
        if(userRepository.existsByAccountNumber(userTocreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This account number  already exists");
        }
        return this.userRepository.save(userTocreate);
    }
}
