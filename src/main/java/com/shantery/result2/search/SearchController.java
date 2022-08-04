package com.shantery.result2.search;

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
	
	//index.htmlから送信されてくるのでpostになるので忘れるな！
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView searchInput(ModelAndView mav) {
		mav.setViewName(SEARCH_INPUT);
		//最初の状態はどちらにもチェックなし
		mav.addObject(ADDNAME_ICON, ICON_NOCHECK);
		return mav;
	}
	
	//検索条件が入力されたら、また検索結果画面で戻るボタンが押されたらpostで受け取る
	//検索条件の保持、バリデーションチェック、検索メソッド呼び出し
	//今後の変更点　リクエストマッピングのバリュー値
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView search(@RequestParam(value=LOGINID,required=false)String loginId,
			@RequestParam(value=USERNAME,required=false)String userName,
			@RequestParam(value=ICON,required=false)String[] iconnm,
			@RequestParam(value=PROFILE,required=false)String profile,
			@RequestParam(value=DISPLAY_BACK,required=false)String back,
			@ModelAttribute(FORM_MODEL)@Validated UserData userdata, BindingResult result,
			ModelAndView mav) {
		//チェックボックス保持用のキーワード
		String iconCheck = null;
		//チェックボックスの配列の長さ
		int length = 0;
		if(iconnm!=null) {
			length = iconnm.length;
		}
		//検索結果画面で戻るボタンが押されたら
		if(back != null) {
			mav.setViewName(SEARCH_INPUT);
			//検索条件保持
			mav.addObject(ADDNAME_LOGINID,loginId);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, iconnm[0]);
			mav.addObject(ADDNAME_PROFILE, profile);
		}else {
			if(!result.hasErrors()) {
				//エラーがなかったら
				boolean searchresult = false;
				String icon = null;
				List<UserData> list = null;
				List<UserData> list2 = null;
				List<UserData> lists = null;
				if(length == 2) {
					iconCheck = ICON_CHECKS;
					//icon-male,icon-femaleの両方にチェック
					icon = ICONMALE;
					//icon-maleの場合の検索結果
					list = service.getAll(loginId, userName, icon, profile);
					icon = ICONFEMALE;
					//icon-femaleの場合の検索結果
					list2 = service.getAll(loginId, userName, icon, profile);
					//2種類のアイコンで検索した結果を追加したリスト
					lists = Stream.concat(list.stream(), list2.stream()).collect(Collectors.toList());
				}else if(length == 1){
					if(iconnm[0].equals(ICONMALE)) {
						iconCheck = ICON_MALE;
						//icon-maleのみにチェック
						icon = ICONMALE;
						lists = service.getAll(loginId, userName, icon, profile);
					}else {
						iconCheck = ICON_FEMALE;		
						//icon-femaleのみにチェック
						icon = ICONFEMALE;
						lists = service.getAll(loginId, userName, icon, profile);
					}
				}else {
					iconCheck = ICON_NOCHECK;
					//チェックなしの場合は空文字にする
					icon = "";
					lists = service.getAll(loginId, userName, icon, profile);
				}
				//検索結果のサイズで件数判断
				if(lists.size() != 0) {
					//検索結果リストを格納
					mav.addObject(ADDNAME_DATALIST, lists);
					mav.addObject(ADDNAME_RESULT, searchresult);
				}else {
					searchresult = true;
					mav.addObject(ADDNAME_RESULT, searchresult);
				}
				//検索条件保持
				mav.addObject(ADDNAME_LOGINID,loginId);
				mav.addObject(ADDNAME_USERNAME, userName);
				mav.addObject(ADDNAME_ICON, iconCheck);
				mav.addObject(ADDNAME_PROFILE, profile);
				//検索結果画面に遷移
				mav.setViewName(SEARCH_RESULT);
			}else {
				//入力エラー表示
				mav.addObject(ADDNAME_ERROR, result.hasErrors());
				mav.addObject(ADDNAME_ICON, ICON_NOCHECK);
				//入力画面に遷移
				mav.setViewName(SEARCH_INPUT);
			}
		}
		return mav;
	}
}
