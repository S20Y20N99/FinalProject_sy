package com.yameokja.mc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.ibatis.session.SqlSession;

@Controller
public class AdminMainController
{
	@Autowired
	private SqlSession sqlSesion;
	
	@RequestMapping(value="/adminmain.action", method=RequestMethod.GET)
	public String allList(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("rvList", dao.rvList());
		model.addAttribute("reqList", dao.reqList());
		model.addAttribute("objList", dao.objList());
		model.addAttribute("revoList", dao.revoList());
		model.addAttribute("inoutList", dao.inoutList());
		
		result = "/WEB-INF/view/admin_main.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/reviewListView.action", method=RequestMethod.GET)
	public String reviewListView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("rvList", dao.rvList());
		
		result = "/WEB-INF/view/admin_reviewListView.jsp";
		return result;
	}
	
	@RequestMapping(value="/storeErrView.action", method=RequestMethod.GET)
	public String storeErrView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("reqList", dao.reqList());
		
		result = "/WEB-INF/view/admin_storeErrView.jsp";
		return result;
	}
	
	@RequestMapping(value="/objectionView.action", method = RequestMethod.GET)
	public String objectionView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("objList", dao.objList());
		
		result = "/WEB-INF/view/admin_objectionView.jsp";
		
		return result;
	}
	
	@RequestMapping(value = "/penaltyView.action", method = RequestMethod.GET)
	public String penaltyView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("revoList", dao.revoList());
		
		result = "/WEB-INF/view/admin_penaltyView.jsp";
		return result;
	}
	
	@RequestMapping(value = "/inoutView.action", method = RequestMethod.GET)
	public String inoutView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("inoutList", dao.inoutList());
		
		result = "/WEB-INF/view/admin_inoutView.jsp";
		return result;
	}
	
	@RequestMapping(value = "/mainAdminView.action", method = RequestMethod.GET)
	public String mainAdminView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
		
		model.addAttribute("admin_name", dao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("rvList", dao.rvList());
		model.addAttribute("reqList", dao.reqList());
		model.addAttribute("objList", dao.objList());
		model.addAttribute("revoList", dao.revoList());
		model.addAttribute("inoutList", dao.inoutList());
		
		result = "/WEB-INF/view/main_admin.jsp";
		return result;
		
	}
	
	@RequestMapping(value="/reviewreportform.action", method=RequestMethod.GET)
	public String reviewReportForm(Model model,HttpServletRequest request, RvApplyViewDTO dto)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		int reviewNum = Integer.parseInt(request.getParameter("rep_apply_num"));
		String state = request.getParameter("state");
		session.setAttribute("rep_apply_num", reviewNum);
		
		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		model.addAttribute("rvupdate", dao.rvupdate(reviewNum));
		model.addAttribute("admin_name", daoM.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("review", dao.rvSearch(reviewNum));
		model.addAttribute("state", state);
		
		result = "/WEB-INF/view/reviewReportForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/errreportform.action", method=RequestMethod.GET)
	public String storeErrReportForm(Model model,HttpServletRequest request, ReqApplyViewDTO dto)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		int reqNum = Integer.parseInt(request.getParameter("req_apply_num"));
		String state = request.getParameter("state");
		
		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		model.addAttribute("admin_name", daoM.searchNum(admin_num, "num").getAdmin_name());
		
		ReqApplyViewDTO err = dao.accuSearch(reqNum);
		model.addAttribute("err", err);
		int st_num = err.getSt_num();
		
		IstDetailDAO_userView sddao = sqlSesion.getMapper(IstDetailDAO_userView.class);
		
		model.addAttribute("store", sddao.store(st_num));
		model.addAttribute("clikeNum", sddao.clikeNum(st_num));
		model.addAttribute("stinfoupdate", dao.stinfoupdate(reqNum));
		ArrayList<StoreKeyDTO> stKeyList =  sddao.stKeys(st_num);
		if(stKeyList.size()>0)
		{
			model.addAttribute("stKeys", stKeyList);
		}
		else
			model.addAttribute("stKeys", null);
		model.addAttribute("openClose", sddao.openClose(st_num));
		ArrayList<StoreBreaktimeDTO> breakTime = sddao.breakTime(st_num);
		if(breakTime.size() > 0)
		{
			model.addAttribute("breakTime", breakTime);
		}
		else
			model.addAttribute("breakTime", null);
		ArrayList<String> stPayList = sddao.stPay(st_num);
		model.addAttribute("stPayList", stPayList);
		ArrayList<StoreCheckDTO> stCheckList = sddao.stcheck(st_num);
		if(stCheckList.size()>0)
		{
			model.addAttribute("stCheckList", stCheckList);
		}
		else
		{
			model.addAttribute("stCheckList", null);
		}
		ArrayList<StoreMenuDTO> menuLists = sddao.menuLists(st_num);
		if(menuLists.size()>0)
		{
			model.addAttribute("menuLists", menuLists);
		}
		else
			model.addAttribute("menuLists", null);	
		
		
		model.addAttribute("state", state);
		
		result = "/WEB-INF/view/storeErrReportForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/objreportform.action", method=RequestMethod.GET)
	public String objectionReportForm(Model model,HttpServletRequest request, ObjApplyViewDTO dto)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		int objNum = Integer.parseInt(request.getParameter("obj_apply_num"));
		
		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		model.addAttribute("objupdate", dao.objupdate(objNum));
		model.addAttribute("admin_name", daoM.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("obj", dao.objSearch(objNum));
		
		result = "/WEB-INF/view/objectionReportForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/penaltyrevokeform.action", method=RequestMethod.GET)
	public String penaltyRevokeForm(Model model,HttpServletRequest request, RevoApplyViewDTO dto)
	{
		HttpSession session = request.getSession();
		
		String result = "";
		String admin_num = (String)session.getAttribute("admin_num");
		int revoNum = Integer.parseInt(request.getParameter("revo_apply_num"));
		
		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		
		model.addAttribute("revoupdate", dao.revoupdate(revoNum));
		model.addAttribute("admin_name", daoM.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("revo", dao.revoSearch(revoNum));
		
		result = "/WEB-INF/view/penaltyRevokeForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/inapplyform.action", method= {RequestMethod.GET, RequestMethod.POST})
	public String inApplyForm(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();
		
		/* int admin_num = (Integer)session.getAttribute("admin_num"); */
		
		int in_apply_num = Integer.parseInt(request.getParameter("in_apply_num"));
		String admin_num = (String)session.getAttribute("admin_num");
		
		
		IAdminMainDAO amdao = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO afdao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		model.addAttribute("inapplyupdate", afdao.inapplyupdate(in_apply_num));
		model.addAttribute("admin_name", amdao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("in", afdao.inSearch(in_apply_num));
		model.addAttribute("in_apply_num", in_apply_num);
		
		result = "/WEB-INF/view/inApplyForm.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/outapplyform.action", method= {RequestMethod.GET, RequestMethod.POST})
	public String outApplyForm(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();
		
		/* int admin_num = (Integer)session.getAttribute("admin_num"); */
		
		int out_apply_num = Integer.parseInt(request.getParameter("out_apply_num"));
		String admin_num = (String)session.getAttribute("admin_num");
		
		IAdminFindDAO afdao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		model.addAttribute("out", afdao.outSearch(out_apply_num));
		
		/*
		IAdminMainDAO amdao = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO afdao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		model.addAttribute("inapplyupdate", afdao.inapplyupdate(in_apply_num));
		model.addAttribute("admin_name", amdao.searchNum(admin_num, "num").getAdmin_name());
		model.addAttribute("in", afdao.inSearch(in_apply_num));
		model.addAttribute("in_apply_num", in_apply_num);
		*/
		result = "/WEB-INF/view/outApplyForm.jsp";
		
		return result;
	}
	
	
	
	@RequestMapping(value="/filedownload.action", method= {RequestMethod.POST, RequestMethod.GET})
		@ResponseBody
	public String fileDownload(@RequestParam("path")String path, @RequestParam("fileName")String fileName, HttpServletResponse response
			, HttpServletRequest request, Model model)
	{
		
		System.out.println(path);
		System.out.println(fileName);

		
		if (FileManager.doFileDownload(fileName, path, response))
			return "1";
		else
			return "-1";
		
	}
	
	/*
	 * // 리뷰 신고 반려 일시
	 * 
	 * @RequestMapping(value="/rvreportre.action", method=RequestMethod.GET) public
	 * String rvreportre(Model model,HttpServletRequest request) { HttpSession
	 * session = request.getSession();
	 * 
	 * String result = ""; String admin_num =
	 * (String)session.getAttribute("admin_num");
	 * 
	 * 
	 * IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class); IAdminFindDAO
	 * dao = sqlSesion.getMapper(IAdminFindDAO.class);
	 * 
	 * model.addAttribute("revoupdate", dao.revoupdate(revoNum));
	 * model.addAttribute("admin_name", daoM.searchNum(admin_num,
	 * "num").getAdmin_name()); model.addAttribute("revo", dao.revoSearch(revoNum));
	 * 
	 * result = "/WEB-INF/view/penaltyRevokeForm.jsp";
	 * 
	 * return result; }
	 */
		
		
		// 리뷰 신고 승인 일시
		
		@RequestMapping(value="/rvreportaccess.action", method=RequestMethod.GET)
		public String rvreportaccess(Model model,HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			String result = "";
			String admin_num = (String)session.getAttribute("admin_num");
			
			
			IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
			IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);
			
			int rep_apply_num = (int) session.getAttribute("rep_apply_num");
			System.out.println(rep_apply_num);
			System.out.println(admin_num);
			
			model.addAttribute("rvreportaccess", dao.rvreportaccess(rep_apply_num, admin_num));
			
			result = "/WEB-INF/view/admin_main.jsp";
			
			return result;
		}
		
		/*
		 * // 리뷰 신고 중재 일시
		 * 
		 * @RequestMapping(value="/rvreporthalf.action", method=RequestMethod.GET)
		 * public String rvreporthalf(Model model,HttpServletRequest request) {
		 * HttpSession session = request.getSession();
		 * 
		 * String result = ""; String admin_num =
		 * (String)session.getAttribute("admin_num");
		 * 
		 * 
		 * IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class); IAdminFindDAO
		 * dao = sqlSesion.getMapper(IAdminFindDAO.class);
		 * 
		 * model.addAttribute("revoupdate", dao.revoupdate(revoNum));
		 * model.addAttribute("admin_name", daoM.searchNum(admin_num,
		 * "num").getAdmin_name()); model.addAttribute("revo", dao.revoSearch(revoNum));
		 * 
		 * result = "/WEB-INF/view/penaltyRevokeForm.jsp";
		 * 
		 * return result; }
		 */
		
		@RequestMapping(value="/inapplysend.action", method= {RequestMethod.GET, RequestMethod.POST})
		public String inApplySend(Model model, HttpServletRequest request)
		{
			String result = "";
			HttpSession session = request.getSession();
			
			IAdminFindDAO fdao = sqlSesion.getMapper(IAdminFindDAO.class);
			IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);
			
			
			int admin_num = Integer.parseInt((String)session.getAttribute("admin_num"));
			
			int in_apply_num = Integer.parseInt(request.getParameter("in_apply_num"));
			
			fdao.inprocess(in_apply_num, admin_num);
			
			model.addAttribute("admin_name", dao.searchNum((String)session.getAttribute("admin_num"), "num").getAdmin_name());
			
			return result;
		}
		
	
}
