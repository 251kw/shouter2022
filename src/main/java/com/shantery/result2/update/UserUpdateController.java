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

@Controller
public class UserUpdateController {
	@Autowired
	UserUpdateRepository repository;
	
	//編集画面へ遷移
	@RequestMapping(value="/index/{id}", method=RequestMethod.POST)
	public ModelAndView edit(@PathVariable Long id,
							 @RequestParam(name="loginId") String loginId,
							 @RequestParam(name="userName") String userName,
							 @RequestParam(name="icon") String icon,
							 @RequestParam(name="profile") String profile,
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
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		//編集ボタンから渡されたデータ
		mav.addObject("userId", userId);
		mav.addObject("LoginId", LoginId);
		mav.addObject("loginID", loginID);
		mav.addObject("UserName", UserName);
		mav.addObject("Icon", Icon);
		mav.addObject("Profile", Profile);
		mav.addObject("userInfo", userInfo);
		mav.setViewName("UserUpdateInput");
		return mav;
	}
	
	//更新確認画面へ遷移
	@RequestMapping(value="/UserUpdateConfirm", method=RequestMethod.POST)
	public ModelAndView editCheck(//編集条件の保持	
								  //フィールド名と同じ名前が優先される為、使いたい方をフィールド名と同じ名前にする
								  @RequestParam(name="loginId") String loginId,	//変更後のログインID
								  @RequestParam(name="userName") String userName,
								  @RequestParam(name="Icon") String Icon,
								  @RequestParam(name="profile") String profile,
								  @Validated @ModelAttribute UserInfo userInfo,
			  					  BindingResult result,
								  @RequestParam(name="userId") String userId,
								  @RequestParam(name="loginID") String loginID,	//初期のログインID
								  //検索条件の保持
								  @RequestParam(name="loginId2") String loginId2,
								  @RequestParam(name="userName2") String userName2,
								  @RequestParam(name="icon2") String icon2,
								  @RequestParam(name="profile2") String profile2,
								  ModelAndView mav) {
		
		boolean check = false;
		
		//変更後のログインID検索
		UserInfo user = repository.findByLoginId(loginId);
		
		//検索条件の保持
		mav.addObject("loginId", loginId2);
		mav.addObject("userName", userName2);
		mav.addObject("icon", icon2);
		mav.addObject("profile", profile2);
		//編集条件の保持
		mav.addObject("userId", userId);
		mav.addObject("LoginId", loginId);	//変更後
		mav.addObject("loginID", loginID);	//変更前(編集画面に遷移して一番初めに表示されるログインID)
		mav.addObject("UserName", userName);
		mav.addObject("Icon", Icon);
		mav.addObject("Profile", profile);
		
		//フィールド毎にバリテーションチェック
		//全て入力されていた場合
		if(result.hasErrors()){
			mav.addObject("userInfo", userInfo);
			mav.setViewName("UserUpdateInput");
		}else {
			//変更前と変更後が同じ場合
			if(loginId.equals(loginID)) {
				mav.setViewName("UserUpdateConfirm");
			}else {				
				//変更が生じ、かつ既存のログインIDでないか確認
				if(user == null) {
					mav.setViewName("UserUpdateConfirm");
				}else {
					check = true;
					mav.addObject("errorloginID", check);
					mav.setViewName("UserUpdateInput");
				}
			}
		}
			return mav;
	}
	
	//更新結果画面へ遷移
	@RequestMapping(value="/UserUpdateResult", method=RequestMethod.POST)
	public ModelAndView updateResult(/*検索条件の保持
									  *検索させるときに同じDBのフィールド名にするためパラメーター名を変更
									  */
									 @RequestParam(name="loginId") String loginId,
									 @RequestParam(name="userName") String userName,
									 @RequestParam(name="icon") String icon,
									 @RequestParam(name="profile") String profile,
									 //更新条件の保持
									 @RequestParam(name="userId") Long userId,
									 @RequestParam(name="LoginId") String LoginId,
									 @RequestParam(name="UserName") String UserName,
									 @RequestParam(name="Icon") String Icon,
									 @RequestParam(name="Profile") String Profile,
									 ModelAndView mav) {
		//更新
		UserInfo user = new UserInfo(userId, LoginId, UserName, Icon, Profile);
		repository.saveAndFlush(user);
		
		//更新結果画面表示
		mav.addObject("LoginId", LoginId);
		mav.addObject("UserName", UserName);
		mav.addObject("Icon", Icon);
		mav.addObject("Profile", Profile);
		
		//検索条件保持
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		
		mav.setViewName("UserUpdateResult");
		return mav;
	}
}
