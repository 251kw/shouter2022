package com.shantery.result2.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shantery.result2.repositories.UserInfoRepository;

/**
 * @author y.nakaya
 *
 */
@Service
public class UserInfoService {
	/**
	 * ユーザー情報 Repository
	 */
	@Autowired
	private UserInfoRepository repository;

	// ユーザー情報全検索
	public List<UserInfoData> searchAll() {
		return repository.findAll();
	}

	// ユーザー情報主キー検索
	public UserInfoData findById(Long id) {
		return repository.findById(id).get();
	}

	// ログインID検索
	public UserInfoData findByLoginId(String loginId) {
		return repository.findByLoginId(loginId);
	}

	/**
	 * ユーザー情報 新規登録
	 */
	public void create(UserInfoData userinfodata) {
		userinfodata.setLoginId(userinfodata.getLoginId());
		userinfodata.setPassword(userinfodata.getPassword());
		userinfodata.setUserName(userinfodata.getUserName());
		userinfodata.setIcon(userinfodata.getIcon());
		userinfodata.setProfile(userinfodata.getProfile());
		repository.save(userinfodata);
	}
}
