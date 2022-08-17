package com.shantery.common;

public class constants {
	//name属性名
	public static final String USERID = "userId";
	public static final String LOGINID = "loginId";
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "password";
	public static final String ICON = "icon";
	public static final String PROFILE = "profile";
	public static final String WRITING = "writing";
	public static final String CHECKBOX = "checkbox";
	
	//iconのvalue属性
	public static final String ICONMALE = "icon-male";
	public static final String ICONFEMALE = "icon-female";
	
	//戻るボタンのvalue値
	public static final String DISPLAY_BACK = "back";
	public static final String DISPLAY_BACKS = "backs";
	
	//　SQL文関連
	public static final String WHERE_LOGINID = "from UserData where loginId =";
	public static final String WHERE_LOGINID2 = "from UserLoginData where loginId =";
	public static final String WHERE_USERNAME = "from UserData where userName LIKE";
	public static final String WHERE_ICON = "from UserData where icon =";
	public static final String WHERE_PROFILE = "from UserData where profile LIKE";
	public static final String AND_USERNAME = "AND userName LIKE";
	public static final String AND_ICON = "AND icon =";
	public static final String AND_PROFILE = "AND profile LIKE";
	public static final String AND_PASSWORD = "AND password =";
	
	//iconの種類判断用
	public static final String ICON_CHECKS = "checks";
	public static final String ICON_NOCHECK = "noCheck";
	public static final String ICON_MALE = "icon-male";
	public static final String ICON_FEMALE = "icon-female";
	
	//Entityクラスのテーブル名、フィールド名、カラム名
	public static final String TABLE_USERS = "users";
	public static final String TABLE_SHOUTS = "shouts";
	public static final String FIELD_LOGINID = "loginId";
	public static final String FIELD_USERNAME = "userName";
	public static final String FIELD_ICON = "icon";
	public static final String FIELD_PROFILE = "profile";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_LOGINID = "loginid";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_SHOUTSID = "shoutsid";
	
	//コントローラー機能
	public static final String FORM_MODEL = "formModel";
	public static final String USER_INFO= "userInfo";
	
	//画面遷移先
	public static final String DISPLAY_OF_INDEX = "index";
	public static final String DISPLAY_OF_TOP = "top";
	public static final String DISPLAY_OF_SEARCH_RESULT = "UserSearchResult";
	public static final String DISPLAY_OF_SEARCH_INPUT = "UserSearchInput";
	public static final String DISPLAY_OF_USERINFO_INPUT = "UserInfoInput";
	public static final String DISPLAY_OF_USERINFO_CONFIRM = "UserInfoConfirm";
	public static final String DISPLAY_OF_USERINFO_RESULT = "UserInfoResult";
	public static final String DISPLAY_OF_UPDATE_INPUT = "UserUpdateInput";
	public static final String DISPLAY_OF_UPDATE_ERROR = "UserUpdateInputError";
	public static final String DISPLAY_OF_UPDATE_CONFIRM = "UserUpdateConfirm";
	public static final String DISPLAY_OF_UPDATE_RESULT = "UserUpdateResult";
	public static final String DISPLAY_OF_DELETE_CONFIRM = "UserDeleteConfirm";
	public static final String DISPLAY_OF_DELETE_RESULT = "UserDeleteResult";
	
	//URL
	public static final String URL_INFO_INPUT = "/UserInfoInput";		//新規登録画面
	public static final String URL_INFO_CONFIRM = "/UserInfoConfirm";	//新規登録確認画
	public static final String URL_INFO_RESULT = "/InfoResult";			//新規登録結果
	public static final String URL_BOARD ="/shouter";					//掲示板
	public static final String URL_ADD_SHOUT = "/addshout";				//叫ぶ
	public static final String URL_LOGOUT = "/logout";					//ログアウト
	public static final String URL_SEARCH_INPUT = "/searchInput";		//検索入力
	public static final String URL_SEARCH = "/search";					//検索結果
	public static final String URL_RESULT_BACK = "/resultback";			//再検索
	public static final String URL_UPDATE = "/update/{id}";				//編集入力
	public static final String URL_UPDATE_INPUT = "/UserUpdateInput";	//戻るボタンを押したときの編集画面
	public static final String URL_UPDATE_CONFIRM = "/UserUpdateConfirm"; //更新内容確認
	public static final String URL_UPDATE_RESULT = "/UserUpdateResult";	//更新結果
	public static final String URL_DELETE_CONFIRM = "/deleteConfirm";	//削除確認
	public static final String URL_DELETE_RESULT = "/deleteResult";		//削除結果
	
	//addObject,値保管時の第一引数名
	public static final String ADDNAME_USERID = "userId";
	public static final String ADDNAME_LOGINID = "loginId";
	public static final String ADDNAME_PASSWORD = "password";
	public static final String ADDNAME_USERNAME = "userName";
	public static final String ADDNAME_ICON = "icon";
	public static final String ADDNAME_PROFILE = "profile";
	public static final String ADDNAME_DATALIST = "datalist";
	public static final String ADDNAME_SHOUTLIST = "shoutlist";
	public static final String ADDNAME_RESULT = "result";
	public static final String ADDNAME_ERROR = "error";
	public static final String ADDNAME_CHECKBOX = "checkbox";
	public static final String ADDNAME_UPDATE_USERID = "userId";
	public static final String ADDNAME_UPDATE_BEFORE_LOGINID = "loginID";
	public static final String ADDNAME_UPDATE_AFTER_LOGINID = "LoginId";
	public static final String ADDNAME_UPDATE_USERNAME = "UserName";
	public static final String ADDNAME_UPDATE_ICON = "Icon";
	public static final String ADDNAME_UPDATE_PROFILE = "Profile";
	
	//ログイン用判断フラグ
	public static final String DIFFERENT_INFORMATION = "differinfo";
	public static final String BLANK = "blank";
	
	//新規登録用判断フラグ
	public static final String INFO_CHECK = "info";
	
	//新規登録のLoginId重複エラー用
	public static final String DUPLICATION_ERROR = "error2";
	
	//検索条件保持
	public static final String LOGINID2 = "loginId2";
	public static final String USERNAME2 = "userName2";
	public static final String ICON2 = "icon2";
	public static final String PROFILE2 = "profile2";
	
	//更新情報保持
	public static final String UPDATE_USERID = "userId";
	public static final String UPDATE_AFTER_LOGINID = "LoginId";
	public static final String UPDATE_BEFORE_LOGINID = "loginID";
	public static final String UPDATE_USERNAME = "UserName";
	public static final String UPDATE_ICON = "Icon";
	public static final String UPDATE_PROFILE = "Profile";
	
	//チェックボックス用判断フラグ
	public static final String CHECKBOX_CHECK = "check";
}
