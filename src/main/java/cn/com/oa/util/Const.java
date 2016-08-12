package cn.com.oa.util;



public class Const {

	public static String HX_APPLICATION=null;
	public static String HX_ACCESS_TOKEN=null;
	public static String HX_URL=null;
	public static String HX_CLIENT_ID=null;
	public static String HX_CLIENT_SECRET=null;
	public static Long HX_TOKEN_TIME=null;
	
	public static final String SYSTEM_USERHOME = System.getProperties().getProperty("user.home");
	public static final String ATTACHMENT_PATH=SYSTEM_USERHOME+"/OA_ATTACHMENT/";
	
	public static final String PERMISSION_ADMIN="admin";
	public static final String PERMISSION_SUPERADMIN="superadmin";
	public static final String PERMISSION_COMMONLYADMIN="commonlyadmin";
	
	public static final String PERMISSION_TOPUSER="topuser";
	public static final String PERMISSION_COMMONUSER="commonuser";
}
