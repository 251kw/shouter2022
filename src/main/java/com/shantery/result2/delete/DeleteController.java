package com.shantery.result2.delete;
import static com.shantery.common.constants.ADDNAME_DATALIST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.Repository;
/**
 * @author k.iwai
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.login.LoginService;
import com.shantery.result2.login.UserLoginData;
import com.shantery.result2.repositories.UserDeleteRepository;
import com.shantery.result2.search.UserData;
import com.shantery.result2.update.UserInfo;

@Controller
public class DeleteController {
	@Autowired
	UserDeleteRepository udRepository;
	 
	 // 削除確認画面へ移動
	@RequestMapping(value="/deleteConfirm",method=RequestMethod.POST)
	public ModelAndView deleteConfirm(
			//　チェックボックスの値を取得
			@RequestParam(name="checkbox",required=false)Long[] userId,
			@RequestParam(value="loginId")String loginId,
		   @RequestParam(value="userName")String userName,
		   @RequestParam(value="icon")String icon,
		   @RequestParam(value="profile")String profile,
			ModelAndView mav) {
		
		List<UserInfo> list = null;
		List<UserInfo> lists = udRepository.findAllByUserId(userId[0]);
		
	
		
		for(int i=1; i<userId.length; i++) {
		
			list = udRepository.findAllByUserId(userId[i]);
			lists.addAll(list);
				
		}
		mav.addObject(ADDNAME_DATALIST, lists);
		//検索条件の保持
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
	
		mav.setViewName("UserDeleteConfirm");
		return mav;
	}
	
	//削除結果画面へ移動
/*	@RequestMapping(value="/DeleteResult", method=RequestMethod.POST)
	public ModelAndView deleteResult(//検索条件の保持
		 @RequestParam(name="loginId") String loginId,
		 @RequestParam(name="userName") String userName,
		 @RequestParam(name="icon") String icon,
		 @RequestParam(name="profile") String profile,
		 ModelAndView mav) {
		//削除実行
		UserInfom user = new UserInfom(loginId, userName, icon, profile);
		Repository.deleteByUserId(user);
		
		//削除結果画面表示
		mav.addObject("LoginId", LoginId);
		mav.addObject("UserName", UserName);
		mav.addObject("Icon", Icon);
		mav.addObject("Profile", Profile);
		
		//検索条件保持
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		
		mav.setViewName("UserDeleteResult");
		return mav;
	}	*/
}
