package ua.bakhtin.metr2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.bakhtin.metr2.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
     User findByEmail(String email);
     User findByPhone(String phone);
}