package com.shantery.result2.delete;
import static com.shantery.common.constants.ADDNAME_DATALIST;

import java.util.List;
/**
 * @author k.iwai
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.UserDeleteRepository;

@Controller
public class DeleteController {
	@Autowired
	UserDeleteRepository udRepository;
	 
	 // 削除確認画面へ移動
	@RequestMapping(value="/deleteConfirm",method=RequestMethod.POST)
	public ModelAndView deleteConfirm(
			//　値を取得
			@RequestParam(name="checkbox",required=false)Long[] userId,
			@RequestParam(value="loginId")String loginId,
			@RequestParam(value="userName")String userName,
			@RequestParam(value="icon")String icon,
			@RequestParam(value="profile")String profile,
			ModelAndView mav) {
		// UserInfo型リストを作成
		List<UserInfom> list = null;
		
		// ループに入る前にチェックボックス0番目の値を保持		
		List<UserInfom> lists = udRepository.findAllByUserId(userId[0]);
		
		// チェックボックスの数だけユーザーIDから値を検索
		for(int i=1; i<userId.length; i++) {
			list = udRepository.findAllByUserId(userId[i]);
			lists.addAll(list);
		}
		// リストに追加
		mav.addObject(ADDNAME_DATALIST, lists);
		
		// 検索条件の保持
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);	
		mav.setViewName("UserDeleteConfirm");
		return mav;
	}
	
	// 削除結果画面へ移動
	@RequestMapping(value="/deleteResult", method=RequestMethod.POST)
	public ModelAndView deleteResult(
			//　値を取得
			@RequestParam(name="userId",required=false)Long[] userId,
			@RequestParam(value="loginId2")String loginId,
			@RequestParam(value="userName2")String userName,
			@RequestParam(value="icon2")String icon,
			@RequestParam(value="profile2")String profile,
			ModelAndView mav) {
		
		// UserInfo型リストを作成
		List<UserInfom> list = null;
		
		// ループに入る前にチェックボックス0番目の値を保持		
		List<UserInfom> lists = udRepository.findAllByUserId(userId[0]);
		
		// チェックボックスの数だけユーザーIDから値を検索
		for(int i=1; i<userId.length; i++) {		
			list = udRepository.findAllByUserId(userId[i]);
			lists.addAll(list);			
		}
		// リストに追加
		mav.addObject(ADDNAME_DATALIST, lists);
		
		// 1件づつ削除を実行
		for(int i=0; i<userId.length; i++) {
			udRepository.deleteByUserId(userId[i]);
		}		
		// 検索条件保持
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);		
		mav.setViewName("UserDeleteResult");
		return mav;
	}	
}
