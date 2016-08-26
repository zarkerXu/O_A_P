package cn.com.oa.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.com.oa.model.Doc;
import cn.com.oa.model.DocMeet;
import cn.com.oa.model.MEntry;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Notice;
import cn.com.oa.model.Organ;
import cn.com.oa.model.Organization;
import cn.com.oa.model.Page;
import cn.com.oa.model.Task;
import cn.com.oa.model.Tree;
import cn.com.oa.model.User;
import cn.com.oa.service.AttachmentService;
import cn.com.oa.service.DocService;
import cn.com.oa.service.MEntryService;
import cn.com.oa.service.MeetService;
import cn.com.oa.service.NoticeService;
import cn.com.oa.service.OrganService;
import cn.com.oa.service.OrganizationService;
import cn.com.oa.service.TaskService;
import cn.com.oa.service.UserService;
import cn.com.oa.util.Const;
import cn.com.oa.util.FileUpload;
import cn.com.oa.util.PasswordUtil;
import cn.com.oa.util.UuidUtil;
import cn.com.oa.util.WebUtil;

@Controller
@RequestMapping("/phone")
public class PhoneController extends BaseController {
	@Resource
	AttachmentService attachmentService;
	@Resource
	private UserService userService;
	@Resource
	private DocService docService;
	@Resource
	private TaskService taskService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private MeetService meetService;
	@Resource
	private MEntryService mEntryService;
	@Resource
	private OrganService organservice;
	@Resource
	private NoticeService noticeService;

	/**
	 * 公文发送
	 * 
	 * @param value
	 * @param doc
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/docSend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docSend(
			@RequestParam(value = "value", required = true) String value,
			Doc doc,
			HttpServletRequest request,
			@RequestParam(value = "upfile", required = false) MultipartFile[] files) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			doc.setCuid(user.getId());
			doc.setSignNum(doc.getDepartment().split(",").length);
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
					attachment
							.setFileSize(String.valueOf(file.getSize() / 1024));
					attachment.setPid(id);
					attachment.setSuffix(extName);
					attachment.setType(1);
					attachment.setUrl(url);
					attachmentService.insert(attachment);
				}
			}
			String[] oidList = doc.getDepartment().split(",");
			for (String oid : oidList) {
				Task task = new Task();
				task.setDid(id);
				task.setOid(oid);
				task.setType(1);
				task.setSignStatus(1);
				taskService.insert(task);
			}
			List<String> list = userService.findUsernamebyOrganizations(doc
					.getDepartment());
			if (list.size() != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("action", "docSend");
				map.put("id", id);
				WebUtil.sendText(list.toArray(new String[list.size()]),
						JSON.toJSONString(map));
			} else {
				return returnMap(1, "", "无常用联系人");
			}
			return returnMap(0, "发送成功", null);
		} else
			return returnMap(1, "验证失败", null);
	}

	/**
	 * 已发送公文
	 * 
	 * @param value
	 * @param doc
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/docUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docUser(
			@RequestParam(value = "value", required = true) String value,
			Doc doc, Page page, HttpServletRequest request) {
		try {
			String username = simpleDecrypt(value);
			User user = userService.findByAccount(username);
			if (user != null) {
				page.setUserids(user.getId());
				doc.setOrderBy("d.createTime desc");
				page = docService.findByUserToPage(doc, page);
				return returnMap(0, "", page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap(1, "", null);
	}

	/**
	 * 公文接收
	 * 
	 * @param value
	 * @param doc
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/docReceive", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docReceive(
			@RequestParam(value = "value", required = true) String value,
			Doc doc, Page page) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			page.setUserids(user.getId());
			page = docService.findByTaskToPage(doc, page);
			return returnMap(0, null, page);
		} else
			return returnMap(1, "验证失败", null);
	}

	/**
	 * 公文签收
	 * 
	 * @param value
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/docSign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docSign(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "tid", required = true) String tid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			if (taskService.find(tid).getSignStatus() == 1) {
				Task task = new Task();
				task.setId(tid);
				task.setSignStatus(0);
				task.setUid(user.getId());
				taskService.update(task);
				if (docService.isSign(tid)) {
					Task task2 = taskService.find(tid);
					Doc doc = new Doc();
					doc.setId(task2.getDid());
					doc.setSignStatus(0);
					docService.update(doc);
				}
				return returnMap(0, "", null);
			} else {
				return returnMap(1, "已签收", null);
			}
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 公文催收
	 * 
	 * @param value
	 * @param oid
	 * @param did
	 * @return
	 */
	@RequestMapping(value = "/docUrge", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docUrge(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "oid", required = true) String oid,
			@RequestParam(value = "did", required = true) String did) {
		String username = simpleDecrypt(value);
		User user2 = userService.findByAccount(username);
		if (user2 != null) {
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
				map.put("action", "docUrge");
				map.put("id", did);
				WebUtil.sendText(list.toArray(new String[list.size()]),
						JSON.toJSONString(map));
				return returnMap(0, "已催收", "");
			} else {
				return returnMap(1, "无常用联系人", "");
			}
		}
		return returnMap(1, "erro", null);

	}

	@RequestMapping(value = "/getOrganizationData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganizationData(
			@RequestParam(value = "value", required = true) String value,
			HttpServletRequest request) {
		 String username = simpleDecrypt(value);
		List<Organization> list = new ArrayList<Organization>();
		User user = userService.findByAccount(username);
		if (user != null) {
			Organization userOrganization = organizationService.find(user
					.getOid());
			if (userOrganization.getPid().equals("0")) {
				Organization organization = new Organization();
				organization.setPid("0");
				list = organizationService.findByParameter(organization);
				for (Organization organization2 : list) {
					if (organization2.getId().equals(user.getOid())) {
						organization2
								.setOrganizationList(getOrganizationList(user
										.getOid()));
					}
				}
				List<Organization> list2 = new ArrayList<Organization>();
				for (Organization temp : list) {
					DFS(temp, list2);
				}

				for(Organization temp : list2){
					temp.setOrganizationList(null);
				}
				return returnMap(0, "", list2);
			} else {
				List<Organization> organizations = organizationService
						.findParentById(user.getOid());
				Organization organization = organizations.get(0);
				organization
						.setOrganizationList(getOrganizationList(organizations
								.get(0).getId()));
				list.add(organization);

				List<Organization> list2 = new ArrayList<Organization>();
				for (Organization temp : list) {
					DFS(temp, list2);
				}
				for(Organization temp : list2){
					temp.setOrganizationList(null);
				}
				return returnMap(0, "", list2);
			}
		}

		return returnMap(1, "", null);
	}
	//递归处理
	private void DFS(Organization temp, List<Organization> mDepartments) {
		mDepartments.add(temp);
		if (temp.getOrganizationList() != null
				&& temp.getOrganizationList().size() >= 0) {
			for (Organization account : temp.getOrganizationList()) {
				account.setName(temp.getName() + "/" + account.getName());
				DFS(account, mDepartments);
			}
		}
	}

	@RequestMapping(value = "/getCheckInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCheckInfo(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "pid", required = true) String pid) {
		try {
			String username = simpleDecrypt(value);
			User user = userService.findByAccount(username);
			if (user != null) {
				List<Task> tasks = new ArrayList<Task>();
				tasks = taskService.findCheckInfo(pid);
				return returnMap(0, null, tasks);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap(1, "", null);
	}

	/**
	 * 获取附件列表
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/getAttach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAttach(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "pid", required = true) String pid) {

		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Attachment attachment = new Attachment();
			attachment.setPid(pid);
			List<Attachment> attachmentList = attachmentService
					.findByParameter(attachment);
			return returnMap(0, null, attachmentList);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 下载
	 * 
	 * @param value
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Attachment attachment = attachmentService.find(id);
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String downLoadPath = attachment.getUrl();
			System.out.println(downLoadPath);
			try {
				long fileLength = new File(downLoadPath).length();
				response.setContentType("application/vnd.ms-word;");
				response.setHeader(
						"Content-disposition",
						"attachment; filename="
								+ new String(attachment.getFileName().getBytes(
										"utf-8"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		}
	}

	@RequestMapping(value = "/meetSend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetSend(
			String value,
			Meet meet,
			@RequestParam(value = "upfile", required = false) MultipartFile[] files,
			HttpServletRequest request) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			meet.setCuid(user.getId());
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
					attachment
							.setFileSize(String.valueOf(file.getSize() / 1024));
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
			return returnMap(0, "发送成功", null);
		} else
			return returnMap(1, "验证失败", null);
	}

	/**
	 * 会议签收
	 * 
	 * @param value
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/meetSign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetSign(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "tid", required = true) String tid) {

		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			if (taskService.find(tid).getSignStatus() == 1) {
				Task task = new Task();
				task.setId(tid);
				task.setSignStatus(0);
				task.setUid(user.getId());
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
		return returnMap(1, "erro", null);
	}

	/**
	 * 已发布会议
	 * 
	 * @param value
	 * @param doc
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meetUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUser(
			@RequestParam(value = "value", required = true) String value,
			Meet doc, Page page, HttpServletRequest request) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			page.setUserids(user.getId());
			doc.setOrderBy("d.createTime desc");
			page = meetService.findByUserToPage(doc, page);
			return returnMap(0, "", page);
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 会议接收
	 * 
	 * @param value
	 * @param meet
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meetReceive", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docReceive(
			@RequestParam(value = "value", required = true) String value,
			Meet meet, Page page, HttpServletRequest request) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			page.setUserids(user.getId());
			page = meetService.findByTaskToPage(meet, page);
			return returnMap(0, "", page);
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 会议催收
	 * 
	 * @param value
	 * @param oid
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/meetUrge", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUrge(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "oid", required = true) String oid,
			@RequestParam(value = "mid", required = true) String mid) {
		String username = simpleDecrypt(value);
		User user2 = userService.findByAccount(username);
		if (user2 != null) {
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
				return returnMap(0, "已催收", null);
			} else {
				return returnMap(1, "无常用联系人", null);
			}
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 会议承办
	 * 
	 * @param value
	 * @param meet
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/meetCompany", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetCompany(
			@RequestParam(value = "value", required = true) String value,
			Meet meet, Page page) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			meet.setMeetCompany(userService.find(user.getId()).getOid());
			meet.setOrderBy("d.createTime desc");
			page = meetService.findByParameterToPage(meet, page);
			return returnMap(0, "", page);
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 获取接收单位会议的报名表
	 * 
	 * @param value
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/getMEntryByPid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMEntryByPid(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "pid", required = true) String pid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			MEntry mEntry = new MEntry();
			mEntry.setPid(pid);
			return returnMap(0, null, mEntryService.findByParameter(mEntry));
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 获取根据id单个报名人员信息
	 * 
	 * @param value
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMEntry(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			return returnMap(0, null, mEntryService.find(id));
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 修改报名人员
	 * 
	 * @param value
	 * @param mEntry
	 * @param editType
	 * @return
	 */
	@RequestMapping(value = "/updateMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateMEntry(
			@RequestParam(value = "value", required = true) String value,
			MEntry mEntry,
			@RequestParam(value = "editType", required = true) String editType) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
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
		}
		return returnMap(1, null, null);
	}

	/**
	 * 批量添加报名人员
	 * 
	 * @param value
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insertListMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertListMEntry(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "json", required = true) String json) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			List<MEntry> list = JSON.parseArray(json, MEntry.class);
			mEntryService.insertList(list);
			return returnMap(0, null, null);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 接收单位报名表提交审批
	 * 
	 * @param value
	 * @param id
	 * @return
	 */ 
	@RequestMapping(value = "/updateTaskPassStatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateTaskPassStatus(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "tid", required = true) String tid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			try {
				if (taskService.find(tid).getPassStatus() == 0) {
					return returnMap(1, "报名已经截止", null);
				}
				Task task = new Task();
				task.setId(tid);
				task.setPassStatus(2);
				taskService.update(task);
				return returnMap(0, "成功", null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnMap(1, null, null);
	}

	/**
	 * 删除报名人员
	 * 
	 * @param value
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delMEntry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delMEntry(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String ids) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			try {
				mEntryService.delete(ids);
				return returnMap(0, "删除成功", null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnMap(1, "删除失败", null);
	}

	/**
	 * 会议承办通过人员报名
	 * 
	 * @param value
	 * @param id
	 * @param pass
	 * @param passRemark
	 * @return
	 */
	@RequestMapping(value = "/meetUserPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUserPass(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "pass", required = true) Boolean pass,
			@RequestParam(value = "passRemark", required = true) String passRemark) {
		String username = simpleDecrypt(value);
		User user1 = userService.findByAccount(username);
		if (user1 != null) {
			MEntry mEntry = new MEntry();
			mEntry.setId(id);
			boolean flag = false;
			if (pass) {
				mEntry.setPassStatus(0);
				mEntryService.update(mEntry);
				flag = true;
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
						map.put("id", task2.getDid());
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
			if (flag) {
				return returnMap(0, "", "成功通过");
			} else {
				return returnMap(1, "", "未通过");
			}

		}
		return returnMap(1, null, null);
	}

	/**
	 * 承办单位获取会议报名表
	 * 
	 * @param value
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/getMEntryByPassStatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMEntryByPassStatus(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "mid", required = true) String mid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			MEntry mEntry = new MEntry();
			mEntry.setId(mid);
			mEntry.setOrderBy("m.type, m.passStatus desc,p.sort,o.id,o.level");
			return returnMap(0, null, mEntryService.findByMeetid(mEntry));
		}
		return returnMap(1, null, null);
	}

	/**
	 * 会议审批通过
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/meetPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetPass(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "mid", required = true) String mid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Meet meet = new Meet();
			meet.setId(mid);
			meet.setPassStatus(0);
			meetService.update(meet);
			taskService.updatePassStatusByPid(mid, 0);
			return returnMap(0, null, null);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取当前用户公文会议未签收数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getIndexNum", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIndexNum(
			@RequestParam(value = "value", required = true) String value) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("docNum", taskService.getIndexNum(user.getId(), 1));
			map.put("meetNum", taskService.getIndexNum(user.getId(), 2));
			map.put("moticeNum", taskService.getIndexNum(user.getId(), 3));
			return returnMap(0, null, map);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 报名重开
	 * 
	 * @param value
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/meetUnpass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> meetUnPass(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String mid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Meet meet = new Meet();
			meet.setId(mid);
			meet.setPassStatus(2);
			meetService.update(meet);
			taskService.updatePassStatusByPid(mid, 2);
			return returnMap(0, null, null);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取常用单位组
	 * 
	 * @param value
	 * @param select
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMagroupAll", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMagroupAll(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "select", required = true) String select,
			@RequestParam(value = "id", required = true) String id) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			if (select.equals("all")) {
				List<Organ> organs = new ArrayList<Organ>();
				Organ organ = new Organ();
				organ.setType(0);
				organ.setUid(user.getId());
				organs = organservice.select(organ);
				return returnMap(0, "", organs);
			} else if (select.equals("one")) {
				Organ organ = new Organ();
				organ = organservice.selectone(id);
				return returnMap(0, "", organ);
			}
			return returnMap(0, "", null);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 添加常用单位组
	 * 
	 * @param value
	 * @param ids
	 * @param name
	 * @param sort
	 * @return
	 */
	@RequestMapping(value = "/insertMagroup", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMagroup(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "sort", required = true) Integer sort) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Organ organ = new Organ();
			organ.setValue(ids);
			organ.setSort(sort);
			organ.setName(name);
			organ.setType(0);
			organ.setUid(user.getId());
			organservice.insert(organ);
			return returnMap(0, null, null);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 修改常用单位组
	 * 
	 * @param id
	 * @param ids
	 * @param name
	 * @param sort
	 * @return
	 */
	@RequestMapping(value = "/updateMagroup", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "sort", required = true) Integer sort) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Organ organ = new Organ();
			organ.setId(id);
			organ.setValue(ids);
			organ.setSort(sort);
			organ.setName(name);
			organservice.update(organ);
			return returnMap(0, "", null);
		}
		return returnMap(1, null, null);
	}

	@RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateNotice(
			@RequestParam(value = "value", required = true) String value,
			String id) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Notice notice = noticeService.select(id);
			return returnMap(0, "", notice);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取公告列表
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeList(
			@RequestParam(value = "value", required = true) String value) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			List<Notice> notices = new ArrayList<Notice>();
			// if(user.getLevel()==2){
			// notices=noticeService.getOwnNoticeList(user.getRemark());
			// }else{
			notices = noticeService.getOwnNoticeList(user.getOid());
			// }
			return returnMap(0, "", notices);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取公告
	 * 
	 * @param value
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNotice(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			Notice notice = noticeService.selectone(id);
			return returnMap(0, "", notice);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取当前用户以及其组织名称
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/getMyUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMyUser(
			@RequestParam(value = "value", required = true) String value) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			User user2 = userService.selectWithOrgnization(user.getId());
			user.setOrgname(user2.getOrgname());
			return returnMap(0, "", user2);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 转发
	 * 
	 * @param id
	 *            (公文或者会议的id)
	 * @param organid
	 * @param type
	 *            (0:doc;1:meet)
	 * @return
	 */

	@RequestMapping(value = "/Relay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Relay(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "organid", required = true) String organid,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "relayRemark", required = true) String relayRemark) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			String[] oidList = organid.split(",");
			String uids = "";
			// 转发公文
			String did = UuidUtil.get32UUID();
			// 该机构下面的所有人员
			try {
				for (String oid : oidList) {
					String uidLists = userService.finduidByOid(oid);
					if (uidLists == null) {
						continue;
					}
					String[] uidList = uidLists.split(",");
					for (String uid : uidList) {
						Task task = new Task();
						task.setDid(did);
						task.setOid(oid);
						task.setUid(uid);
						uids += uid + ",";
						task.setType(Integer.parseInt(type));
						task.setSignStatus(1);
						task.setRemark("1");
						taskService.insert(task);
					}
				}
				String[] nums = uids.split(",");
				if (type.equals("1")) {
					Doc docone = new Doc();
					docone = docService.find(id);
					System.out.println("123" + id);
					Doc doc = new Doc();
					doc.setId(did);
					doc.setPersonnel(uids.substring(0, uids.length() - 1));
					doc.setCuid(user.getId());
					doc.setSignNum(nums.length);
					doc.setRelayRemark(relayRemark);
					doc.setTid(id);
					doc.setRemark("0");
					if (docone.getOldid() == null) {
						doc.setOldid(id);
					} else {
						doc.setOldid(docone.getOldid());
					}
					docService.copyInsert(doc);
				} else if (type.equals("2")) {
					Meet meetone = new Meet();
					meetone = meetService.find(id);
					Meet meet = new Meet();
					meet.setId(did);
					meet.setPersonnel(uids.substring(0, uids.length() - 1));
					meet.setCuid(user.getId());
					meet.setSignNum(nums.length);
					meet.setRelayRemark(relayRemark);
					meet.setTid(id);
					meet.setRemark("0");
					if (meetone.getOldid() == null) {
						meet.setOldid(id);
					} else {
						meet.setOldid(meetone.getOldid());
					}
					meetService.copyInsert(meet);
				}
				return returnMap(0, "已转发", "");

			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnMap(1, "转发未成功！", "");
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取我的转发公文和会议
	 */
	@RequestMapping(value = "/findRelay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findRelay(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "keywords", required = true) String keywords,
			DocMeet docmeet, Page page) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			docmeet.setCuid(user.getId());
			page = meetService.mfindByRelayToPage(docmeet, page);
			return returnMap(0, "成功", page);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 选择我的公文审批和会议审批
	 * 
	 * @param doc
	 * @param page
	 * @return
	 */

	@RequestMapping(value = "/findApproveRelay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> mfindApproveRelay(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "keywords", required = true) String keywords,
			DocMeet docmeet, Page page) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			docmeet.setCuid(user.getId());
			page = meetService.mfindByApproveToPage(docmeet, page);
			return returnMap(0, "成功", page);
		}
		return returnMap(1, null, null);
	}

	/**
	 * 公文和会议的审批
	 */
	@RequestMapping(value = "/RelayPass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docRelayPass(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "tid", required = true) String tid,
			@RequestParam(value = "passRemark", required = true) String passRemark,
			@RequestParam(value = "signnum", required = true) String signnum) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			try {
				Task task = new Task();
				task.setId(tid);
				task.setRemark("0");
				task.setPassRemark(passRemark);
				task.setSignStatus(0);
				taskService.update(task);
				task = taskService.find(tid);
				String did = task.getDid();
				if (task.getType() == 1) {
					// 公文
					int i = taskService.getDocRelayNum(did);
					if (i == Integer.parseInt(signnum)) {
						System.out.println("11111" + did);
						Doc doc = new Doc();
						doc.setSignStatus(0);
						doc.setId(did);
						docService.update(doc);
					}
				} else if (task.getType() == 2) {
					// 会议
					int i = taskService.getMeetRelayNum(did);
					if (i == Integer.parseInt(signnum)) {
						System.out.println("22222" + did);
						Meet meet = new Meet();
						meet.setSignStatus(0);
						meet.setId(did);
						meetService.update(meet);
					}
				}
				return returnMap(0, "已审批", "");

			} catch (Exception e) {
				return returnMap(1, "审批未成功", "");
			}
		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取自己审批的意见
	 */
	@RequestMapping(value = "/ApproveGet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> docApproveGet(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "tid", required = true) String tid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			try {
				Task task = new Task();
				task = taskService.find(tid);
				return returnMap(0, "获取成功", task);

			} catch (Exception e) {
				return returnMap(1, "获取失败", null);
			}

		}
		return returnMap(1, null, null);
	}

	/**
	 * 获取审批的详情
	 * 
	 * @param did
	 * @param type
	 * @return
	 */

	@RequestMapping(value = "/findapproved", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findapproved(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "type", required = true) String type) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			try {
				Task task = new Task();
				task.setDid(id);
				task.setType(Integer.parseInt(type));
				List<Task> tasks = taskService.findapproved(task);
				return returnMap(0, "获取成功", tasks);

			} catch (Exception e) {
				return returnMap(1, "获取失败", null);
			}

		}
		return returnMap(1, null, null);
	}

	/**
	 * 转发组织树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOrganizationTreeData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganizationTreeData(
			@RequestParam(value = "value", required = true) String value) {
		String username = simpleDecrypt(value);
		User userone = userService.findByAccount(username);
		if (userone != null) {
			try {
				User user = userService.find(userone.getId());
				List<Tree> treeList = new ArrayList<Tree>();
				List<Organization> organizations = organizationService
						.findParentById(user.getOid());
				Tree tree = new Tree(organizations.get(0));
				tree.setChildren(getOrganizationTree(organizations.get(0)
						.getId()));
				treeList.add(tree);
				
				List<Tree> list2 = new ArrayList<Tree>();
				for (Tree temp : treeList) {
					DFStwo(temp, list2);
				}

				for(Tree temp : list2){
					temp.setChildren(null);
				}
			
				
				return returnMap(0, "获取成功", list2);

			} catch (Exception e) {
				return returnMap(1, "获取失败", null);
			}
			

		}
		return returnMap(1, null, null);
	}
	
	private void DFStwo(Tree temp,List<Tree> mList) {
        mList.add(temp);
        if (temp.getChildren() != null && temp.getChildren().size() >= 0) {
            for (Tree relay : temp.getChildren()) {
                relay.setName(temp.getName() + "/" + relay.getName());
                DFStwo(relay,mList);
            }
        }
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

	/**
	 * 手机端导出Excel文件
	 * 
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/passExcel")
	public ModelAndView passExcel(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "mid", required = true) String mid) {
		String username = simpleDecrypt(value);
		User userone = userService.findByAccount(username);
		if (userone != null) {
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
		return null;
	}

	/**
	 * app登陆后获取通告中的内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexFirstGet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> indexFirstGet(
			@RequestParam(value = "value", required = true) String value) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			try {
				List<DocMeet> docmeets = new ArrayList<DocMeet>();
				docmeets = meetService.getindexFirstGet(user.getOid());
				return returnMap(0, "获取成功", docmeets);

			} catch (Exception e) {
				e.printStackTrace();
				return returnMap(1, "获取失败", null);
			}

		}
		return returnMap(1, null, null);

	}

	/**
	 * app登陆后获取通告中的内容test
	 * 
	 * @return
	 */
	@RequestMapping(value = "/monitest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> monitest(
			@RequestParam(value = "value", required = true) String value) {

		User user = userService.findByAccount(value);
		if (user != null) {
			try {
				List<DocMeet> docmeets = new ArrayList<DocMeet>();
				docmeets = meetService.getindexFirstGet(user.getOid());
				return returnMap(0, "获取成功", docmeets);

			} catch (Exception e) {
				e.printStackTrace();
				return returnMap(1, "获取失败", null);
			}

		}
		return returnMap(1, null, null);

	}

	/**
	 * 公告签收
	 * 
	 * @param value
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/noticeSign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> noticeSign(
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "tid", required = true) String tid) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			if (taskService.find(tid).getSignStatus() == 1) {
				Task task = new Task();
				task.setId(tid);
				task.setSignStatus(0);
				task.setUid(user.getId());
				taskService.update(task);
				return returnMap(0, "签收成功", null);
			} else {
				return returnMap(1, "已签收", null);
			}
		}
		return returnMap(1, "erro", null);
	}

	/**
	 * 首页获取报名未通过的信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexNoPassMeet", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> indexNoPassMeet(
			@RequestParam(value = "value", required = true) String value) {
		String username = simpleDecrypt(value);
		User user = userService.findByAccount(username);
		if (user != null) {
			List<String> info = new ArrayList<String>();
			info = taskService.getindexNoPassMeet(user.getOid());
			return returnMap(0, "获取成功", info);
		}
		return returnMap(1, "erro", null);
	}

	@RequestMapping(value = "/gettest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> gettest() {
		boolean yes = WebUtil.register("zarkers", "19961996", "zarker");
		System.out.println(yes);
		return returnMap(1, "", yes);
	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteuser() {
		boolean yes = WebUtil.delete("zarkers");
		System.out.println(yes);
		return returnMap(1, "", yes);
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTime() {
		return returnMap(0, null, new Date());
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

	public String simpleDecrypt(String str) {
		String[] a = PasswordUtil.simpleDecrypt(str).split("_");
		try {
			if (a.length == 2) {
				Long l = new Date().getTime() - Long.valueOf(a[1]);
				if (l > 0 && l < 60000) {
					return a[0];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
