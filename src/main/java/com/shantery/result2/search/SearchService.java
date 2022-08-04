package com.shantery.result2.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import static com.shantery.common.constants.*;

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
								.createQuery(WHERE_LOGINID + "'" + loginId + "'" + AND_USERNAME + "'%" + userName + "%'" 
											+ AND_ICON + "'" + icon + "'" + AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					} else{
						// 2.３項目(profile以外)
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'" +loginId + "'" + AND_USERNAME + "'%" + userName + "%'" 
										+ AND_ICON + "'" + icon + "'")
								.getResultList();
					}
				} else {
					if(profile != "") {
						// 3.3項目（icon以外）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'"+ loginId + "'" + AND_USERNAME + "'%" + userName + "%'" 
											+ AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 4.2項目（idとname）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'"+ loginId + "'" + AND_USERNAME + "'%" + userName + "%'")
								.getResultList();
					}
				}
			} else {
				if(icon != "") {
					if(profile != "") {
						// 5.３項目（name以外）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'"+ loginId + "'" + AND_ICON + "'" +icon + "'" + AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 6.２項目（id,icon）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'"+ loginId + "'" + AND_ICON + "'" + icon + "'")
								.getResultList();
					}
				}else {
					if(profile != "") {
						// 7.2項目（id,profile）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'"+ loginId + "'" + AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					}else {
						// 8.１項目（id）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_LOGINID + "'" +loginId + "'")
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
								.createQuery(WHERE_USERNAME + "'%" + userName + "%'" 
											+ AND_ICON + "'" + icon + "'" + AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 10.２項目（name,icon）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_USERNAME + "'%" + userName + "%'" 
											+ AND_ICON + "'" + icon + "'" )
								.getResultList();
					}
				} else {
					if(profile != "") {
						// 11.２項目（name,profile）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_USERNAME + "'%" + userName + "%'" 
											+ AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					} else {
						// 12.１項目（name）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_USERNAME + "'%" + userName + "%'")
								.getResultList();
					}
				}
			} else {
				if(icon != "") {
					if(profile != "") {
						// 13.２項目（icon,profile）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_ICON + "'" + icon + "'" + AND_PROFILE + "'%" + profile + "%'")
								.getResultList();
					}else {
						// 14.１項目（icon）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_ICON + "'" + icon + "'" )
								.getResultList();
					}
				} else {
					if(profile != "") {
						// 15.１項目（profile）
						ResultList = (List<UserData>) entityManager
								.createQuery(WHERE_PROFILE + "'%" + profile + "%'")
								.getResultList();
					}
				}
			}
		}
		
		return ResultList;
	}
}
