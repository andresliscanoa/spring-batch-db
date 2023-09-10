package com.lliscano.two.repository;


import com.lliscano.two.entity.UsersTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTwoRepository extends JpaRepository<UsersTwo, Long> {
}
