package com.shantery.result2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shantery.result2.update.UserInfo;

public interface UserUpdateRepository extends JpaRepository<UserInfo, Long>{
	
}
