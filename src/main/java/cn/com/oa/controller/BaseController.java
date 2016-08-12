package cn.com.oa.controller;

import java.util.HashMap;
import java.util.Map;



abstract class BaseController {
	
	
	public Map<String,Object> returnMap(Integer code,String msg,Object data){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}

}
