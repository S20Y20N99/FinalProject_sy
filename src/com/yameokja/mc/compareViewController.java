package com.yameokja.mc;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class compareViewController 
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/compareView.action", method=RequestMethod.GET)
	public String compareView(HttpServletRequest request, Model model)
	{
		String result = "";
		
		IcompareViewDAO dao = sqlSession.getMapper(IcompareViewDAO.class);
		
		int st_num1 = Integer.parseInt(request.getParameter("st_num1"));
		int st_num2 = Integer.parseInt(request.getParameter("st_num2"));
		int st_num3 = Integer.parseInt(request.getParameter("st_num3"));
		
		
		model.addAttribute("nameLoCat1", dao.nameLoCat1(st_num1));
		model.addAttribute("scoRe1", dao.scoRe1(st_num1));
		model.addAttribute("openClose1", dao.openClose1(st_num1));
		model.addAttribute("others1", dao.others1(st_num1));
		model.addAttribute("menuLists1", dao.menuLists1(st_num1));
		
		
		model.addAttribute("nameLoCat2", dao.nameLoCat2(st_num2));
		model.addAttribute("scoRe2", dao.scoRe2(st_num2));
		model.addAttribute("openClose2", dao.openClose2(st_num2));
		model.addAttribute("others2", dao.others2(st_num2));
		model.addAttribute("menuLists2", dao.menuLists2(st_num2));
		
		
		model.addAttribute("nameLoCat3", dao.nameLoCat3(st_num3));
		model.addAttribute("scoRe3", dao.scoRe3(st_num3));
		model.addAttribute("openClose3", dao.openClose3(st_num3));
		model.addAttribute("others3", dao.others3(st_num3));
		model.addAttribute("menuLists3", dao.menuLists3(st_num3));
		
		result = "/WEB-INF/view/compareView.jsp";

		return result;
	}
}