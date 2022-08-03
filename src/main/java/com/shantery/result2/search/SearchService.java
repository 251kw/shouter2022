package com.shantery.result2.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;

/**
 * @author r.totoki
 *
 */

@Service
public class SearchService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<UserData> getAll(String loginId, String userName, String icon, String profile) {
		
		List<UserData> ResultList = null;

		//　検索条件の分岐
		if(loginId != "") {	// IDに検索内容アリ
			if(userName != "") {	//　Id,Name
				if(icon != "") {	// Id,Name,icon
					if(profile != "") {	// ALL
						// 1.全項目
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'"+ loginId + "'" + "AND userName LIKE" + "'%" + userName + "%'" 
											+ "AND icon =" + "'" + icon + "'" + "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					} else{
						// 2.３項目(profile以外)
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'" +loginId + "'" + "AND userName LIKE" + "'%" + userName + "%'" 
										+ "AND icon =" + "'" + icon + "'")
								.getResultList();
					}
				} else {
					if(profile != "") {
						// 3.3項目（icon以外）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'"+ loginId + "'" + "AND userName LIKE" + "'%" + userName + "%'" 
											+ "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 4.2項目（idとname）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'"+ loginId + "'" + "AND userName LIKE" + "'%" + userName)
								.getResultList();
					}
				}
			} else {
				if(icon != "") {
					if(profile != "") {
						// 5.３項目（name以外）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'"+ loginId + "'" + "AND icon =" + "'" +icon + "'" + "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 6.２項目（id,icon）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'"+ loginId + "'" + "AND icon =" + "'" + icon + "'")
								.getResultList();
					}
				}else {
					if(profile != "") {
						// 7.2項目（id,profile）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'"+ loginId + "'" + "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					}else {
						// 8.１項目（id）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where loginId =" + "'" +loginId + "'")
								.getResultList();
					}
				}
			}
		}else {		// id=""
			if(userName != "") {
				if(icon != "") {
					if(profile != "") {
						// 9.３項目（id以外）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where userName LIKE" + "'%" + userName + "%'" 
											+ "AND icon =" + "'" + icon + "'" + "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 10.２項目（name,icon）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where userName LIKE" + "'%" + userName + "%'" 
											+ "AND icon =" + "'" + icon + "'" )
								.getResultList();
					}
				} else {
					if(profile != "") {
						// 11.２項目（name,profile）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where userName LIKE" + "'%" + userName + "%'" 
											+ "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 12.１項目（name）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where userName LIKE" + "'%" + userName + "%'")
								.getResultList();
					}
				}
			} else {
				if(icon != "") {
					if(profile != "") {
						// 13.２項目（icon,profile）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where icon =" + "'" + icon + "'" + "AND profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					}else {
						// 14.１項目（icon）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where icon =" + "'" + icon + "'" )
								.getResultList();
					}
				} else {
					if(profile != "") {
						// 15.１項目（profile）
						ResultList = (List<UserData>) entityManager
								.createQuery("from UserData where profile LIKE" + "'%" + profile + "%'")
								.getResultList();
					}
				}
			}
		}
		
		return ResultList;
	}
}





/* ３２パターンの方、一応キープしときます
@SuppressWarnings("unchecked")
public List<UserData> getAll(String loginId, String userName, String icon1, String icon2, String profile) {
	List<UserData> ResultList = null;
	if(userName.equals("") && icon1==null && icon2==null && profile.equals("")) {	// ログインIDのみ
		ResultList = (List<UserData>) entityManager
				.createQuery("from UserData where loginId =" + loginId)
				.getResultList();
	} else if(loginId.equals("") && icon1==null && icon2==null &&  profile.equals("")) {	// ユーザー名のみ
		ResultList = (List<UserData>) entityManager
				.createQuery("from UserData where userName LIKE" + "'%" + userName + "%'")
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
*/