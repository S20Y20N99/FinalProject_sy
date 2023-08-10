package com.yameokja.mc;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/*import java.util.ArrayList;
import java.util.HashMap;*/

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/yameokja.action", method=RequestMethod.GET)
	public String firstPageLoad(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String result = "";
		
		if (session != null && session.getAttribute("user_num") != null && !session.getAttribute("user_num").equals(""))
		{
			result = "redirect:main.action";
			
			return result;
		}
		 
		
		String check = "";
		
		if (session.getAttribute("check") != null)
		{
			check = (String)session.getAttribute("check");
			if (check.equals("Loginfail"))
				session.setAttribute("check", "fail");
			else
				session.removeAttribute("check");
		}
		
		
		result = "/WEB-INF/view/yameokja_login.jsp";
		
		return result;
	}
	
	
	@RequestMapping(value="/login.action", method=RequestMethod.POST)
	public String storeMainLoad(HttpServletRequest request, Model model, UserDTO user)
	{
		HttpSession session = request.getSession();

		String result = "";
		int check = 0;
		
		IUserDAO uDao = sqlSession.getMapper(IUserDAO.class);
		IAdminMainDAO iDao = sqlSession.getMapper(IAdminMainDAO.class);
		
		check = uDao.userLoginCheck(user.user_id, user.user_pw);
		
		if (check == 1)
		{
			UserDTO userInfo = uDao.searchUserInfo(user.user_id, "id");
			session.setAttribute("user_num", userInfo.user_num);
			
			int storeCheck = uDao.storeCheck(userInfo.user_num);
			
			if (storeCheck > 0)
				result = "redirect:storemain.action";
			else
				result = "redirect:main.action";
		}
		else if (check == 0)
		{
			check = iDao.adminLoginCheck(user.user_id, user.user_pw);
			
			if (check == 1)
			{
				String admin_num = iDao.searchNum(user.user_id, "id").getAdmin_num();
				session.setAttribute("admin_num", admin_num);
				
				result = "redirect:adminmain.action";
			}
			else
			{
				session.setAttribute("check", "Loginfail");
				result = "redirect:yameokja.action";
			}
		}
		
		return result;
	}
	
	@RequestMapping(value="/join.action", method=RequestMethod.POST)
	public String join()
	{
		String result = "";
		
		result = "/WEB-INF/view/yameokja_join.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/idduplicheck.action", method=RequestMethod.POST)
		@ResponseBody
	public String idCheck(@RequestParam("user_id") String user_id, String userid)
	{
		String result = "";
		int count = 0;
		
		/*
		if (userid.contains(" "))
			result += "{\"count\":\"-1\"}";
		else
		{
			IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
			count = udao.idCheck(userid);
			
			result += "{\"count\":\""+count+"\"}";
		}
		*/
		
		String[] arr = user_id.split(" ");
		
		if(arr.length>1)
		{
			result += "{\"count\":\"-1\"}";
		}
		else
		{
			IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
			count = udao.idCheck(user_id);
			result += "{\"count\":\""+count+"\"}";
		}

		return result;
	}
	
	
	@RequestMapping(value="/nickduplicheck.action", method=RequestMethod.POST)
		@ResponseBody
	public String nickCheck(@RequestParam("user_nick") String user_nick, String usernick)
	{
		String result = "";
		int count = 0;
		
		IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
		count = udao.nickCheck(user_nick);
		
		result += "{\"count\":\""+count+"\"}";
		
		return result;
	}
	
	
	@RequestMapping(value="/joinsend.action", method=RequestMethod.POST)
	public String joinSend(UserDTO user)
	{
		String result = "";
		String user_num = "";
		int check = 0;
		
		IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
		
		do
		{
			user_num = randStr();
			check = udao.userNumCheck(user_num);
			
		} while (check != 0);
		
		user.setUser_num(user_num);
		
		int count = udao.joinSend(user);
		
		
		result = "redirect:yameokja.action";
		
		return result;
	}
	
	
	
	
	String randStr()
	{
		Random rnd =new Random();

		StringBuffer buf =new StringBuffer();

		for(int i=0; i<10; i++)
		{

		    // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. 
			// true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

		    if(rnd.nextBoolean())
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    else
		    	buf.append((rnd.nextInt(10)));

		}

		return buf.toString();
	}
	
	
	@RequestMapping(value="/usermypage.action", method = {RequestMethod.POST, RequestMethod.GET})	
	public String studentList(HttpServletRequest requset, Model model)
	{
		HttpSession session = requset.getSession();
		
		String user_num = (String)session.getAttribute("user_num");
		String result = "";
		
		LocalDate currentDate = LocalDate.now();
        int monthValue = currentDate.getMonthValue();
		
        IMainDAO idao = sqlSession.getMapper(IMainDAO.class);
        IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
        
        List<Integer> ibmatList = idao.getIbmatStNumber(user_num);
		List<Integer> jjimList = idao.getJjimStNumber(user_num);
		List<Integer> comList = idao.getStoreComList(user_num);
		
		/* System.out.println(comList); */
		
		UserDTO user = udao.searchUserInfo(user_num, "num");
		
		if (1 <= monthValue && monthValue <= 6)
		{
			user.setPoint_sum(udao.secondHalf(user_num).point_sum);
			user.setUser_grade(udao.firstHalf(user_num).user_grade);
		}
		else if(7 <= monthValue && monthValue <= 12)
		{
			user.setPoint_sum(udao.firstHalf(user_num).point_sum);
			user.setUser_grade(udao.secondHalf(user_num).user_grade);
		}
		
		if (ibmatList.size() > 0)
			model.addAttribute("ibmat_list", idao.getStoreList(ibmatList));
		else
			model.addAttribute("ibmat_list", null);
		
		if (jjimList.size() > 0)
			model.addAttribute("likelist", idao.getStoreList(jjimList));
		else
			model.addAttribute("likelist", null);

		if (comList.size() > 0)
			model.addAttribute("comList", idao.getStoreList(comList));
		else
			model.addAttribute("comList", null);
		
		model.addAttribute("userJjimList", idao.userJjimSearch(user_num));
		
		
		model.addAttribute("user", user);
		model.addAttribute("rvlist", udao.searchRvList(user_num));
		model.addAttribute("comparedlist", udao.searchComparedList(user_num));
		model.addAttribute("tastekeyword", udao.searchTasteKeyword(user_num));
		
		result = "WEB-INF/view/user_MyPage.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/user_stupdate_relist.action", method=RequestMethod.GET)
	public String userstUpdatereListform(HttpServletRequest request, Model model)
	{
	    HttpSession session = request.getSession();
	    String user_num = (String)session.getAttribute("user_num");
	    
	    String actionName = "/user_stupdate_relist.action";
	    
	    IUserstupdaterelistDAO dao = sqlSession.getMapper(IUserstupdaterelistDAO.class);
	    
	    String result = null;
	    
	    // 이전 페이지(?)로부터 넘어온 게시물 번호 수신
		String strNum = request.getParameter("num");
		int num=0;
		if (strNum != null)
			num = Integer.parseInt(strNum);
		
		// 이전 페이지(?)로부터 넘어온 페이지 번호 수신
		String pageNum = request.getParameter("pageNum");
		int currentPage = 1;
		if (pageNum != null)
			currentPage = Integer.parseInt(pageNum);
	    
		// 페이지당 아이템 수를 여기에 설정하세요
	    int itemsPerPage = 10;
	    
	    // 전체 데이터 수 구해서 총 페이지 수 계산
	    int totalPage = dao.count_relist(user_num) / itemsPerPage;
	    
	    if (dao.count_relist(user_num) % itemsPerPage != 0)
	    	totalPage++;
	    System.out.println(currentPage);
	    System.out.println(totalPage);
	    // 전체 페이지 수 보다 표시할 페이지가 큰 경우
		// 표시할 페이지를 전체 페이지로 처리
		// → 데이터를 삭제해서 페이지가 줄어들었을 경우...
		if (currentPage > totalPage)
			currentPage = totalPage;
		
		// 데이터베이스에서 가져올 시작과 끝 위치	    
	    int startRow = (currentPage - 1) * itemsPerPage + 1;
	    int endRow = currentPage + itemsPerPage - 1;
	    
	    
	    
	    List<UserStupdaterelistDTO> user_stupdate_relist = dao.user_stupdate_relist(
	        user_num, 
	        startRow, 
	        endRow
	    );
	    
		/*
		 * System.out.println(actionName);
		 * 
		 * System.out.println(Paging.pageIndexList(currentPage, totalPage, actionName));
		 */
	    
	    model.addAttribute("pageIndex", Paging.pageIndexList(currentPage, totalPage, actionName));
		
	    model.addAttribute("user_stupdate_relist", user_stupdate_relist);
	    result = "/WEB-INF/view/user_StUpdate_reList.jsp";
	    
	    return result;
	};
	
	@RequestMapping(value="/user_rv_report.action", method=RequestMethod.GET)
	public String rvReportListform(HttpServletRequest request, Model model)
	{
	    HttpSession session = request.getSession();
	    String user_num = (String)session.getAttribute("user_num");
	    
	    String actionName = "/seur_rv_report.action";
	    
	    String result = null;
	    
	    IuserrvReportDAO dao = sqlSession.getMapper(IuserrvReportDAO.class);
	    
	    // 이전 페이지(?)로부터 넘어온 게시물 번호 수신
 		String strNum = request.getParameter("num");
 		int num=0;
 		if (strNum != null)
 			num = Integer.parseInt(strNum);
 		
 		// 이전 페이지(?)로부터 넘어온 페이지 번호 수신
 		String pageNum = request.getParameter("pageNum");
 		int currentPage = 1;
 		if (pageNum != null)
 			currentPage = Integer.parseInt(pageNum);
 	    
 		// 페이지당 아이템 수를 여기에 설정하세요
 	    int itemsPerPage = 10;
 	    
 	    // 전체 데이터 수 구해서 총 페이지 수 계산
 	    int totalPage = dao.count_replist(user_num) / itemsPerPage;
 	    
 	    if (dao.count_replist(user_num) % itemsPerPage != 0)
 	    	totalPage++;
 	    System.out.println(currentPage);
 	    System.out.println(totalPage);
 	    // 전체 페이지 수 보다 표시할 페이지가 큰 경우
 		// 표시할 페이지를 전체 페이지로 처리
 		// → 데이터를 삭제해서 페이지가 줄어들었을 경우...
 		if (currentPage > totalPage)
 			currentPage = totalPage;
 		
 		// 데이터베이스에서 가져올 시작과 끝 위치	    
 	    int startRow = (currentPage - 1) * itemsPerPage + 1;
 	    int endRow = currentPage + itemsPerPage - 1;
	    
 	    model.addAttribute("pageIndex", Paging.pageIndexList(currentPage, totalPage, actionName));
	    List<UserrvreportDTO> rvReportList = dao.user_rv_Report(user_num, startRow, endRow); // 여러 개의 DTO 객체를 리스트로 가져옴
	    
	    model.addAttribute("user_rv_Report", rvReportList); // 리스트를 전달
	    result = "/WEB-INF/view/user_rv_Report.jsp";
	    
	    return result;
	};
	
	
	@RequestMapping(value="/logout.action", method = {RequestMethod.POST, RequestMethod.GET})	
	public String logout(HttpServletRequest requset)
	{
		HttpSession session = requset.getSession();
		
		String result = "";
		
		session.removeAttribute("user_num");
		session.removeAttribute("admin_num");
		
		result = "redirect:yameokja.action";
		
		return result;
	}
	
}
