package com.shantery.result2.repositories;

import com.shantery.result2.login.UserLoginData;
import com.shantery.result2.search.UserData;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author r.totoki
 *
 */

@Repository
public interface LoginRepository extends JpaRepository<UserLoginData, Long>{
	
	public Optional<UserData> findByUserId(Long userId);
	public Optional<UserData> findByLoginId(String loginId);
	public Optional<UserData> findByPassword(String password);
	
}
