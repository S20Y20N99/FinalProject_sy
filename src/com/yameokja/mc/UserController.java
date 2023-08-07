package com.yameokja.mc;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*import java.util.ArrayList;
import java.util.HashMap;*/

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController
{
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings("null")
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
	
	@RequestMapping(value="/idduplicheck.action", method=RequestMethod.GET)
		@ResponseBody
	public String idCheck(HttpServletRequest request, HttpServletResponse response, String userid)
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
		
		String[] arr = userid.split(" ");
		
		if(arr.length>1)
		{
			result += "{\"count\":\"-1\"}";
		}
		else
		{
			IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
			count = udao.idCheck(userid);
			result += "{\"count\":\""+count+"\"}";
		}

		return result;
	}
	
	
	@RequestMapping(value="/nickduplicheck.action", method=RequestMethod.GET)
		@ResponseBody
	public String nickCheck(HttpServletRequest request, HttpServletResponse response, String usernick)
	{
		String result = "";
		int count = 0;
		
		IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
		count = udao.nickCheck(usernick);
		
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
	
	
	@RequestMapping(value="/usermypage.action", method = RequestMethod.POST)	
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
			model.addAttribute("comparingbox", idao.getStoreList(comList));
		else
			model.addAttribute("comparingbox", null);
		
		model.addAttribute("userJjimList", idao.userJjimSearch(user_num));
		
		
		model.addAttribute("user", user);
		model.addAttribute("rvlist", udao.searchRvList(user_num));
		model.addAttribute("comparedlist", udao.searchComparedList(user_num));
		model.addAttribute("tastekeyword", udao.searchTasteKeyword(user_num));
		
		result = "WEB-INF/view/user_MyPage.jsp";
		
		return result;
	}
	
	
	@RequestMapping(value="/logout.action", method = RequestMethod.POST)	
	public String logout(HttpServletRequest requset)
	{
		HttpSession session = requset.getSession();
		
		String result = "";
		
		session.removeAttribute("user_num");
		session.removeAttribute("admin_num");
		
		result = "WEB-INF/view/yameokja_login.jsp";
		
		return result;
	}
	
}
