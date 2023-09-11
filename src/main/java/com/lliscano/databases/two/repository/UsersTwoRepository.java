package com.lliscano.databases.two.repository;


import com.lliscano.databases.two.entity.UsersTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTwoRepository extends JpaRepository<UsersTwo, Long> {
}
