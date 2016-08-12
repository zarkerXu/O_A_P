package cn.com.oa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/home/index";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String name,String password) {
		Subject user = SecurityUtils.getSubject();
		if (name == null || name.equals("")) {
			return "redirect:/login";
		}
		UsernamePasswordToken token = new UsernamePasswordToken(name, "");
		try {
			user.login(token);
			return "redirect:/home/index";
		} catch (AuthenticationException e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/logout";
	}

}
