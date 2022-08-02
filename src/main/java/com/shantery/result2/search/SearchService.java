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
	public List<UserData> getAll(String loginId, String userName, String icon1, String icon2, String profile) {
		List<UserData> ResultList = null;
		if(userName.equals("") && icon1==null && icon2==null && profile.equals("")) {	// ログインIDのみ
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId)
					.getResultList();
		} else if(loginId.equals("") && icon1==null && icon2==null &&  profile.equals("")) {	// ユーザー名のみ
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%")
					.getResultList();
		} else if(loginId.equals("") && userName.equals("") && icon2==null &&  profile.equals("")) {	// アイコン１
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where icon =" + icon1)
					.getResultList();
		} else if(loginId.equals("") && userName.equals("") && icon1==null &&  profile.equals("")) {	// アイコン２
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where icon =" + icon2)
					.getResultList();
		} else if(loginId.equals("") && userName.equals("") &&  icon1==null && icon2==null){	// プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where profile =" + "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("") && icon1== null && icon2==null) {	// ユーザー名、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%" + "AND profile =" + "%" + profile + "%" )
					.getResultList();
		} else if(loginId.equals("") && userName.equals("") && icon1==null) {	// アイコン２、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where icon =" + icon2 + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("") && userName.equals("") && icon2==null) {	// アイコン１、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where icon =" + icon1 + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(userName.equals("") && icon1==null && icon2==null) {	// ログインID、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(userName.equals("") && icon1==null) {		// ログインID、アイコン２
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("") && icon1==null) {	// ユーザー名、アイコン２
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + userName + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("") && userName.equals("")) {	// 	アイコン１、アイコン２
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where icon =" + icon1 + "OR" + icon2)
					.getResultList();
		} else if(icon1==null && icon2==null && profile.equals("")) {
			// ログインID、ユーザー名
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%")
					.getResultList();
		} else if(loginId.equals("") && icon2==null && profile.equals("")) {
			//ユーザー名、アイコン1
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%" + "AND icon=" + icon1)
					.getResultList();
		} else if(userName.equals("") && icon2==null && profile.equals("")) {
			//ログインID、アイコン1
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND icon=" + icon1)
					.getResultList();
		} else if((loginId.equals("") && userName.equals(""))) {
			//アイコン1、アイコン2、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where profile =" + "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("") && icon1==null) {
			//ユーザー名、アイコン2、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%" + "AND icon=" + icon2 + "AND profile ="+ "%" + profile + "%")
					.getResultList();
		} else if(userName.equals("") && icon1==null) {
			// ログインID、アイコン２、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND icon=" + icon2 + "AND profile ="+ "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("") && icon2==null) {
			//ユーザー名、アイコン1、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%" + "AND icon=" + icon1 + "AND profile ="+ "%" + profile + "%")
					.getResultList();
		} else if(userName.equals("") && icon2==null) {
			// ログインID、アイコン1、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND icon=" + icon1 + "AND profile ="+ "%" + profile + "%")
					.getResultList();
		} else if(loginId.equals("")  && profile.equals("")) {
			//ユーザー名、アイコン1、アイコン2
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%" )
					.getResultList();
		} else if(userName.equals("") && profile.equals("")) {
			//ログインID、アイコン1、アイコン2
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId)
					.getResultList();
		} else if(icon1==null && profile.equals("")){
			//ログインID、ユーザー名、アイコン2
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%" + "AND icon =" + icon2)
					.getResultList();
		} else if(icon1==null && icon2==null){
			//ログインID、ユーザー名、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%" + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(icon2==null && profile.equals("")) {
			//ログインID、ユーザー名、アイコン1
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%" + "AND icon =" + icon1)
					.getResultList();
		} else if(loginId.equals("")) {
			//ユーザー名、アイコン1、アイコン2、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where userName =" + "%" + userName + "%" + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(userName.equals("")) {
			//ログインID、アイコン1、アイコン2、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(icon1==null){
			//ログインID、ユーザー名、アイコン2、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%" + "AND icon =" + icon2 + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(icon2==null) {
			//ログインID、ユーザー名、アイコン1、プロフィール
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%" + "AND icon =" + icon1 + "AND profile =" + "%" + profile + "%")
					.getResultList();
		} else if(profile.equals("")) {
			//ログインID、ユーザーID、アイコン1、アイコン2
			ResultList = (List<UserData>) entityManager
					.createQuery("from UserData where loginId =" + loginId + "AND userName =" + "%" + userName + "%" )
					.getResultList();
		}
		
		return ResultList;
		
	}
}