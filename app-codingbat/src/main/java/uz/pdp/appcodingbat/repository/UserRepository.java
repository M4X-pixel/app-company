package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
