package cn.com.oa.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.oa.model.Doc;
import cn.com.oa.model.DocMeet;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Notice;
import cn.com.oa.model.Organization;
import cn.com.oa.model.Page;
import cn.com.oa.model.Task;
import cn.com.oa.model.Tree;
import cn.com.oa.model.User;
import cn.com.oa.service.DocService;
import cn.com.oa.service.MeetService;
import cn.com.oa.service.NoticeService;
import cn.com.oa.service.OrganizationService;
import cn.com.oa.service.TaskService;
import cn.com.oa.service.UserService;
import cn.com.oa.util.Const;
import cn.com.oa.util.UuidUtil;

@Controller
@RequestMapping("/others")
public class OtherController extends BaseController {
	@Resource
	private NoticeService noticeService;
	@Resource
	private UserService userService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private DocService docService;
	@Resource
	private TaskService taskService;
	@Resource
	private MeetService meetService;

	/**
	 * 返回到公告页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/noticeAuditIndex", method = RequestMethod.GET)
	public ModelAndView noticeAuditIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("others/noticeAuditIndex");
		return mav;
	}

	/**
	 * 返回到我的审计页面
	 */
	@RequestMapping(value = "/ApproveIndex", method = RequestMethod.GET)
	public ModelAndView ApproveIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("others/ApproveIndex");
		return mav;
	}

	/**
	 * 返回到我的转发页面
	 */

	@RequestMapping(value = "/RelayIndex", method = RequestMethod.GET)
	public ModelAndView RelayIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("others/RelayIndex");
		return mav;
	}

	/**
	 * 接收到的公告列表
	 */

	@RequestMapping(value = "/getOwnNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOwnNoticeList(Notice notice, Page page,
			HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		String id = (String) currentUser.getPrincipal();
		User user = userService.find(id);
		Page pageObj = new Page();
//		try {
//			currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
//			page.setIds(user.getRemark());
//			pageObj = noticeService.findByParameterToPage(notice, page);
//			return returnMap(0, "", pageObj);
//		} catch (AuthorizationException e1) {
			try {
				page.setIds(user.getOid());
				pageObj = noticeService.findByParameterToPage(notice, page);
				return returnMap(0, "", pageObj);

			} catch (Exception e) {
				e.printStackTrace();
			}

//		}
		return returnMap(0, "", null);
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
		Notice notice = noticeService.selectone(id);
		return returnMap(0, "", notice);
	}

	/**
	 * 首页获取到公告
	 */
	@RequestMapping(value = "/HomegetNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> HomegetNotice() {
		Subject currentUser = SecurityUtils.getSubject();
		String id = (String) currentUser.getPrincipal();
		User user = userService.find(id);
		List<Notice> notices = new ArrayList<Notice>();
//		try {
//			currentUser.checkPermission(Const.PERMISSION_COMMONLYADMIN);
//			notices = noticeService.findByParameterinhome(user.getRemark());
//			return returnMap(0, "", notices);
//		} catch (AuthorizationException e1) {
			try {
				notices = noticeService.findByParameterinhome(user.getOid());
				return returnMap(0, "", notices);

			} catch (Exception e) {
				e.printStackTrace();
			}

//		}
		return returnMap(0, "", null);

	}

	/**
	 * 转发组织树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOrganizationTreeData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrganizationTreeData() {
		Subject currentUser = SecurityUtils.getSubject();
		String uid = (String) currentUser.getPrincipal();
		User user = userService.find(uid);
		List<Tree> treeList = new ArrayList<Tree>();
		List<Organization> organizations = organizationService
				.findParentById(user.getOid());
		Tree tree = new Tree(organizations.get(0));
		tree.setChildren(getOrganizationTree(organizations.get(0).getId()));
		treeList.add(tree);
		return returnMap(0, "", treeList);
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
	 * 转发
	 * 
	 * @param id
	 * @param organid
	 * @param type
	 *            (0:doc;1:meet)
	 * @return
	 */

	@RequestMapping(value = "/Relay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Relay(String id, String organid, String type,String relayRemark) {
		Subject currentRoot = SecurityUtils.getSubject();
		String[] oidList = organid.split(",");
		String uids="";
		//转发公文
		String did=UuidUtil.get32UUID();
			//该机构下面的所有人员
		try {
			for (String oid : oidList) {	
				String uidLists=userService.finduidByOid(oid);
				if(uidLists==null){
					continue;
				}
				String[] uidList=uidLists.split(",");
				for(String uid: uidList){
				Task task = new Task();
				task.setDid(did);
				task.setOid(oid);
				task.setUid(uid);
				uids+=uid+",";
				task.setType(Integer.parseInt(type));
				task.setSignStatus(1);
				task.setRemark("1");
				taskService.insert(task);
				}
			}
			String[] nums=uids.split(",");
			if (type.equals("1")) {
				Doc docone=new Doc();
				docone=docService.find(id);
				Doc doc = new Doc();
				doc.setId(did);
				doc.setPersonnel(uids.substring(0,uids.length()-1));
				doc.setCuid(String.valueOf(currentRoot.getPrincipal()));
				doc.setSignNum(nums.length);
				doc.setTid(id);
				doc.setRelayRemark(relayRemark);
				doc.setRemark("0");
				if(docone.getOldid()==null){
					doc.setOldid(id);
				}else{
					doc.setOldid(docone.getOldid());
				}
				docService.copyInsert(doc);
		}else if(type.equals("2")){
			Meet meetone=new Meet();
			meetone=meetService.find(id);
			Meet meet=new Meet();
			meet.setId(did);
			meet.setPersonnel(uids.substring(0,uids.length()-1));
			meet.setCuid(String.valueOf(currentRoot.getPrincipal()));
			meet.setSignNum(nums.length);
			meet.setTid(id);
			meet.setRelayRemark(relayRemark);
			meet.setRemark("0");
			if(meetone.getOldid()==null){
				meet.setOldid(id);
			}else{
				meet.setOldid(meetone.getOldid());
			}
			meetService.copyInsert(meet);	
		}
		return returnMap(0, "", "已转发");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap(0,"","转发未成功！");
	}
	
	

	
	
	
	/**
	 * 获取我的转发公文和会议
	 */	
	@RequestMapping(value = "/findRelay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findRelay(
			DocMeet docmeet,Page page) {
			Subject currentRoot=SecurityUtils.getSubject();
			String cuid=(String) currentRoot.getPrincipal();
			docmeet.setCuid(cuid);
			page=meetService.mfindByRelayToPage(docmeet, page);
			return returnMap(0, "成功", page);
		
	}
	
	/**
	 * 获取我的公文审批和会议审批
	 * 
	 * @param DocMeets
	 * @param page
	 * @return
	 */
	
	@RequestMapping(value = "/findApproveRelay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> mfindApproveRelay(DocMeet docmeet,
			Page page) {
			Subject currentRoot=SecurityUtils.getSubject();
			String cuid=(String) currentRoot.getPrincipal();
			docmeet.setCuid(cuid);
			page=meetService.mfindByApproveToPage(docmeet, page);
			return returnMap(0, "成功", page);
		
	}
	
	
	
	/**
	 * 公文和会议的审批
	 */
	@RequestMapping(value="/docRelayPass",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> docRelayPass(String tid,String passRemark,String signnum){
		Task task =new Task();
		task.setId(tid);
		task.setRemark("0");
		task.setPassRemark(passRemark);
		task.setSignStatus(0);
		taskService.update(task);
		task=taskService.find(tid);
		String did=task.getDid();
		if(task.getType()==1){
			//公文
			int i=taskService.getDocRelayNum(did);
			if(i==Integer.parseInt(signnum)){
				Doc doc =new Doc();
				doc.setSignStatus(0);
				doc.setId(did);
				docService.update(doc);
			}
		}else if(task.getType()==2){
			//会议
			int i=taskService.getMeetRelayNum(did);
			if(i==Integer.parseInt(signnum)){
				Meet meet =new Meet();
				meet.setSignStatus(0);
				meet.setId(did);
				meetService.update(meet);
			}
		}
		return returnMap(0,"","已审批");
	}
	
	/**
	 * 获取自己审批的意见
	 */
	@RequestMapping(value="/docApproveGet",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> docApproveGet(String tid){
		Task task=new Task();
		task=taskService.find(tid);
		return returnMap(0,"",task);
	}
	
	/**
	 * 获取审批的详情
	 * @param did
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/findapproved",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findapproved(String did,String type){
		Task task=new Task();
		task.setDid(did);
		task.setType(Integer.parseInt(type));
		List<Task> tasks=taskService.findapproved(task);
		return returnMap(0,"",tasks);
	}
	
		
	

}
