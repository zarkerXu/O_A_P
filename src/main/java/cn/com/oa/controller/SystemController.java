package cn.com.oa.controller;

import java.util.ArrayList;
import java.util.Date;
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
import cn.com.oa.model.Notice;
import cn.com.oa.model.Organization;
import cn.com.oa.model.OrganizationType;
import cn.com.oa.model.Task;
import cn.com.oa.model.Tree;
import cn.com.oa.model.User;
import cn.com.oa.service.AttachmentService;
import cn.com.oa.service.NoticeService;
import cn.com.oa.service.OrganizationService;
import cn.com.oa.service.OrganizationTypeService;
import cn.com.oa.service.TaskService;
import cn.com.oa.service.UserService;
import cn.com.oa.util.Const;
import cn.com.oa.util.FileUpload;
import cn.com.oa.util.UuidUtil;
import cn.com.oa.util.WebUtil;

@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController {
	@Resource
	private OrganizationService organizationService;

	@Resource
	private UserService userService;

	@Resource
	private OrganizationTypeService organizationTypeService;

	@Resource
	private NoticeService noticeService;

	@Resource
	private TaskService taskService;

	@Resource
	private AttachmentService attachmentService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.checkPermission(Const.PERMISSION_ADMIN);
			return "system/system";
		} catch (AuthorizationException e) {
		}
		return null;
	}

	@RequestMapping(value = "/userindex", method = RequestMethod.GET)
	public String userindex() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			return "system/userIndex";
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				return "system/userIndex";
			} catch (AuthorizationException e2) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = "/updateOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOrganization(Organization organization,
			String editType) {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		try {
			currentUser.checkPermission(Const.PERMISSION_ADMIN);
			if (editType.equals("add")) {
				organization.setCuid(uid);
				organizationService.insert(organization);
			} else if (editType.equals("edit")) {
				organization.setUuid(uid);
				organizationService.update(organization);
			}
			return returnMap(0, "", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/delOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delOrganization(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.checkPermission(Const.PERMISSION_ADMIN);
			organizationService.del(id);
			return returnMap(0, "", null);
		} catch (AuthorizationException e) {

		}
		return returnMap(1, "", null);
	}
	/**
	 * getContactsList in userindex
	 * @return
	 */
	@RequestMapping(value = "/getOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganization() {
		Subject currentUser = SecurityUtils.getSubject();
		List<Organization> list = new ArrayList<Organization>();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			list = getOrganizationList("0");
			return returnMap(0, "", list);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				String id = (String) currentUser.getPrincipal();
				User user = userService.find(id);
				Organization organization = organizationService.find(user
						.getRemark());
				organization.setOrganizationList(getOrganizationList(user
						.getRemark()));
				list.add(organization);
				return returnMap(0, "", list);
			} catch (Exception e2) {
			}
		}
		return returnMap(1, "", null);
	}
	
	@RequestMapping(value = "/getOrganizationTree", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganizationTree() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		User user = userService.find(uid);
		List<Organization> list = null;
		List<Tree> treeList = new ArrayList<Tree>();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			Organization organization = new Organization();
			organization.setPid("0");
			list = organizationService.findByParameter(organization);
			for (Organization organization2 : list) {
				Tree tree = new Tree(organization2);
				treeList.add(tree);
				tree.setChildren(getOrganizationTree(organization2.getId()));
			}
			return returnMap(0, "", treeList);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				List<Organization> organizations = organizationService
						.findParentById(user.getRemark());
				Tree tree = new Tree(organizations.get(0));
				tree.setChildren(getOrganizationTree(organizations.get(0)
						.getId()));
				treeList.add(tree);
				return returnMap(0, "", treeList);
			} catch (Exception e2) {
			}
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/getOrganizationData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganizationData() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		User user = userService.find(uid);
		List<Organization> list = new ArrayList<Organization>();
		try {
			currentUser.checkPermission(Const.PERMISSION_TOPUSER);
			Organization organization = new Organization();
			organization.setPid("0");
			list = organizationService.findByParameter(organization);
			for (Organization organization2 : list) {
				if (organization2.getId().equals(user.getOid())) {
					organization2.setOrganizationList(getOrganizationList(user
							.getOid()));
				}
			}
			return returnMap(0, "", list);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONUSER);
				List<Organization> organizations = organizationService
						.findParentById(user.getOid());
				Organization organization = organizations.get(0);
				organization
						.setOrganizationList(getOrganizationList(organizations
								.get(0).getId()));
				list.add(organization);
				return returnMap(0, "", list);
			} catch (Exception e2) {
			}
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/getOrganizationTreeData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganizationTreeData() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		User user = userService.find(uid);
		List<Organization> list = null;
		List<Tree> treeList = new ArrayList<Tree>();
		try {
			currentUser.checkPermission(Const.PERMISSION_TOPUSER);
			Organization organization = new Organization();
			organization.setPid("0");
			list = organizationService.findByParameter(organization);
			for (Organization organization2 : list) {
				Tree tree = new Tree(organization2);
				treeList.add(tree);
				if (organization2.getId().equals(user.getOid())) {
					tree.setChildren(getOrganizationTree(user.getOid()));
				}
			}
			return returnMap(0, "", treeList);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONUSER);
				List<Organization> organizations = organizationService
						.findParentById(user.getOid());
				Tree tree = new Tree(organizations.get(0));
				tree.setChildren(getOrganizationTree(organizations.get(0)
						.getId()));
				treeList.add(tree);
				return returnMap(0, "", treeList);
			} catch (Exception e2) {
			}
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/adminIndex", method = RequestMethod.GET)
	public ModelAndView adminIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/adminIndex");
		String[] users = new String[] { "user2" };
		WebUtil.sendText(users, "发送成功");
		return mav;
	}

	@RequestMapping(value = "/getAdmin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAdmin() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			List<User> list = new ArrayList<User>();
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			list = userService.findAllAdmin();
			System.out.println(list.get(1).getOrgname());
			return returnMap(0, "", list);
		} catch (AuthorizationException e) {
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateAdmin(User user, String editType) {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			if (editType.equals("add")) {
				user.setLevel(2);
				user.setOid("1");
				user.setCuid(uid);
				userService.insert(user);
			} else if (editType.equals("edit")) {
				user.setUuid(uid);
				userService.update(user);
			}
			return returnMap(0, "", null);
		} catch (AuthorizationException e) {
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/delAdmin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delAdmin(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			User user = new User();
			user.setId(id);
			user.setUuid(uid);
			user.setIsuse(1);
			userService.update(user);
			return returnMap(0, "", null);
		} catch (AuthorizationException e) {

		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUser() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		try {
			List<User> list = null;
			try {
				currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
				list = userService.findAllUsers();
				return returnMap(0, "", list);
			} catch (AuthorizationException e) {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				list = userService.findUserByAdminid(uid);
				return returnMap(0, "", list);
			}
		} catch (AuthorizationException e) {
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(User user, String editType) {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN) ;
			if (editType.equals("add")) {
				user.setLevel(0);
				user.setCuid(uid);
				userService.insert(user);
			} else if (editType.equals("edit")) {
			
				user.setUuid(uid);
				userService.update(user);
			}
			return returnMap(0, "", null);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				if (editType.equals("add")) {
					user.setLevel(0);
					user.setCuid(uid);
					userService.insert(user);
				} else if (editType.equals("edit")) {
				
					user.setUuid(uid);
					userService.update(user);
				}
				return returnMap(0, "", null);
				
			} catch (AuthorizationException e2) {
				e2.printStackTrace();
			}
		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delUser(String id) {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			User user = new User();
			user.setId(id);
			user.setUuid(uid);
			user.setIsuse(1);
			userService.update(user);
			return returnMap(0, "", null);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				User user = new User();
				user.setId(id);
				user.setUuid(uid);
				user.setIsuse(1);
				userService.update(user);
				return returnMap(0, "", null);
				
			} catch (AuthorizationException e2) {
				e2.printStackTrace();
			}

		}
		return returnMap(1, "", null);
	}

	@RequestMapping(value = "/getUserData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserData() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		String oid = userService.find(uid).getOid();
		return returnMap(0, "", userService.findOrganizationAllUser(oid));
	}
	
	@RequestMapping(value = "/getMyOrganizationUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMyOrganizationUser() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		User user = userService.find(uid);
		List<User> userList=userService.findOrganizationAllUser(user.getOid());
		return returnMap(0, "", userList);
	}
	/**
	 * 获取当前用户以及其组织名称
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getMyUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMyUser() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		User user = userService.selectWithOrgnization(uid);
		user.setOrgname(user.getOrgname().replaceAll(",", " "));
		return returnMap(0, "", user);
	}

	public List<Organization> getOrganizationList(String id) {
		List<Organization> list = null;
		Organization organization = new Organization();
		organization.setPid(id);
		list = organizationService.findByParameter(organization);
		if (list != null && list.size() > 0) {
			for (Organization t : list) {
				t.setOrganizationList(getOrganizationList(t.getId()));
			}
		}
		return list;
	}

	@RequestMapping(value = "/typeIndex", method = RequestMethod.GET)
	public ModelAndView typeIndex() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/typeIndex");
		return mv;
	}

	@RequestMapping(value = "/insertType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertType(OrganizationType type,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		type.setCuid(String.valueOf(currentRoot.getPrincipal()));
		String id = UuidUtil.get32UUID();
		type.setId(id);
		type.setCreateTime(new Date());
		try {
			organizationTypeService.insert(type);
			return returnMap(0, "", null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/selectType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectType(OrganizationType type,
			HttpServletRequest request) {
		List<OrganizationType> organizationTypes = new ArrayList<OrganizationType>();
		organizationTypes = organizationTypeService.select(type);
		return returnMap(0, "", organizationTypes);
	}

	@RequestMapping(value = "/delType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delType(String id) {
		try {
			organizationTypeService.delete(id);
			return returnMap(0, "", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getupdateType", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getupdateType(String id,
			HttpServletRequest request) {
		try {
			OrganizationType organizationtype = new OrganizationType();
			organizationtype = organizationTypeService.selectone(id);
			return returnMap(0, "", organizationtype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/updateType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateType(OrganizationType type,
			HttpServletRequest request) {
		try {
			Subject currentRoot = SecurityUtils.getSubject();
			type.setUuid(String.valueOf(currentRoot.getPrincipal()));
			type.setUpdataTime(new Date());
			organizationTypeService.update(type);
			return returnMap(0, "", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/noticeIndex", method = RequestMethod.GET)
	public String noticeIndex(OrganizationType type, HttpServletRequest request) {
		return "system/noticeIndex";
	}

	/**
	 * 发布公告
	 * 
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "/insertNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertNotice(
			Notice notice,
			@RequestParam(value = "upfile", required = false) MultipartFile[] files) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		notice.setCuid(uid);
		String[] oidList = notice.getDepartment().split(",");
		String id = noticeService.insert(notice);
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
				attachment.setType(3);
				attachment.setUrl(url);
				attachmentService.insert(attachment);
			}
		}
		for (String oid : oidList) {
			Task task = new Task();
			task.setDid(id);
			task.setOid(oid);
			task.setType(3);
			task.setSignStatus(1);
			taskService.insert(task);
		}
		List<String> list = userService.findUsernamebyOrganizations(notice
				.getDepartment());
		if (list.size() != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("action", "noticeSend");
			map.put("id", id);
			WebUtil.sendText(list.toArray(new String[list.size()]),
					JSON.toJSONString(map));
		} else {
			return returnMap(1, "", "无常用联系人");
		}
		return returnMap(0, "", null);
	}

	/**
	 * 获取公告列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeList() {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		List<Notice> notices = noticeService.getNoticeList(uid);
		return returnMap(0, "", notices);
	}

	/**
	 * 获取公告管理列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAdminNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAdminNoticeList(Notice notice) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		notice.setCuid(uid);
		List<Notice> notices = noticeService.findByParameter(notice);
		return returnMap(0, "", notices);
	}
	
	

	/**
	 * 获取单个公告
	 * 
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "/getNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNotice(String id) {
		Notice notice = noticeService.select(id);
		return returnMap(0, "", notice);
	}

	/**
	 * 删除公告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delNotice(String id) {
		noticeService.deleteNotice(id);
		taskService.deleteByPid(id);
		return returnMap(0, "", "删除成功");
	}

	@RequestMapping(value = "/getAttach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAttach(String pid) {

		Attachment attachment = new Attachment();
		attachment.setPid(pid);
		List<Attachment> attachmentList = attachmentService
				.findByParameter(attachment);
		return returnMap(0, null, attachmentList);
	}

	public List<Tree> getOrganizationTree(String id) {
		List<Organization> list = null;
		List<Tree> list2 = new ArrayList<Tree>();
		Organization organization = new Organization();
		organization.setPid(id);
		list = organizationService.findByParameter(organization);
		if (list != null && list.size() > 0) {
			for (Organization t : list) {
				Tree tree = new Tree(t);
				tree.setChildren(getOrganizationTree(t.getId()));
				list2.add(tree);
			}
		}
		return list2;
	}
	
	
	//组织管理中，第一次请求
	@RequestMapping(value = "/getTheOrganization", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getTheOrganization() {
		Subject currentUser = SecurityUtils.getSubject();
		List<Organization> list=null;
		try {
			currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
			Organization organization=new Organization();
			organization.setPid("0");
			list=organizationService.findByParameter(organization);
			return returnMap(0, "", list);
		} catch (AuthorizationException e) {
			try {
				currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				String id=(String)currentUser.getPrincipal();
				User user=userService.find(id);
				Organization organization=new Organization();
				organization.setId(user.getRemark());
				list=organizationService.findByParameter(organization);
				return returnMap(0, "", list);
			} catch (Exception e2) {
			}
		}
		return returnMap(1, "", null);
	}
	
	//组织管理中，来自节点的请求
		@RequestMapping(value = "/getTheNextOrganization", method = RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> getTheNextOrganization(String pid) {
			List<Organization> list=null;
			try {
				Organization organization=new Organization();
				organization.setPid(pid);
				list=organizationService.findByParameter(organization);
				return returnMap(0, "", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnMap(1, "", null);
		}
	
	//获取最高级别的单位
		@RequestMapping(value="/getFirstOrganize",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> getFirstOrganize(){
			List<Organization> list=null;
			try {
				list=organizationService.findFirstOrganize();
				return returnMap(1,"",list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnMap(1,"",null);
		}
		
		//获取他的下面的所有单位
		@RequestMapping(value="/getallChildOrganize",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> getallChildOrganize(String pid){
			List<Organization> list=null;
			try {
				list=organizationService.findallChildOrganize(pid);
				return returnMap(1,"",list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnMap(1,"",null);
		}
		
		//获取他上面一级部门
				@RequestMapping(value="/getupOrganize",method=RequestMethod.POST)
				@ResponseBody
				public Map<String,Object> getupOrganize(String id){
					Organization organization=new Organization();
					if(id.equals("0")){
						organization.setName("管理员单位");
						organization.setIsdef(0);
						return returnMap(1,"",organization);
					}else{
					try {
						organization=organizationService.find(id);
						return returnMap(1,"",organization);
					} catch (Exception e) {
						e.printStackTrace();
					}
					}
					return returnMap(1,"",null);
				}
				
		//获取该管理员所对应的单位
				@RequestMapping(value="/getSelfOrganize",method=RequestMethod.POST)
				@ResponseBody
				public Map<String,Object> getSelfOrganize(){
					Subject currentUser = SecurityUtils.getSubject();
					Organization organization=new Organization();
					try {
						currentUser.checkPermission(Const.PERMISSION_SUPERADMIN);
						String id=(String)currentUser.getPrincipal();
						User user=userService.find(id);
						organization=organizationService.find(user.getRemark());
						return returnMap(1,"",organization);
					} catch (AuthorizationException e) {
						try {
							currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
							String id=(String)currentUser.getPrincipal();
							User user=userService.find(id);
							organization=organizationService.find(user.getRemark());
							return returnMap(1,"",organization);
						} catch(AuthorizationException e2){
							e2.printStackTrace();
						}
						
						
					}
					return returnMap(1,"",null);
				}

}
