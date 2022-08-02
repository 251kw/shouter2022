package com.shantery.result2.repositories;

import com.shantery.result2.search.UserData;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SearchRepository extends JpaRepository<UserData, String>{
	
	public Optional<UserData> findByUserId(String userId);
	public Optional<UserData> findByLoginId(String loginId);
	public Optional<UserData> findByUserName(String userName);
	public Optional<UserData> findByIcon1(String icon1);
	public Optional<UserData> findByIcon2(String icon2);
	public Optional<UserData> findByProfile(String profile);
	
}
