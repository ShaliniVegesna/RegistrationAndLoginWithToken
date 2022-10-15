package com.InterceptorPractice.repository;

import com.InterceptorPractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
}
