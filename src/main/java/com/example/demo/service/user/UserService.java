package com.example.demo.service.user;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll().stream().map(UserConverter::toUser)
        .collect(Collectors.toList());
  }

  public User getOneBy(UUID id) {
    var userEntity = userRepository.findById(id).orElseThrow(NotFoundException::new);
    return UserConverter.toUser(userEntity);
  }

  public User save(User user) {
    user.setId(user.getId() != null ? user.getId() : UUID.randomUUID());
    var entityRequest = UserConverter.toEntity(user);
    var entityResponse = userRepository.save(entityRequest);
    return UserConverter.toUser(entityResponse);
  }

  public User replace(UUID id, User user) {
    var databaseUser = this.getOneBy(id);
    databaseUser.setUsername(user.getUsername());
    databaseUser.setPassword(user.getPassword());
    var newUser = this.save(databaseUser);
    return newUser;
  }

  public void delete(UUID id) {
    this.getOneBy(id);
    userRepository.deleteById(id);
  }

}
