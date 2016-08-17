package cn.com.oa.controller;

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

import cn.com.oa.export.ExcelPassView;
import cn.com.oa.model.Attachment;
import cn.com.oa.model.MEntry;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Page;
import cn.com.oa.model.Task;
import cn.com.oa.model.User;
import cn.com.oa.service.AttachmentService;
import cn.com.oa.service.MEntryService;
import cn.com.oa.service.MeetService;
import cn.com.oa.service.TaskService;
import cn.com.oa.service.UserService;
import cn.com.oa.util.Const;
import cn.com.oa.util.FileUpload;
import cn.com.oa.util.UuidUtil;
import cn.com.oa.util.WebUtil;

@Controller
@RequestMapping("/meet")
public class MeetController extends BaseController {
	@Resource
	private MeetService meetService;
	@Resource
	private AttachmentService attachmentService;
	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private MEntryService mEntryService;

	@RequestMapping(value = "/meetInputIndex", method = RequestMethod.GET)
	public ModelAndView meetInputIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meet/meetInputIndex");
		return mav;
	}

	@RequestMapping(value = "/meetReceiveIndex", method = RequestMethod.GET)
	public ModelAndView docReceiveIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meet/meetReceiveIndex");
		return mav;
	}

	@RequestMapping(value = "/meetIndex", method = RequestMethod.GET)
	public ModelAndView docIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meet/meetIndex");
		return mav;
	}

	@RequestMapping(value = "/meetAuditIndex", method = RequestMethod.GET)
	public ModelAndView meetAuditIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meet/meetAuditIndex");
		return mav;
	}

	@RequestMapping(value = "/meetCompanyIndex", method = RequestMethod.GET)
	public ModelAndView meetCompanyIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("meet/meetCompanyIndex");
		return mav;
	}

	/**
	 * 会议承办
	 * 
	 * @param meet
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/meetCompany", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetCompany(Meet meet, Page page) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		meet.setOrderBy("passStatus desc,meetTime desc");
		meet.setMeetCompany(userService.find(uid).getOid());
		page = meetService.findByParameterToPage(meet, page);
		return returnMap(0, "", page);
	}

	/**
	 * 获取单个会议信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMeet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMeet(
			@RequestParam(value = "id", required = true) String id) {
		return returnMap(0, null, meetService.find(id));
	}

	/**
	 * 会议发送
	 * 
	 * @param meet
	 * @param files
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meetSend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetSend(
			Meet meet,
			@RequestParam(value = "upfile", required = false) MultipartFile[] files,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		meet.setCuid(String.valueOf(currentRoot.getPrincipal()));
		meet.setSignNum(meet.getDepartment().split(",").length);
		String id = meetService.insert(meet);
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
				attachment.setType(2);
				attachment.setUrl(url);
				attachmentService.insert(attachment);
			}
		}
		String[] oidList = meet.getDepartment().split(",");
		for (String oid : oidList) {
			Task task = new Task();
			task.setDid(id);
			task.setOid(oid);
			task.setType(2);
			task.setSignStatus(1);
			taskService.insert(task);
		}
		List<String> list = userService.findUsernamebyOrganizations(meet
				.getDepartment());
		if (list.size() != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("action", "meetSend");
			map.put("id", id);
			WebUtil.sendText(list.toArray(new String[list.size()]),
					JSON.toJSONString(map));
		} else {
			return returnMap(1, "", "无常用联系人");
		}
		return returnMap(0, null, "发送成功");
	}

	/**
	 * 会议接收
	 * 
	 * @param meet
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meetReceive", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docReceive(Meet meet, Page page,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		page.setUserids(uid);
		page = meetService.findByTaskToPage(meet, page);
		return returnMap(0, "", page);
	}

	/**
	 * 会议签收
	 * 
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/meetSign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetSign(String tid) {

		if (taskService.find(tid).getSignStatus() == 1) {
			Subject currentRoot = SecurityUtils.getSubject();
			String uid = (String) currentRoot.getPrincipal();
			Task task = new Task();
			task.setId(tid);
			task.setSignStatus(0);
			task.setUid(uid);
			taskService.update(task);
			if (meetService.isSign(tid)) {
				Task task2 = taskService.find(tid);
				Meet meet = new Meet();
				meet.setId(task2.getDid());
				meet.setSignStatus(0);
				meetService.update(meet);
			}
			return returnMap(0, "", null);
		} else {
			return returnMap(1, "已签收", null);
		}
	}

	/**
	 * 会议审计
	 * 
	 * @param meet
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meetSelect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetSelect(Meet meet, Page page,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		Page pageObj = new Page();
		meet.setOrderBy("createTime desc");
		try {
			currentRoot.checkPermission(Const.PERMISSION_SUPERADMIN);
			pageObj = meetService.findByParameterToPage(meet, page);
			return returnMap(0, "", pageObj);
		} catch (AuthorizationException e) {
			try {
				currentRoot.checkPermission(Const.PERMISSION_COMMONLYADMIN);
				String id = (String) currentRoot.getPrincipal();
				User user = userService.find(id);
				List<String> userids = userService.selectuserid(user);
				String uids = "'" + id + "',";
				for (int i = 0; i < userids.size(); i++) {
					uids += "'" + userids.get(i) + "'" + ",";
				}
				uids = uids.substring(0, uids.length() - 1);
				page.setUserids(uids);
				pageObj = meetService.findByParameterToPage(meet, page);
				return returnMap(0, "", pageObj);

			} catch (AuthorizationException e2) {

			}
		}
		return returnMap(0, "", null);
	}

	@RequestMapping(value = "/meetdelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetdelete(Meet meet, Page page,
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
		meetService.delete(id);
		taskService.deletemt(id);
		return meetSelect(meet, page, request);
	}

	/**
	 * 已发布会议
	 * 
	 * @param doc
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meetUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUser(Meet doc, Page page,
			HttpServletRequest request) {
		Subject currentRoot = SecurityUtils.getSubject();
		String uid = (String) currentRoot.getPrincipal();
		doc.setOrderBy("signStatus desc,createTime desc");
		page.setUserids(uid);
		page = meetService.findByUserToPage(doc, page);
		return returnMap(0, "", page);
	}

	/**
	 * 会议转发
	 * 
	 * @param id
	 * @param personnel
	 * @return
	 */
	@RequestMapping(value = "/meetRelay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetRelay(String id, String personnel) {
		Subject currentRoot = SecurityUtils.getSubject();
		String did = UuidUtil.get32UUID();
		String[] oidList = personnel.split(",");
		Meet meet = new Meet();
		meet.setPersonnel(personnel);
		meet.setCuid(String.valueOf(currentRoot.getPrincipal()));
		meet.setSignNum(oidList.length);
		meet.setCuid(String.valueOf(currentRoot.getPrincipal()));
		meet.setTid(id);
		meet.setId(did);
		meetService.copyInsert(meet);
		for (String oid : oidList) {
			Task task = new Task();
			task.setDid(did);
			task.setUid(oid);
			task.setType(2);
			task.setSignStatus(1);
			taskService.insert(task);
		}
		return returnMap(0, "", "已转发");
	}

	/**
	 * 会议转发审批
	 * 
	 * @param id
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/meetRelayPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docRelayPass(String id, String remark) {
		Meet meet = new Meet();
		meet.setId(id);
		meet.setRemark(remark);
		meetService.update(meet);
		return returnMap(0, "", "已审批");
	}

	/**
	 * 会议承办通过人员报名
	 * 
	 * @param id
	 * @param pass
	 * @param passRemark
	 * @return
	 */
	@RequestMapping(value = "/meetUserPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUserPass(String id, Boolean pass,
			String passRemark) {
		MEntry mEntry = new MEntry();
		mEntry.setId(id);
		if (pass) {
			mEntry.setPassStatus(0);
			mEntryService.update(mEntry);
		} else {
			mEntry.setPassStatus(1);
			mEntry.setPassRemark(passRemark);
			MEntry mEntry2 = mEntryService.find(id);
			Task task = new Task();
			Task task2 = new Task();
			task2 = taskService.find(mEntry2.getPid());
			task.setId(mEntry2.getPid());
			task.setPassStatus(1);
			taskService.update(task);
			mEntryService.update(mEntry);
			try {
				User user = new User();
				user = userService.find(task2.getUid());
				List<String> list = new ArrayList<String>();
				list.add(user.getAccount());
				if (list.size() != 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("action", "meetUserPass");
					map.put("id", id);
					WebUtil.sendText(list.toArray(new String[list.size()]),
							JSON.toJSONString(map));
					return returnMap(0, "", "已催收");
				} else {
					return returnMap(1, "", "无常用联系人");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnMap(0, null, null);
	}

	/**
	 * 报名关闭
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/meetPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetPass(String id) {
		Meet meet = new Meet();
		meet.setId(id);
		meet.setPassStatus(0);
		meetService.update(meet);
		taskService.updatePassStatusByPid(id, 0);
		return returnMap(0, null, null);
	}

	/**
	 * 报名重开
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/meetUnpass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUnPass(String id) {
		Meet meet = new Meet();
		meet.setId(id);
		meet.setPassStatus(2);
		meetService.update(meet);
		taskService.updatePassStatusByPid(id, 2);
		return returnMap(0, null, null);
	}

	@RequestMapping(value = "/getAttach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAttach(String pid) {

		Attachment attachment = new Attachment();
		attachment.setPid(pid);
		attachment.setType(2);
		List<Attachment> attachmentList = attachmentService
				.findByParameter(attachment);
		return returnMap(0, null, attachmentList);
	}

	/**
	 * 获取接收单位会议的报名表
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/getMEntryByPid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMEntryByPid(String pid) {
		MEntry mEntry = new MEntry();
		mEntry.setPid(pid);
		return returnMap(0, null, mEntryService.findByParameter(mEntry));
	}

	/**
	 * 获取根据id单个报名人员信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMEntry(String id) {
		return returnMap(0, null, mEntryService.find(id));
	}

	/**
	 * 删除报名人员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delMEntry(String id) {
		try {
			mEntryService.delete(id);
			return returnMap(0, null, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return returnMap(1, null, "删除失败");
		}
	}

	/**
	 * 修改报名人员
	 * 
	 * @param mEntry
	 * @param editType
	 * @return
	 */
	@RequestMapping(value = "/updateMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateMEntry(MEntry mEntry, String editType) {
		try {
			if (editType.equals("add")) {
				mEntryService.insert(mEntry);
				return returnMap(0, null, null);
			} else if (editType.equals("edit")) {
				mEntry.setPassStatus(3);
				mEntryService.update(mEntry);
				return returnMap(0, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap(1, null, null);
	}

	/**
	 * 承办单位获取会议报名表
	 * 
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/getMEntryByPassStatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMEntryByPassStatus(String mid) {
		MEntry mEntry = new MEntry();
		mEntry.setId(mid);
		return returnMap(0, null, mEntryService.findByMeetid(mEntry));
	}

	/**
	 * 接收单位报名表提交审批
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/updateTaskPassStatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateTaskPassStatus(String id, Integer status) {
		try {
			if (taskService.find(id).getPassStatus() == 0) {
				return returnMap(1, null, null);
			}
			Task task = new Task();
			task.setId(id);
			task.setPassStatus(status);
			taskService.update(task);
			return returnMap(0, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap(1, null, null);
	}

	@RequestMapping(value = "/passExcel")
	public ModelAndView passExcel(String mid) {
		ModelAndView view = new ModelAndView();
		Meet meet = meetService.find(mid);
		MEntry mEntry = new MEntry();
		mEntry.setId(mid);
		mEntry.setOrderBy("type,passStatus");
		List<MEntry> MElist = mEntryService.findByMeetid(mEntry);
		List<List<List<String>>> list = new ArrayList<List<List<String>>>();
		List<List<String>> list1 = new ArrayList<List<String>>();
		List<List<String>> list2 = new ArrayList<List<String>>();
		List<List<String>> list3 = new ArrayList<List<String>>();
		Integer i = 1;
		for (MEntry entry : MElist) {
			List<String> stringlist = new ArrayList<String>();
			stringlist.add(String.valueOf(i));
			stringlist.add(entry.getOrganame());
			stringlist.add(entry.getName());
			stringlist.add((entry.getSex() == 0 ? "男" : "女"));
			stringlist.add(entry.getPost());
			stringlist.add(entry.getPhone());
			stringlist.add(entry.getRemark());
			stringlist.add((entry.getPassStatus() == 0 ? "已审批" : entry
					.getPassStatus() == 1 ? "审批未通过"
					: entry.getPassStatus() == 3 ? "未审批" : ""));
			if (entry.getType() == 0) {
				list1.add(stringlist);
			} else if (entry.getType() == 1) {
				list2.add(stringlist);
			} else if (entry.getType() == 2) {
				list3.add(stringlist);
			}
			i++;
		}
		list.add(list1);
		list.add(list2);
		list.add(list3);
		view.setView(new ExcelPassView("会议报名表", meet.getName(), list));
		return view;
	}

	/**
	 * 会议催收
	 * 
	 * @param oid
	 * @param did
	 * @return
	 */
	@RequestMapping(value = "/meetUrge", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUrge(String oid, String mid) {
		User user = new User();
		user.setOid(oid);
		user.setIsmain(0);
		List<User> userList = userService.findByParameter(user);
		List<String> list = new ArrayList<String>();
		for (User user1 : userList) {
			list.add(user1.getAccount());
		}
		if (list.size() != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("action", "meetUrge");
			map.put("id", mid);
			WebUtil.sendText(list.toArray(new String[list.size()]),
					JSON.toJSONString(map));
			return returnMap(0, "", "已催收");
		} else {
			return returnMap(1, "", "无常用联系人");
		}

	}

}
