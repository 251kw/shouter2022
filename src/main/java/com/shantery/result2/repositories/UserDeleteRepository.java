package com.shantery.result2.repositories;
/**
 * @author k.iwai
 *
 */

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shantery.result2.delete.UserInfom;

public interface UserDeleteRepository extends JpaRepository<UserInfom, Long>{ 
	public List<UserInfom> findAllByUserId(Long userId);
	@Transactional
	public List<UserInfom> deleteByUserId(Long userId);
	
}