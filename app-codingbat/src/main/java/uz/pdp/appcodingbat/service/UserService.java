package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Star_badge;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.model.UserDto;
import uz.pdp.appcodingbat.repository.Star_badgeRepository;
import uz.pdp.appcodingbat.repository.UserRepository;
import uz.pdp.appcodingbat.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

@Autowired
    UserRepository userRepository;
@Autowired
    Star_badgeRepository star_badgeRepository;

public List<User> getUsers(){
    return userRepository.findAll();
}

public ResponseEntity<?> getUserById(Integer id){
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("User not found",false));
    return ResponseEntity.ok(optionalUser.get());
}

public ResponseEntity<?> addUser(UserDto dto){
    Optional<Star_badge> optionalStar_badge = star_badgeRepository.findById(dto.getStar_badgeId());
    if (!optionalStar_badge.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("StarBadge error",false));
    User user = new User();
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setStarBadge(optionalStar_badge.get());
    userRepository.save(user);
    return ResponseEntity.ok(new ApiResponse("User added",true));

}

public ResponseEntity<?> editUser(Integer id,UserDto dto){
    Optional<Star_badge> optionalStar_badge = star_badgeRepository.findById(dto.getStar_badgeId());
    if (!optionalStar_badge.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("StarBadge error",false));
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("User not found",false));
    User user = optionalUser.get();
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setStarBadge(optionalStar_badge.get());
    userRepository.save(user);
    return ResponseEntity.ok(new ApiResponse("User edited",true));
}

public ResponseEntity<?> deleteUser(Integer id){
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("User not found",false));
     userRepository.delete(optionalUser.get());
     return ResponseEntity.ok(new ApiResponse("User deleted",true));
}


}
