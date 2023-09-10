package com.lliscano.one.repository;


import com.lliscano.one.entity.UsersOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersOneRepository extends JpaRepository<UsersOne, Long> {
}
