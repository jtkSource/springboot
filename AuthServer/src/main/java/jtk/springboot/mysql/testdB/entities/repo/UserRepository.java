package jtk.springboot.mysql.testdB.entities.repo;

import jtk.springboot.mysql.testdB.entities.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by jubin on 31/12/16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByUsername(@Param("username") String username);

    @Caching(
            cacheable ={@Cacheable(value = "usersCache",
            key = "#root.args[0]",
            unless = "#result!=null && #result.isActive()==false ")
            },
            evict = {}
    )
    @Query("select u from User u where u.username= :username and u.password= :password")
    public User findByUsernameAndPassword(@Param("username") String username,
                                          @Param("password") String password);

}
