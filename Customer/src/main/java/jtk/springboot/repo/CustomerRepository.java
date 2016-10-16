package jtk.springboot.repo;

import jtk.springboot.entities.Customer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by jubin on 25/9/16.
 */

@RepositoryRestResource
@Lazy
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByName(@Param("name") String name);
}
