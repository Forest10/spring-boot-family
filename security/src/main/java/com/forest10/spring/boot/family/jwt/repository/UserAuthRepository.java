package com.forest10.spring.boot.family.jwt.repository;

import com.forest10.spring.boot.family.common.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Forest10
 * @date 2018/5/17 下午3:16
 */
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}