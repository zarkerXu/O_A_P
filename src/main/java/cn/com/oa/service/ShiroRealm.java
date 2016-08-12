package cn.com.oa.service;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.com.oa.model.Organization;
import cn.com.oa.model.User;
import cn.com.oa.util.Const;

import java.util.List;

import javax.annotation.Resource;

/**
 * Class description
 *
 * @author zlh
 * @version 1.0.0, 16/03/31
 */
public class ShiroRealm extends AuthorizingRealm {
	@Resource
    private UserService userService;
	@Resource
	private OrganizationService organizationService;
   /* private String prefix;

    public void setKeyPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return super.getAuthorizationCacheKey(principals) + prefix;
    }*/


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User login=new User();
        login.setAccount(token.getUsername());
        List<User> userList=userService.findByParameter(login);
        User user=null;
        if(userList!=null){
        	user=userList.get(0);
        }else{
        	throw new AuthenticationException("account.not.activate");
        }
        System.out.println("doGetAuthenticationInfo:" + user.getAccount());
        return new SimpleAuthenticationInfo(user.getId(),
                String.valueOf(token.getPassword()),
                user.getAccount());

    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {
        	User user = userService.find((String) getAvailablePrincipal(principals));
        	Organization organization=organizationService.find(user.getOid());
        	if(user.getLevel()!=0){
        		info.addStringPermission(Const.PERMISSION_ADMIN);
        		if(user.getLevel()==1){
        			info.addStringPermission(Const.PERMISSION_SUPERADMIN);
        			//info.addStringPermission(Const.PERMISSION_COMMONLYADMIN);
        		}else if(user.getLevel()==2){
        			info.addStringPermission(Const.PERMISSION_COMMONLYADMIN);
        		}
        	}
        	if(organization.getPid().equals("0")){
        		info.addStringPermission(Const.PERMISSION_TOPUSER);
        	}else{
        		info.addStringPermission(Const.PERMISSION_COMMONUSER);
        	}
        	return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;

    }

}


//~ Formatted by Jindent --- http://www.jindent.com
