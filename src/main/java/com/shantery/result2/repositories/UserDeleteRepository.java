package com.shantery.result2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shantery.result2.update.UserInfo;

public interface UserDeleteRepository extends JpaRepository<UserInfo, Long>{ 
	public List<UserInfo> findAllByUserId(Long userId);
	public List<UserInfo> deleteAllByUserId(Long userId);
	
}