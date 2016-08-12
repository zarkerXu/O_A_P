package cn.com.oa.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.oa.model.Attachment;
import cn.com.oa.model.Doc;
import cn.com.oa.model.Organ;
import cn.com.oa.model.Page;
import cn.com.oa.model.Task;
import cn.com.oa.model.User;
import cn.com.oa.service.AttachmentService;
import cn.com.oa.service.DocService;
import cn.com.oa.service.OrganService;
import cn.com.oa.service.TaskService;
import cn.com.oa.service.UserService;
import cn.com.oa.util.Const;
import cn.com.oa.util.FileUpload;
import cn.com.oa.util.UuidUtil;
import cn.com.oa.util.WebUtil;

@Controller
@RequestMapping("/doc")
public class DocController extends BaseController {
	@Resource
	private DocService docService;
	@Resource
	private AttachmentService attachmentService;
	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private OrganService organservice;

	@RequestMapping(value = "/docInputIndex", method = RequestMethod.GET)
	public ModelAndView docInputIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("doc/docInputIndex");
		return mav;
	}
	/**
	 * 公文发送
	 * @param doc
	 * @param files
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/docSend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docSend(
			Doc doc,
			@RequestParam(value = "upfile", required = false) MultipartFile[] files,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		doc.setCuid(String.valueOf(currentRoot.getPrincipal()));
		String[] oidList = doc.getDepartment().split(",");
		doc.setSignNum(oidList.length);
		String id = docService.insert(doc);
		if (files != null) {
			for (MultipartFile file : files) {
				String extName = null;
				if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
					extName = file.getOriginalFilename().substring(
							file.getOriginalFilename().lastIndexOf("."));
				}
				String url = FileUpload.fileUp(file, Const.ATTACHMENT_PATH,
						UuidUtil.get32UUID());
				Attachment attachment = new Attachment();
				attachment.setFileName(file.getOriginalFilename());
				attachment.setFileSize(String.valueOf(file.getSize() / 1024));
				attachment.setPid(id);
				attachment.setSuffix(extName);
				attachment.setType(1);
				attachment.setUrl(url);
				attachmentService.insert(attachment);
			}
		}
		for (String oid : oidList) {
			Task task = new Task();
			task.setDid(id);
			task.setOid(oid);
			task.setType(1);
			task.setSignStatus(1);
			taskService.insert(task);
		}
		List<String> list=userService.findUsernamebyOrganizations(doc.getDepartment());
		if(list.size()!=0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("action", "docSend");
			map.put("id", id);
			WebUtil.sendText(list.toArray(new String[list.size()]),JSON.toJSONString(map));
		}else{
			return returnMap(1,"","无常用联系人");
		}
		return returnMap(0, null, "发送成功");
	}

	@RequestMapping(value = "/docReceiveIndex", method = RequestMethod.GET)
	public ModelAndView docReceiveIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("doc/docReceiveIndex");
		return mav;
	}
	
	/**
	 * 签收
	 * @param tid
	 * @return
	 */

	@RequestMapping(value = "/docSign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docSign(String tid) {
		
		if (taskService.find(tid).getSignStatus() == 1) {
			Subject currentRoot = SecurityUtils.getSubject();
			String uid = (String) currentRoot.getPrincipal();
			Task task = new Task();
			task.setId(tid);
			task.setSignStatus(0);
			task.setUid(uid);
			taskService.update(task);
			if(docService.isSign(tid)){
				Task task2=taskService.find(tid);
				Doc doc=new Doc();
				doc.setId(task2.getDid());
				doc.setSignStatus(0);
				docService.update(doc);
			}
			return returnMap(0, "", null);
		}else{
			return returnMap(1, "已签收", null);
		}
	}

	@RequestMapping(value = "/docIndex", method = RequestMethod.GET)
	public ModelAndView docIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("doc/docIndex");
		return mav;
	}

	@RequestMapping(value = "/docAuditIndex", method = RequestMethod.GET)
	public ModelAndView docAuditIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("doc/docAuditIndex");
		return mav;
	}
/**
 * 公文审计
 * @param doc
 * @param page
 * @param request
 * @return
 */
	@RequestMapping(value = "/docSelect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docSelect(Doc doc, Page page,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		Page pageObj = new Page();
		doc.setOrderBy("signStatus desc,createTime desc");
		try {
			currentRoot.checkPermission(Const.PERMISSION_SUPERADMIN);
			pageObj = docService.findByParameterToPage(doc, page);
			return returnMap(0, "", pageObj);
		} catch (AuthorizationException e) {
			try {
				currentRoot.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				String id = (String) currentRoot.getPrincipal();
				User user = userService.find(id);
				List<String> userids = userService.selectuserid(user);
				System.out.println("userid" + userids);
				String uids = "'" + id + "',";
				for (int i = 0; i < userids.size(); i++) {
					uids += "'" + userids.get(i) + "'" + ",";
				}
				uids = uids.substring(0, uids.length() - 1);
				page.setUserids(uids);
				pageObj = docService.findByParameterToPage(doc, page);
				return returnMap(0, "", pageObj);

			} catch (AuthorizationException e2) {
			}
		}

		return returnMap(0, "", null);
	}
	
	@RequestMapping(value = "/getDoc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDoc(
			@RequestParam(value = "id", required = true) String id) {
		return returnMap(0, null, docService.find(id));
	}
/**
 * 公文删除
 * @param doc
 * @param page
 * @param request
 * @return
 */
	@RequestMapping(value = "/docdelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docdelete(Doc doc, Page page,
			HttpServletRequest request) {
		String ids = page.getIds();
		ids = ids.substring(0, ids.length() - 1);
		String ides[] = ids.split("\\,");
		String id = "";
		for (int i = 0; i < ides.length; i++) {
			String singid = ides[i];
			id += "'" + singid + "'" + ",";
		}
		id = id.substring(0, id.length() - 1);
		docService.delete(id);
		return docSelect(doc, page, request);
	}
/**
 * 公文接收
 * @param doc
 * @param page
 * @param request
 * @return
 */
	@RequestMapping(value = "/docReceive", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docReceive(Doc doc, Page page,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		page.setUserids(uid);
		page = docService.findByTaskToPage(doc, page);
		return returnMap(0, "", page);
	}
	/**
	 * 已发布公文
	 * @param doc
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/docUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docUser(Doc doc, Page page,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		doc.setOrderBy("signStatus desc,createTime desc");
		page.setUserids(uid);
		page = docService.findByUserToPage(doc, page);
		return returnMap(0, "", page); 
	}

	@RequestMapping(value = "/getAttach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAttach(String pid) {

		Attachment attachment = new Attachment();
		attachment.setPid(pid);
		attachment.setType(1);
		List<Attachment> attachmentList = attachmentService
				.findByParameter(attachment);
		return returnMap(0, null, attachmentList);
	}
	@RequestMapping(value = "/getGGAttach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGGAttach(String pid) {

		Attachment attachment = new Attachment();
		attachment.setPid(pid);
		attachment.setType(3);
		List<Attachment> attachmentList = attachmentService
				.findByParameter(attachment);
		return returnMap(0, null, attachmentList);
	}
	/**
	 * 添加常用单位
	 * @param organ
	 * @return
	 */
	@RequestMapping(value="/insertMagroup",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insertMagroup(Organ organ){
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		organ.setUid(uid);
		organservice.insert(organ);
		return returnMap(0,null,null);
	}
	
	/**
	 * 获取常用单位
	 * @param select
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getMagroupAll",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getMagroupAll(String select,Organ organ){
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		if(select.equals("all")){
			organ.setUid(uid);
		List<Organ> organs=new ArrayList<Organ>();
		organs=organservice.select(organ);
		return returnMap(0,"",organs);
		}
		else if(select.equals("one")){
			Organ organd=new Organ();
			organd=organservice.selectone(organ.getId());
			return returnMap(0,"",organd);
		}
		return returnMap(0,"",null);
	}
	
	@RequestMapping(value="/deletethis",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deletethis(String id){
		organservice.delete(id);
		return returnMap(0,"",null);
	}
	
	/**
	 * 修改常用单位
	 * @param id
	 * @param organ
	 * @return
	 */
	@RequestMapping(value="/updateMagroup",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(String id,Organ organ){
		organ.setId(id);
		organservice.update(organ);
		return returnMap(0,"",null);
	}
	/**
	 * 公文催收
	 * @param oid
	 * @param did
	 * @return
	 */
	@RequestMapping(value="/docUrge",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> docUrge(String oid,String did,HttpServletRequest request){
		BufferedReader br;
		try {
			br = request.getReader();
			String str, wholeStr = "";
			while ((str = br.readLine()) != null) {
				wholeStr += str;
			}
			System.out.println(wholeStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		User user=new User();
		user.setOid(oid);
		user.setIsmain(0);
		List<User> userList=userService.findByParameter(user);
		List<String> list=new ArrayList<String>();
		for(User user1:userList){
			list.add(user1.getAccount());
		}
		if(list.size()!=0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("action", "docUrge");
			map.put("id", did);
			WebUtil.sendText(list.toArray(new String[list.size()]),JSON.toJSONString(map));
			return returnMap(0,"","已催收");
		}else{
			return returnMap(1,"","无常用联系人");
		}
	}

	
	
	
	

}
