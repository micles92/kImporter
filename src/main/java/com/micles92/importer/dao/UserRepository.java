package com.micles92.importer.dao;
import com.micles92.importer.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mlesniak on 2017-09-14.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
     boolean existsByLoginAndPassword(String login, String password);

     boolean existsByLogin(String login);

     User findByLogin(String login);
}
