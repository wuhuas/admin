package com.knowledge.common.base.enums;

/**
 * 
 * 异常枚举
 * 
 * @author francis
 * 
 */
public enum EnumResult {

	SUCCESS("0", "成功！"),

	ERROR("1", "網絡異常.請稍後重試."),

	ERROR_PARAMS("2", "參數錯誤."),

	ERROR_MSG_CODE("3", "驗證碼錯誤."),

	DEFIN_ACCOUNT("4", "用戶不存在."),

	LOCKED_ACCOUNT("5", "用戶已被鎖定."),

	ERROR_PASSWORD("6", "密碼錯誤."),

	ERROR_USER_NOT_EXIST("7", "該用戶不存在."),

	ERROR_USER_PASSWORD("8", "密碼錯誤."),

	ERROR_DATA_DEFIND("9", "數據不存在."),

	ERROR_USER_INFO("10", "用戶信息獲取失敗."),

	ERROR_USER_OLD_PASS_DIFFERENT("11", "舊密碼錯誤."),

	PARAM_PHONE_NULL("12", "手機號碼不能為空"),

	PARAM_ACCOUNT_NULL("13", "賬號不能為空"),

	PARAM_PASSWORD_NULL("14", "密碼不能為空"),

	PARAM_NICKNAME_NULL("15", "暱稱不能為空"),

	PARAM_NICKNAME_EXIST("16", "暱稱已存在"),

	ERROR_RESOURCE_DELETE("17","請先刪除該資源的子項."),
	
	ERROR_PARAM("18","參數為空."),
	
	ERROR_EMAIL_USE("19","郵箱已被佔用."),
	
	ERROR_ACTIVE_NOT("20","賬號未激活."),
	
	ERROR_NOT_TOKEN("21","請登錄."),
	
	ERROR_TOKEN_INVALID("22","Token失效."),
	
	ERROR_ACTIVE_FAIL("23","激活碼失效，激活失敗"),
	
	ERROR_URL_TIMEOUT("24","鏈接超時，請重新註冊郵箱"),
	
	ERROR_EMAIL_NOT_REGISTER("25","郵箱未註冊."),
	
	ERROR_KEYWORD_DOES_EXIST("26","關鍵詞已存在."),
	
	ERROR_URL_INVALID("27","鏈接失效."),
	
	ERROR_KAPTCHA("28","驗證碼錯誤."),
	
	ERROR_KAPTCHA_NOTFIND("29","驗證碼未找到."),
	
	ERROR_KAPTCHA_TIMEOUT("30","驗證碼過去."),
	
	ERROR_KAPTCHA_FAIL("31","驗證碼渲染失敗."),
	
	ERROR_NEWS_CANT_TOP("28","該咨詢已下架 ，無法置頂."),
	
	ERROR_RECOMMEND_WORKS_ONLY_THREE("29","作品推薦最大設置3個"),
	
	ERROR_DRAFT_UPPER_MAX("30","征稿啟示上架最大設置1個"),
	
	ERROR_NEWS_TOP_MAX("31","資訊每個分類置頂最大設置3個"),
	
	ERROR_COMPOSITION_STOP("32","作文不能首頁推薦"),
	
	ERROR_NOT_BINDING("33","未綁定系统用户"),
	
	ERROR_USE_THIRD_BINDING("33","第三方賬號已被佔用"),
	
	ERROR_USE_BINDING("34","該用戶已被綁定"),
	
	//ERROR_SUB_EMAIL_NOT_EXIST("35","訂閱郵箱不存在"),
	
	ERROR_NOT_SUB_EMAIL("35","郵箱未訂閱"),
	
	ERROR_COMPLIMENTARY_DATE("36","赠阅日期不能為空"),
	
	ERROR_READ_DATE("37","期刊閱讀日期不能為空"),
	
	ERROR_ILLEGAL_OPERATION("400","Illegal operation !!!"),
	
	ERROR_UNAUTHORIZED("401","未授權"),
	
	FILE_IS_NOTEMPTY("1012", "文件不能為空"),

	FILE_UPLOAD_FAIL("1013", "文件上傳失敗"),
	
	FILE_DOWNLOAD_FAIL("1014", "文件加载失敗"),
	
	FILE_UPLOAD_TYPE_ERROR("1011","不支持的文件類型."),
	
	FILE_UPLOAD_TOTAL_PARTS("1021","文件分片总数不应超过10000")
	
	;
	private String code;

	private String msg;

	EnumResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
