package com.shantery.result2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shantery.result2.login.ShoutData;

/**
 * @author s.ogata
 *
 */
@Repository
public interface ShoutRepository extends JpaRepository<ShoutData, Long> {
	//掲示板画面のshoutリストを取得し、日付の新しい順で並べ替え
	public List<ShoutData> findAllByOrderByDateDesc();
	
}
