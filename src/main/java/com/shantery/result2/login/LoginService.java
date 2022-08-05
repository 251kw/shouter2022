package com.shantery.result2.login;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import static com.shantery.common.constants.*;

/**
 * @author r.totoki
 *
 */

@Service
public class LoginService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<UserLoginData> getAll(String loginId, String password) {
		
		List<UserLoginData> ResultList = null;
		
		// ログインIDとパスワードを受け取り、データベース上に一致するユーザー情報があるか検索
		ResultList = (List<UserLoginData>) entityManager
				.createQuery(WHERE_LOGINID + "'" + loginId + "'" + "AND password =" + "'" + password + "'")
				.getResultList();
		
		return ResultList;
		
	}
}
