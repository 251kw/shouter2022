package com.shantery.result2.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.SearchRepository;
import static com.shantery.common.constants.*;
/**
 * @author s.ogata
 *
 */

@Controller
public class SearchController {
	
	@Autowired
	SearchRepository repository;
	
	@Autowired
	private SearchService service;
	
	//index.htmlから送信されてくるのでpost
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ModelAndView searchInput(ModelAndView mav) {
		mav.setViewName(DISPLAY_OF_SEARCH_INPUT);
		//最初の状態はどちらにもチェックなし
		mav.addObject(ADDNAME_ICON, ICON_NOCHECK);
		return mav;
	}

	//検索内容入力画面からindex.html画面に戻るときのメソッド
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String indexback() {
		return DISPLAY_OF_INDEX;
	}
	
	//検索条件の保持、バリデーションチェック、検索メソッド呼び出し
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView search(@RequestParam(value=LOGINID,required=false)String loginId,
			@RequestParam(value=USERNAME,required=false)String userName,
			@RequestParam(value=ICON,required=false)String[] icons,
			@RequestParam(value=PROFILE,required=false)String profile,
			@RequestParam(value=DISPLAY_BACK,required=false)String back,
			@ModelAttribute(FORM_MODEL)@Validated UserData userdata, BindingResult result,
			ModelAndView mav) {
		//チェックボックス保持用のキーワード
		String iconCheck = null;
		//チェックボックスの配列の長さ
		int length = 0;
		if(icons!=null) {
			length = icons.length;
		}
		//検索結果画面で戻るボタンが押されたら
		if(back != null) {
			mav.setViewName(DISPLAY_OF_SEARCH_INPUT);
			//検索条件保持
			mav.addObject(ADDNAME_LOGINID,loginId);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icons[0]);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.addObject(ADDNAME_CHECKBOX,CHECKBOX_NOCHECK);
		}else {
			if(!result.hasErrors()) {
				//エラーがなかったら
				//検索結果用フラグ
				boolean searchresult = false;
				String icon = null;
				//アイコン1種類の時用リスト
				List<UserData> list = null;
				//アイコンが2種類選択された時用の追加リスト
				List<UserData> list2 = null;
				//listとlist2を結合させたリスト
				List<UserData> lists = null;
				if(length == 2) {
					//アイコンが2種類選択された場合のフラグ
					iconCheck = ICON_CHECKS;
					//icon-male,icon-femaleの両方にチェック
					icon = ICONMALE;
					//icon-maleの場合の検索結果
					list = service.getAll(loginId, userName, icon, profile);
					icon = ICONFEMALE;
					//icon-femaleの場合の検索結果
					list2 = service.getAll(loginId, userName, icon, profile);
					//2種類のアイコンで検索した結果を結合したリスト
					lists = Stream.concat(list.stream(), list2.stream()).collect(Collectors.toList());
				}else if(length == 1){
					if(icons[0].equals(ICONMALE)) {
						//icon-maleの場合のフラグ
						iconCheck = ICON_MALE;
						//icon-maleのみにチェック
						icon = ICONMALE;
						lists = service.getAll(loginId, userName, icon, profile);
					}else {
						//icon-femaleの場合のフラグ
						iconCheck = ICON_FEMALE;		
						//icon-femaleのみにチェック
						icon = ICONFEMALE;
						lists = service.getAll(loginId, userName, icon, profile);
					}
				}else {
					//アイコンにチェックしない場合のフラグ
					iconCheck = ICON_NOCHECK;
					//チェックなしの場合は空文字にする
					icon = "";
					lists = service.getAll(loginId, userName, icon, profile);
				}
				//検索結果のサイズで件数判断
				if(lists.size() != 0) {
					//検索結果リストを格納
					mav.addObject(ADDNAME_DATALIST, lists);
					//検索結果がある場合、フラグはfalseのまま
					mav.addObject(ADDNAME_RESULT, searchresult);
				}else {
					//検索結果0件場合は、フラグをtrueにする
					searchresult = true;
					mav.addObject(ADDNAME_RESULT, searchresult);
				}
				//検索条件保持
				mav.addObject(ADDNAME_LOGINID,loginId);
				mav.addObject(ADDNAME_USERNAME, userName);
				mav.addObject(ADDNAME_ICON, iconCheck);
				mav.addObject(ADDNAME_PROFILE, profile);
				//検索結果画面に遷移
				mav.setViewName(DISPLAY_OF_SEARCH_RESULT);
			}else {
				//エラーがある場合は表示用に結果を格納
				mav.addObject(ADDNAME_ERROR, result.hasErrors());
				mav.addObject(ADDNAME_ICON, ICON_NOCHECK);
				//入力画面に遷移
				mav.setViewName(DISPLAY_OF_SEARCH_INPUT);
			}
		}
		mav.addObject("check", false);
		return mav;
	}
	
	//編集入力、更新結果、削除確認、削除結果から検索結果に戻るときの再検索メソッド
	@RequestMapping(value="/resultback",method=RequestMethod.POST)
	public ModelAndView resultback(@RequestParam(value=LOGINID2)String loginId,
								   @RequestParam(value=USERNAME2)String userName,
								   @RequestParam(value=ICON2)String icon,
								   @RequestParam(value=PROFILE2)String profile,
								   @RequestParam(value=USERID,required=false)Long[] userId,
								   @RequestParam(value=DISPLAY_BACK,required=false)String back,
								   ModelAndView mav) {
		//検索条件の保持
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);
		if(back != null) {
			//編集機能ではチェックボックスは関係ないので
			mav.addObject("check", false);
		}else {
			//削除項目のチェックボックスの保持
			ArrayList<Long> checkboxList = new ArrayList<Long>();
			for(Long checkboxNum: userId) {
				checkboxList.add(checkboxNum);
			}
			mav.addObject("check", true);
			mav.addObject(ADDNAME_CHECKBOX, checkboxList);
		}
			
		//アイコン1種類の時用リスト
		List<UserData> list = null;
		//アイコンが2種類選択された時用の追加リスト
		List<UserData> list2 = null;
		//listとlist2を結合させたリスト
		List<UserData> lists = null;
		if(icon.equals(ICON_NOCHECK)) {
			//チェックなしの場合は空文字にする
			icon = "";
			lists = service.getAll(loginId, userName, icon, profile);
		}else if(icon.equals(ICON_CHECKS)) {
			icon = ICON_MALE;
			//icon-maleの場合の検索結果
			list = service.getAll(loginId, userName, icon, profile);
			icon = ICON_FEMALE;
			//icon-femaleの場合の検索結果
			list2 = service.getAll(loginId, userName, icon, profile);
			//2種類のアイコンで検索した結果を結合したリスト
			lists = Stream.concat(list.stream(), list2.stream()).collect(Collectors.toList());
		}else {
			//icon-male.icon-femaleのどちらか
			lists = service.getAll(loginId, userName, icon, profile);
		}
		mav.addObject(ADDNAME_DATALIST, lists);
		mav.setViewName(DISPLAY_OF_SEARCH_RESULT);
		return mav;
	}
}
