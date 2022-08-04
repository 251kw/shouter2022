package com.shantery.result2.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shantery.result2.repositories.UserUpdateRepository;

@Service
public class UserUpdateService {
	@Autowired
	private UserUpdateRepository repository;
	
	//ユーザー情報全検索
	public List<UserInfo> searchAll(){
		return repository.findAll();
	}
	
	//ユーザー情報主キー検索
	public UserInfo findById(Long id) {
		return repository.findById(id).get();
	}
	
	//ログインID検索
	public UserInfo findByLoginId(String loginId) {
		return repository.findByLoginId(loginId);
	}
}
