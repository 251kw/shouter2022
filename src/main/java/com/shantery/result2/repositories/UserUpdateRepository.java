package com.shantery.result2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shantery.result2.update.UserInfo;

@Repository
public interface UserUpdateRepository extends JpaRepository<UserInfo, Long>{
	
	//ログインID検索
	UserInfo findByLoginId(String loginId);
}
