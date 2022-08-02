package com.shantery.result2.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<UserData> getAll() {
		return (List<UserData>) entityManager
			.createQuery("from users").getResultList();
	}
	
	public UserData get1(String loginId) {
		return (UserData)entityManager
				.createQuery("from users where loginId =" + loginId)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserData> get2(String userName) {
		return (List<UserData>)entityManager
				.createQuery("from users where userName =" + userName)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserData> get3(String icon1) {
		return (List<UserData>)entityManager
				.createQuery("from users where icon =" + icon1)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserData> get4(String icon2) {
		return (List<UserData>)entityManager
				.createQuery("from users where icon =" + icon2)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserData> get5(String profile) {
		return (List<UserData>)entityManager
				.createQuery("from users where userName =" + profile)
				.getResultList();
	}
	
	
}