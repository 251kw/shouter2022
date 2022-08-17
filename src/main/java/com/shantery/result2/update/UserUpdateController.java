package com.shantery.result2.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.UserUpdateRepository;
import static com.shantery.common.constants.*;

/**
 * @author h.kasai
 *
 */
@Controller
public class UserUpdateController {
	@Autowired
	UserUpdateRepository repository;
	
	//編集画面へ遷移
	@RequestMapping(value=URL_UPDATE, method=RequestMethod.POST)
	public ModelAndView edit(@PathVariable Long id,
							 //編集ボタンから送られた情報の取得
							 @RequestParam(name=LOGINID) String loginId,
							 @RequestParam(name=USERNAME) String userName,
							 @RequestParam(name=ICON) String icon,
							 @RequestParam(name=PROFILE) String profile,
							 @ModelAttribute UserInfo userInfo,
							 ModelAndView mav) {
		UserInfo user = repository.findById(id).get();
		Long userId = user.getUserId();
		String LoginId = user.getLoginId();
		String loginID = user.getLoginId();
		String UserName = user.getUserName();
		String Icon = user.getIcon();
		String Profile = user.getProfile();
		
		//検索条件の保持
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);
		
		//編集ボタンから渡されたデータ
		mav.addObject(ADDNAME_UPDATE_USERID, userId);
		mav.addObject(ADDNAME_UPDATE_AFTER_LOGINID, LoginId);
		mav.addObject(ADDNAME_UPDATE_BEFORE_LOGINID, loginID);
		mav.addObject(ADDNAME_UPDATE_USERNAME, UserName);
		mav.addObject(ADDNAME_UPDATE_ICON, Icon);
		mav.addObject(ADDNAME_UPDATE_PROFILE, Profile);	
		mav.addObject(USER_INFO, userInfo);
		mav.setViewName(DISPLAY_OF_UPDATE_INPUT);
		return mav;
	}
	
	//更新確認画面へ遷移
	@RequestMapping(value=URL_UPDATE_CONFIRM, method=RequestMethod.POST)
	public ModelAndView editCheck(//編集内容の保持	
								  //フィールド名と同じ名前が優先される為、使いたい方をフィールド名と同じ名前にする
								  @RequestParam(name=LOGINID) String loginId,	//変更後のログインID
								  @RequestParam(name=USERNAME) String userName,
								  @RequestParam(name=UPDATE_ICON) String Icon,		
								  @RequestParam(name=PROFILE) String profile,
								  @Validated @ModelAttribute UserInfo userInfo,
			  					  BindingResult result,
								  @RequestParam(name=USERID) String userId,
								  @RequestParam(name=UPDATE_BEFORE_LOGINID) String loginID,	//変更前(編集画面に遷移して一番初めに表示されるログインID)
								  //検索条件の保持
								  @RequestParam(name=LOGINID2) String loginId2,
								  @RequestParam(name=USERNAME2) String userName2,
								  @RequestParam(name=ICON2) String icon2,
								  @RequestParam(name=PROFILE2) String profile2,
								  ModelAndView mav) {
		
		boolean check = false;
		
		//変更後のログインID検索
		UserInfo user = repository.findByLoginId(loginId);
		
		//検索条件の保持
		mav.addObject(ADDNAME_LOGINID, loginId2);
		mav.addObject(ADDNAME_USERNAME, userName2);	
		mav.addObject(ADDNAME_ICON, icon2);
		mav.addObject(ADDNAME_PROFILE, profile2);
		
		//編集条件の保持
		mav.addObject(ADDNAME_UPDATE_USERID, userId);	
		mav.addObject(ADDNAME_UPDATE_AFTER_LOGINID, loginId);	//変更後
		mav.addObject(ADDNAME_UPDATE_BEFORE_LOGINID, loginID);	//変更前(編集画面に遷移して一番初めに表示されるログインID)
		mav.addObject(ADDNAME_UPDATE_USERNAME, userName);
		mav.addObject(ADDNAME_UPDATE_ICON, Icon);
		mav.addObject(ADDNAME_UPDATE_PROFILE, profile);
		
		//フィールド毎にバリテーションチェック
		//全て入力されていた場合
		if(result.hasErrors()){
			mav.addObject(USER_INFO, userInfo);
			mav.setViewName(DISPLAY_OF_UPDATE_INPUT);
		}else {
			//変更前と変更後が同じ場合
			if(loginId.equals(loginID)) {
				mav.setViewName(DISPLAY_OF_UPDATE_CONFIRM);
			}else {				
				//変更が生じ、かつ既存のログインIDでないか確認
				if(user == null) {
					mav.setViewName(DISPLAY_OF_UPDATE_CONFIRM);
				}else {
					check = true;
					mav.addObject(ADDNAME_ERROR, check);
					mav.setViewName(DISPLAY_OF_UPDATE_INPUT);
				}
			}
		}
		return mav;
	}
	
	//更新確認画面で戻るボタンが押された場合、編集確認画面に遷移
	@RequestMapping(value=URL_UPDATE_INPUT, method=RequestMethod.POST)
	public ModelAndView editBack(//確認画面から送られてくる検索条件
								 @RequestParam(name=LOGINID) String loginId,
								 @RequestParam(name=USERNAME) String userName,
								 @RequestParam(name=ICON) String icon,
								 @RequestParam(name=PROFILE) String profile,
								 //更新内容から送られてくる編集内容
								 @RequestParam(name=UPDATE_USERID) Long id,
								 @RequestParam(name=UPDATE_AFTER_LOGINID) String LoginId,
								 @RequestParam(name=UPDATE_BEFORE_LOGINID) String loginID,
								 @RequestParam(name=UPDATE_USERNAME) String UserName,	
								 @RequestParam(name=UPDATE_ICON) String Icon,
								 @RequestParam(name=UPDATE_PROFILE) String Profile,
								 @ModelAttribute UserInfo userInfo,
								 ModelAndView mav
								 ) {
		
	
		
		//検索条件
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);
		
		//更新内容
		mav.addObject(ADDNAME_UPDATE_USERID,id);
		mav.addObject(ADDNAME_UPDATE_AFTER_LOGINID, LoginId);
		mav.addObject(ADDNAME_UPDATE_BEFORE_LOGINID, loginID);
		mav.addObject(ADDNAME_UPDATE_USERNAME, UserName);
		mav.addObject(ADDNAME_UPDATE_ICON, Icon);
		mav.addObject(ADDNAME_UPDATE_PROFILE, Profile);
		
		mav.addObject(USER_INFO, userInfo);
		mav.setViewName(DISPLAY_OF_UPDATE_INPUT);
		return mav;
	}
	
	//更新結果画面へ遷移
	@SuppressWarnings("unused")
	@RequestMapping(value=URL_UPDATE_RESULT, method=RequestMethod.POST)
	public ModelAndView updateResult(/*検索条件の保持
									  *検索させるときに同じDBのフィールド名にするためパラメーター名を変更
									  */
									 @RequestParam(name=LOGINID) String loginId,
									 @RequestParam(name=USERNAME) String userName,
									 @RequestParam(name=ICON) String icon,
									 @RequestParam(name=PROFILE) String profile,
									 //更新条件の保持
									 @RequestParam(name=UPDATE_USERID) Long userId,	
									 @RequestParam(name=UPDATE_AFTER_LOGINID) String LoginId,
									 @RequestParam(name=UPDATE_USERNAME) String UserName,
									 @RequestParam(name=UPDATE_ICON) String Icon,
									 @RequestParam(name=UPDATE_PROFILE) String Profile,
									 ModelAndView mav) {
		
		boolean check = false;
		
		//userId
		boolean UserId = repository.existsById(userId);
	
		//更新結果画面表示
		mav.addObject(ADDNAME_UPDATE_AFTER_LOGINID, LoginId);
		mav.addObject(ADDNAME_UPDATE_USERNAME, UserName);
		mav.addObject(ADDNAME_UPDATE_ICON, Icon);
		mav.addObject(ADDNAME_UPDATE_PROFILE, Profile);	
		
		//検索条件保持
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);
		
		if(UserId) {
			//更新
			UserInfo user = new UserInfo(userId, LoginId, UserName, Icon, Profile);
			repository.saveAndFlush(user);
			mav.setViewName(DISPLAY_OF_UPDATE_RESULT);
		}else {
			check = true;
			mav.addObject(ADDNAME_ERROR, check);
			mav.setViewName(DISPLAY_OF_UPDATE_RESULT);
		}
		return mav;
	}
}