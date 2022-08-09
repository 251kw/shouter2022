package com.shantery.result2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shantery.result2.info.UserInfoData;

/**
 * ユーザー情報 Repository
 */
/**
 * @author y.nakaya
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoData, Long> {
	// ログインID検索
	UserInfoData findByLoginId(String loginId);
}
