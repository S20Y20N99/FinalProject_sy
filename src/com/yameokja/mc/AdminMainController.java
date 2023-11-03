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

	@RequestMapping(value = "/adminmain.action", method = RequestMethod.GET)
	public String allList(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("rvList", dao.rvList());
		model.addAttribute("reqList", dao.reqList());
		model.addAttribute("objList", dao.objList());
		model.addAttribute("revoList", dao.revoList());
		model.addAttribute("inoutList", dao.inoutList());

		result = "/WEB-INF/view/admin_main.jsp";

		return result;
	}

	@RequestMapping(value = "/reviewListView.action", method = RequestMethod.GET)
	public String reviewListView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("rvList", dao.rvList());

		result = "/WEB-INF/view/admin_reviewListView.jsp";
		return result;
	}

	@RequestMapping(value = "/storeErrView.action", method = RequestMethod.GET)
	public String storeErrView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("reqList", dao.reqList());

		result = "/WEB-INF/view/admin_storeErrView.jsp";
		return result;
	}

	@RequestMapping(value = "/objectionView.action", method = RequestMethod.GET)
	public String objectionView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("objList", dao.objList());

		result = "/WEB-INF/view/admin_objectionView.jsp";

		return result;
	}

	@RequestMapping(value = "/penaltyView.action", method = RequestMethod.GET)
	public String penaltyView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("revoList", dao.revoList());

		result = "/WEB-INF/view/admin_penaltyView.jsp";
		return result;
	}

	@RequestMapping(value = "/inoutView.action", method = RequestMethod.GET)
	public String inoutView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("inoutList", dao.inoutList());

		result = "/WEB-INF/view/admin_inoutView.jsp";
		return result;
	}

	//??
	@RequestMapping(value = "/mainAdminView.action", method = RequestMethod.GET)
	public String mainAdminView(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO dao = sqlSesion.getMapper(IAdminMainDAO.class);

		model.addAttribute("admin_name", dao.searchNum(admin_num));
		model.addAttribute("rvList", dao.rvList());
		model.addAttribute("reqList", dao.reqList());
		model.addAttribute("objList", dao.objList());
		model.addAttribute("revoList", dao.revoList());
		model.addAttribute("inoutList", dao.inoutList());

		result = "/WEB-INF/view/main_admin.jsp";
		return result;

	}

	@RequestMapping(value = "/reviewreportform.action", method = RequestMethod.GET)
	public String reviewReportForm(Model model, HttpServletRequest request, RvApplyViewDTO dto)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");
		int reviewNum = Integer.parseInt(request.getParameter("rep_apply_num"));
		String state = request.getParameter("state");
		session.setAttribute("rep_apply_num", reviewNum);

		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);

		model.addAttribute("rvupdate", dao.rvupdate(reviewNum));
		model.addAttribute("admin_name", daoM.searchNum(admin_num));
		model.addAttribute("review", dao.rvSearch(reviewNum));
		model.addAttribute("state", state);
		
		if(state.equals("처리완료"))
		{
			int rep_process_num = dao.searchRepProNum(reviewNum, admin_num);
			MidRejRsDTO rsDto = dao.rvreportMidRejRs(rep_process_num);
			model.addAttribute("rvRs", rsDto);
		}

		result = "/WEB-INF/view/reviewReportForm.jsp";

		return result;
	}

	@RequestMapping(value = "/errreportform.action", method = RequestMethod.GET)
	public String storeErrReportForm(Model model, HttpServletRequest request, ReqApplyViewDTO dto)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");
		int reqNum = Integer.parseInt(request.getParameter("req_apply_num"));
		String state = request.getParameter("state");

		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);

		model.addAttribute("admin_name", daoM.searchNum(admin_num));

		ReqApplyViewDTO err = dao.accuSearch(reqNum);
		model.addAttribute("err", err);
		int st_num = err.getSt_num();

		IstDetailDAO_userView sddao = sqlSesion.getMapper(IstDetailDAO_userView.class);

		model.addAttribute("store", sddao.store(st_num));
		model.addAttribute("clikeNum", sddao.clikeNum(st_num));
		model.addAttribute("stinfoupdate", dao.stinfoupdate(reqNum));
		ArrayList<StoreKeyDTO> stKeyList = sddao.stKeys(st_num);
		if (stKeyList.size() > 0)
		{
			model.addAttribute("stKeys", stKeyList);
		} else
			model.addAttribute("stKeys", null);
		model.addAttribute("openClose", sddao.openClose(st_num));
		ArrayList<StoreBreaktimeDTO> breakTime = sddao.breakTime(st_num);
		if (breakTime.size() > 0)
		{
			model.addAttribute("breakTime", breakTime);
		} else
			model.addAttribute("breakTime", null);
		ArrayList<String> stPayList = sddao.stPay(st_num);
		model.addAttribute("stPayList", stPayList);
		ArrayList<StoreCheckDTO> stCheckList = sddao.stcheck(st_num);
		if (stCheckList.size() > 0)
		{
			model.addAttribute("stCheckList", stCheckList);
		} else
		{
			model.addAttribute("stCheckList", null);
		}
		ArrayList<StoreMenuDTO> menuLists = sddao.menuLists(st_num);
		if (menuLists.size() > 0)
		{
			model.addAttribute("menuLists", menuLists);
		} else
			model.addAttribute("menuLists", null);

		model.addAttribute("state", state);

		result = "/WEB-INF/view/storeErrReportForm.jsp";

		return result;
	}

	@RequestMapping(value = "/objreportform.action", method = RequestMethod.GET)
	public String objectionReportForm(Model model, HttpServletRequest request, ObjApplyViewDTO dto)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");
		int objNum = Integer.parseInt(request.getParameter("obj_apply_num"));

		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);

		model.addAttribute("objupdate", dao.objupdate(objNum));
		model.addAttribute("admin_name", daoM.searchNum(admin_num));
		model.addAttribute("obj", dao.objSearch(objNum));

		result = "/WEB-INF/view/objectionReportForm.jsp";

		return result;
	}
	
	@RequestMapping(value = "/objpro.action", method = RequestMethod.POST)
	public String objectionProcess(Model model, HttpServletRequest request, ObjApplyViewDTO dto)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");
		
		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class); 
		
		int obj_apply_num = Integer.parseInt(request.getParameter("obj_apply_num"));
		
		int check = dao.objProcess(obj_apply_num, admin_num);
		
		int obj_process_num = dao.searchOpNum(obj_apply_num);
		
		
		
		String checklabel = request.getParameter("checklabel");
		
		if (checklabel.equals("true"))
		{
			String rej_rs = request.getParameter("rej_rs");
			dao.objRej(rej_rs, obj_process_num);
			
		}
		else if (checklabel.equals("false"))
		{
			String reverse_rs = request.getParameter("rever_rs");
			dao.reverseRs(reverse_rs, obj_process_num);
			dao.delPenalty(obj_process_num);
		}
		
		model.addAttribute("admin_name", daoM.searchNum(admin_num));
		
		result = "redirect:objectionView.action";

		return result;
	}

	@RequestMapping(value = "/penaltyrevokeform.action", method = RequestMethod.GET)
	public String penaltyRevokeForm(Model model, HttpServletRequest request, RevoApplyViewDTO dto)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");
		int revoNum = Integer.parseInt(request.getParameter("revo_apply_num"));

		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);

		model.addAttribute("revoupdate", dao.revoupdate(revoNum));
		model.addAttribute("admin_name", daoM.searchNum(admin_num));
		model.addAttribute("revo", dao.revoSearch(revoNum));

		result = "/WEB-INF/view/penaltyRevokeForm.jsp";

		return result;
	}
	
	@RequestMapping(value = "/revopro.action", method = RequestMethod.POST)
	public String revokeProcess(Model model, HttpServletRequest request, ObjApplyViewDTO dto)
	{
		HttpSession session = request.getSession();

		String result = "";
		int admin_num = (int) session.getAttribute("admin_num");
		
		IAdminMainDAO daoM = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class); 
		
		int revo_apply_num = Integer.parseInt(request.getParameter("revo_num"));
		
		int check = dao.revoProcess(revo_apply_num, admin_num);
		
		int revo_process_num = dao.searchRpNum(revo_apply_num);
		
		System.out.println(revo_process_num);
		
		String checklabel = request.getParameter("checklabel");
		
		if (checklabel.equals("true"))
		{
			String rej_rs = request.getParameter("rej_rs");
			dao.revoRej(rej_rs, revo_process_num);
			
		}
		
		model.addAttribute("admin_name", daoM.searchNum(admin_num));
		
		result = "redirect:objectionView.action";

		return result;
	}

	@RequestMapping(value = "/inapplyform.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String inApplyForm(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();

		/* int admin_num = (Integer)session.getAttribute("admin_num"); */

		int in_apply_num = Integer.parseInt(request.getParameter("in_apply_num"));
		int admin_num = (int) session.getAttribute("admin_num");

		IAdminMainDAO amdao = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO afdao = sqlSesion.getMapper(IAdminFindDAO.class);

		model.addAttribute("inapplyupdate", afdao.inapplyupdate(in_apply_num));
		model.addAttribute("admin_name", amdao.searchNum(admin_num));
		model.addAttribute("in", afdao.inSearch(in_apply_num));
		model.addAttribute("in_apply_num", in_apply_num);

		result = "/WEB-INF/view/inApplyForm.jsp";

		return result;
	}

	@RequestMapping(value = "/outapplyform.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String outApplyForm(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();

		/* int admin_num = (Integer)session.getAttribute("admin_num"); */

		int out_apply_num = Integer.parseInt(request.getParameter("out_apply_num"));

		int admin_num = (int)session.getAttribute("admin_num");
		
		IAdminMainDAO amdao = sqlSesion.getMapper(IAdminMainDAO.class);
		IAdminFindDAO afdao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		
		model.addAttribute("outapplyupdate", afdao.outapplyupdate(out_apply_num));
		model.addAttribute("out", afdao.outSearch(out_apply_num));
		model.addAttribute("admin_name", amdao.searchNum(admin_num));
		model.addAttribute("out_apply_num", out_apply_num);

		/*
		 * IAdminMainDAO amdao = sqlSesion.getMapper(IAdminMainDAO.class); IAdminFindDAO
		 * afdao = sqlSesion.getMapper(IAdminFindDAO.class);
		 * 
		 * model.addAttribute("inapplyupdate", afdao.inapplyupdate(in_apply_num));
		 * model.addAttribute("admin_name", amdao.searchNum(admin_num,
		 * "num").getAdmin_name()); model.addAttribute("in",
		 * afdao.inSearch(in_apply_num)); model.addAttribute("in_apply_num",
		 * in_apply_num);
		 */
		result = "/WEB-INF/view/outApplyForm.jsp";

		return result;
	}

	@RequestMapping(value = "/filedownload.action", method = { RequestMethod.POST, RequestMethod.GET })
		@ResponseBody
	public String fileDownload(@RequestParam("path") String path, @RequestParam("fileName") String fileName,
			HttpServletResponse response, HttpServletRequest request, Model model)
	{

		//System.out.println(path);
		//System.out.println(fileName);

		if (FileManager.doFileDownload(fileName, path, response))
			return "1";
		else
			return "-1";

	}
	
	// 리뷰 신고 처리 (승인/중재/반려)
	@RequestMapping(value = "/rvreportsend.action", method = RequestMethod.GET)
	public String rvReportSend(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();

		IAdminFindDAO dao = sqlSesion.getMapper(IAdminFindDAO.class);

		int admin_num = (int)session.getAttribute("admin_num");

		int rep_apply_num = Integer.parseInt(request.getParameter("rep_apply_num"));

		String check = request.getParameter("check");

		// rep_process table <- insert
		int checkNum = dao.rvreportAccess(rep_apply_num, admin_num);
		
		if(checkNum == 1)
		{
			// rep_process_num
			int rep_process_num = dao.searchRepProNum(rep_apply_num, admin_num);
			//System.out.println("rep_process_num : " + rep_process_num);

			// 중재 처리
			if(check.equals("mid"))
			{
				String mid_rs = request.getParameter("myTextarea1");
				//System.out.println("mid_rs : " + mid_rs);
				
				// rep_mid table <- insert
				dao.rvreportHalf(mid_rs, rep_process_num);
			}
			// 반려 처리
			else if(check.equals("res"))
			{
				String rej_rs = request.getParameter("myTextarea2");
				//System.out.println("rej_rs : " + rej_rs);

				// rep_rej table <- insert
				dao.rvreportRej(rej_rs, rep_process_num);
			}
		}

		result = "redirect:adminmain.action";

		return result;
	}
	
	// 가게정보오류수정요청 (승인/반려)
	@RequestMapping(value="/sterrreqsend.action", method= { RequestMethod.GET, RequestMethod.POST })
	public String stErrReqSend(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();

		IAdminFindDAO fdao = sqlSesion.getMapper(IAdminFindDAO.class);

		int admin_num = (int) session.getAttribute("admin_num");

		int req_apply_num = Integer.parseInt(request.getParameter("req_apply_num"));

		String check = request.getParameter("check");
		
		int checkNum = fdao.reqProcess(req_apply_num, admin_num);
		
		if (checkNum == 1)
		{
			int process = fdao.getReqProNum(req_apply_num, admin_num);
			
			// 승인인 경우 penalty insert
			if (check.equals("true"))
			{
				if(fdao.countPenalty(process)>=5)
					fdao.setPenalty(process, 2);
				else
					fdao.setPenalty(process, 1);
			}
			// 반려인 경우 req_rej insert
			else if(check.equals("false"))
			{
				String rej_rs = request.getParameter("myTextarea");
				fdao.reqRej(process, rej_rs);
			}
		}
		
		result = "redirect:adminmain.action";
		
		return result;
	}
	
	// 가게 등록 처리(승인/반려)
	@RequestMapping(value = "/inapplysend.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String inApplySend(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();

		IAdminFindDAO fdao = sqlSesion.getMapper(IAdminFindDAO.class);

		int admin_num = Integer.parseInt((String) session.getAttribute("admin_num"));

		int in_apply_num = Integer.parseInt(request.getParameter("in_apply_num"));

		String check = request.getParameter("check");
		
		int checkNum = fdao.inprocess(in_apply_num, admin_num);

		//System.out.println(checkNum);
			
			
		if (checkNum == 1)
		{
			int process = fdao.getinprocessnum(in_apply_num);
			
			if (check.equals("false"))
				fdao.stlistinsert(process);
			else if (check.equals("true"))
			{
				String rej_rs = request.getParameter("memo");
				fdao.inrej(rej_rs, process);
			}
		}

		result = "redirect:adminmain.action";

		return result;
	}
		
	// 가게 폐업 처리(승인/반려)
	@RequestMapping(value="/outapplysend.action", method= {RequestMethod.GET, RequestMethod.POST})
	public String outApplySend(Model model, HttpServletRequest request)
	{
		String result = "";
		HttpSession session = request.getSession();
		
		IAdminFindDAO fdao = sqlSesion.getMapper(IAdminFindDAO.class);
		
		int admin_num = Integer.parseInt((String)session.getAttribute("admin_num"));
		
		int out_apply_num = Integer.parseInt(request.getParameter("out_apply_num"));
		
		String check = request.getParameter("check");
		
		int checkNum = fdao.outprocess(out_apply_num, admin_num);
		
		//System.out.println(checkNum);
		
		
		if (checkNum == 1)
		{
			int process = fdao.getoutprocessnum(out_apply_num);
			
			/*if (check.equals("false"))
				fdao.stlistinsert(process);*/
			if (check.equals("true"))
			{
				String rej_rs = request.getParameter("memo");
				fdao.outrej(rej_rs, process);
			}
		}

		result = "redirect:adminmain.action";

		return result;
	}

}
