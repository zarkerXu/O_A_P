package cn.com.oa.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.oa.export.ExcelView;
import cn.com.oa.model.Task;
import cn.com.oa.service.DocService;
import cn.com.oa.service.MeetService;
import cn.com.oa.service.TaskService;



@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	@Resource
	private TaskService taskService;
	@Resource
	private DocService docService;
	@Resource
	private MeetService meetService;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	/**
	 * 获取通知数量
	 * @return
	 */
	@RequestMapping(value = "/getIndexNum", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIndexNum() {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("docNum", taskService.getIndexNum(uid, 1));
		map.put("meetNum", taskService.getIndexNum(uid, 2));
		map.put("docApproveNum", taskService.getNotApproveNum(uid, 1));
		map.put("meetApproveNum", taskService.getNotApproveNum(uid, 2));
		return returnMap(0, null, map);
	}
	
	@RequestMapping(value = "/signExcel")
	public ModelAndView signExcel(String id) {
		ModelAndView view=new ModelAndView();
		List<Task> tasks=new ArrayList<Task>();
		tasks=taskService.findCheckInfo(id);
		List<List<String>> list=new ArrayList<List<String>>();
		Integer i=1;
		for(Task task:tasks){
			List<String> stringlist=new ArrayList<String>();
			stringlist.add(String.valueOf(i));
			stringlist.add(task.getOrganame());
			stringlist.add(task.getUname());
			stringlist.add((task.getSignStatus()==0?"已签收":task.getSignStatus()==1?"未签收":""));
			list.add(stringlist);
			i++;
		}
		String title="";
		String name="";
		if(tasks.get(0).getType()==1){
			title="公文签收情况表";
			name=docService.find(id).getDocTitle();
		}else if(tasks.get(0).getType()==2){
			title="会议签收情况表";
			name=meetService.find(id).getName();
		}
		String[] heads=new String[]{"序号","部门","姓名","签收状况"};
		view.setView(new ExcelView(title,name,heads,list));
		return view;
	}
	
	/**
	 * 签收信息
	 * @param did
	 * @return
	 */
	@RequestMapping(value="/getCheckInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCheckInfo(String did){
		List<Task> tasks=new ArrayList<Task>();
		tasks=taskService.findCheckInfo(did);
		return returnMap(0,null,tasks);
	}
	

}
