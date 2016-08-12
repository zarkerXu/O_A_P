package cn.com.oa.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSON;

public class WebUtil {

	public static boolean getToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grant_type", "client_credentials");
		map.put("client_id", Const.HX_CLIENT_ID);
		map.put("client_secret", Const.HX_CLIENT_SECRET);
		StringEntity entity = null;
		if (Const.HX_TOKEN_TIME != null) {
			Long l = new Date().getTime() - Long.valueOf(Const.HX_TOKEN_TIME);
			if (l < 60000) {
				return false;
			}
		}
		try {
			entity = new StringEntity(JSON.toJSONString(map));
			String json = HttpUtil.post(Const.HX_URL + "token", entity, false);
			Map<String, Object> map2 = JSON.parseObject(json, Map.class);
			Const.HX_ACCESS_TOKEN = String.valueOf(map2.get("access_token"));
			Const.HX_APPLICATION = String.valueOf(map2.get("application"));
			Const.HX_TOKEN_TIME = new Date().getTime();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 环信注册
	public static boolean register(String username, String password,
			String nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		map.put("nickname", nickname);
		StringEntity entity = null;
		String json = null;
		try {
			entity = new StringEntity(JSON.toJSONString(map));
			json = HttpUtil.post(Const.HX_URL + "token", entity, true);
			Map<String, Object> map2 = JSON.parseObject(json, Map.class);
			if (map2.get("entities") == null) {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			if (json != null && json.equals("401")) {
				if (getToken()) {
					register(username, password, nickname);
				}
			}
		}
		return false;
	}

	public static boolean sendText(String[] users, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		map.put("target_type", "users");
		map.put("target", users);
		msgMap.put("type", "cmd");
		msgMap.put("action", msg);
		map.put("msg", msgMap);
		StringEntity entity = null;
		String json = null;
		try {
			entity = new StringEntity(JSON.toJSONString(map));
			json = HttpUtil.post(Const.HX_URL + "messages", entity, true);
			Map<String, Object> map2 = JSON.parseObject(json, Map.class);
			if (map2.get("entities") == null) {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			if (json != null && json.equals("401")) {
				if (getToken()) {
					sendText(users, msg);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value",
				PasswordUtil.simpleEncpyt("admin_" + new Date().getTime()));
		StringEntity entity = null;
		try {
			entity = new StringEntity(JSON.toJSONString(map));
			String json = HttpUtil
					.post("http://localhost:8090/OA_console/phone/getOrganizationData",
							entity, false);
			System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
