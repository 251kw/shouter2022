package com.shantery.result2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shantery.result2.login.ShoutData;

/**
 * @author s.ogata
 *
 */
@Repository
public interface ShoutRepository extends JpaRepository<ShoutData, Long> {
	
}
