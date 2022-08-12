package com.shantery.result2.delete;
/**
 * @author k.iwai
 *
 */

import static com.shantery.common.constants.ADDNAME_DATALIST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.UserDeleteRepository;
import static com.shantery.common.constants.*;

@Controller
public class DeleteController {
	@Autowired
	UserDeleteRepository udRepository;
	 
	 // 削除確認画面へ移動
	@RequestMapping(value="/deleteConfirm",method=RequestMethod.POST)
	public ModelAndView deleteConfirm(
			//　それぞれの値を取得
			@RequestParam(name=CHECKBOX,required=false)Long[] userId,
			@RequestParam(value=LOGINID)String loginId,
			@RequestParam(value=USERNAME)String userName,
			@RequestParam(value=ICON)String icon,
			@RequestParam(value=PROFILE)String profile,
			ModelAndView mav) {
		// UserInfom型リストを作成
		List<UserInfom> list = null;
		
		// ループに入る前にチェックボックス0番目の値を保持		
		List<UserInfom> lists = udRepository.findAllByUserId(userId[0]);
		
		// チェックボックスの数だけユーザーIDから値を検索
		for(int i=1; i<userId.length; i++) {
			list = udRepository.findAllByUserId(userId[i]);
			lists.addAll(list);
		}
		// リストに追加(削除確認画面で表示するデータ)
		mav.addObject(ADDNAME_DATALIST, lists);
		
		// 検索条件の保持
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);	
		mav.setViewName(DISPLAY_OF_DELETE_CONFIRM);
		return mav;
	}
	
	// 削除結果画面へ移動
	@RequestMapping(value="/deleteResult", method=RequestMethod.POST)
	public ModelAndView deleteResult(
			//　値を取得
			@RequestParam(name=USERID,required=false)Long[] userId,
			@RequestParam(value=LOGINID)String loginId,
			@RequestParam(value=USERNAME)String userName,
			@RequestParam(value=ICON)String icon,
			@RequestParam(value=PROFILE)String profile,
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
		// リストに追加(削除結果画面表示用)
		mav.addObject(ADDNAME_DATALIST, lists);
		
		// 1件づつ削除を実行
		for(int i=0; i<userId.length; i++) {
			udRepository.deleteByUserId(userId[i]);
		}		
		// 検索条件保持
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);		
		mav.setViewName(DISPLAY_OF_DELETE_RESULT);
		return mav;
	}	
}
