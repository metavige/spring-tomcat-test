package com.example.springtomcattest.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Lazy
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
