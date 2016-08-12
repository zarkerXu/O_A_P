package  cn.com.oa.util;

import java.util.Date;


import org.springframework.core.io.ClassPathResource;

/**
 * PasswordUtil.java
 * 密码加密、解密操作
 *
 * @author zlh
 */
public class PasswordUtil {

	
	private static SimpleCipherDES des = new SimpleCipherDES(new ClassPathResource("s.key"));;
	
    /**
     * 解密操作
     * 
     * @param  待解密的字符串
     * @return 解密字符串
     */
	public static String simpleDecrypt(String pwd) {

		return new String(des.simpleStringDecrypt(pwd));
		
	}
	
    /**
     * 加密操作
     * 
     * @param  待加密的字符串
     * @return 加密结果字符串
     */
	public static String simpleEncpyt(String pwd) {

		return new String(des.simpleStringEncrypt(pwd.getBytes()));
		
	}
	
	
    /**
     * 测试
     * 
     */
	public static void main(String[] args) {
//		String acc_pwd="admin_11111";
//		//加密
//		String encpytStr=simpleEncpyt(acc_pwd);
//		System.out.println(acc_pwd+"加密过后的字符串是："+encpytStr);
//		
//		//解密
//		String decpytStr=simpleDecrypt(encpytStr);
//		System.out.println(acc_pwd+"解密过后的字符串是："+decpytStr);
//		System.out.println(simpleDecrypt("d055b3456838559ba077ed65db15a6485fb13fa655551938"));
		System.out.println(simpleEncpyt("admin_"+new Date().getTime()));
	}

}
