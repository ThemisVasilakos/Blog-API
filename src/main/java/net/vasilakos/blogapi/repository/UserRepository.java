package net.vasilakos.blogapi.repository;

import net.vasilakos.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select user_id from user where username=?1",nativeQuery = true)
    Long findUserIdByUsername(String username);
}
