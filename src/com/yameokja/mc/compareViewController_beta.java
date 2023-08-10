package com.yameokja.mc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class compareViewController_beta 
{
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value="/compareView.action", method = RequestMethod.POST)
	public String stDetail(HttpServletRequest request, Model model, String checkedCompare)
	{
		HttpSession session = request.getSession();
		String user_num = (String)session.getAttribute("user_num");
		
		String result = "";
		
		IcompareViewDAO dao = sqlSession.getMapper(IcompareViewDAO .class);
		IUserDAO uDao = sqlSession.getMapper(IUserDAO.class);
		
		// 사용자 정보
		UserDTO user = uDao.searchUserInfo(user_num, "num");
		
		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();

		if (month < 7)
			user.setUser_grade(uDao.firstHalf(user_num).user_grade);
		else
			user.setUser_grade(uDao.secondHalf(user_num).user_grade);
		
		model.addAttribute("user", user);
		
		System.out.println(checkedCompare.substring(0, checkedCompare.length() - 1));
		String tmpStr = checkedCompare.substring(0, checkedCompare.length() - 1);
		String[] comparedStore = tmpStr.split(",");
		
		List<Integer> stnumList = new ArrayList<Integer>();
		
		for (String st_num : comparedStore)
		{
			stnumList.add(Integer.parseInt(st_num));
			/* System.out.println(st_num); */
		}
		/* System.out.println(comparedStore); */
		
		ArrayList<StoreDetailDTO> stores = dao.store(stnumList);
		
		ArrayList<StoreOpencloseDTO> openCloses = dao.openClose(stnumList);	//-- 비교함에서 선택한 가게 영업정보 리스트
			
		ArrayList<StoreCheckDTO> stChecks = dao.stcheck(stnumList);			//-- 비교함에서 선택한 가게 체크정보 리스트
		
		model.addAttribute("storeLen", stnumList.size());
		model.addAttribute("store", stores);
		model.addAttribute("openClose", openCloses);
		model.addAttribute("stcheck", stChecks);
		
		/*
		//int st_num = Integer.parseInt(request.getParameter("st_num"));
		String st_numString = request.getParameter("st_num");
		System.out.println("Received st_num: " + st_numString);
		
		if (st_numString == null || st_numString.isEmpty()) 
		{
		    // Handle error or return a default value
		}
		String[] values = st_numString.split(",");
		
		int storeLen = values.length;
		
		ArrayList<ArrayList<StoreDetailDTO>> stores = new ArrayList<>();
		ArrayList<ArrayList<StoreOpencloseDTO>> openCloses = new ArrayList<>();
		ArrayList<ArrayList<StoreCheckDTO>> stChecks = new ArrayList<>();
		ArrayList<ArrayList<compareViewDTO>> menus = new ArrayList<>();
		*/
		
		/*
		for (Integer value : stnumList) // st_num 들
		{
		    try 
		    {
		        int st_num = Integer.parseInt(value.trim());

		        ArrayList<StoreDetailDTO> storeForCurrentNum = dao.store(st_num);
		        
		        System.out.println("Fetched size for st_num " + st_num + ": " + storeForCurrentNum.size());
		        
		        // Only add to 'stores' if there's data
		        if (storeForCurrentNum != null && !storeForCurrentNum.isEmpty()) 
		        {
		            stores.add(storeForCurrentNum);
		            System.out.println("Data added for st_num " + st_num);
		        } 
		        else 
		        {
		            System.out.println("No data or null data for st_num " + st_num);
		        }

		        openCloses.add(dao.openClose(st_num));
		        stChecks.add(dao.stcheck(st_num));
		        menus.add(dao.menuLists(st_num));

		        System.out.println("stores after adding data for st_num " + st_num + ": " + stores);

		    } 
		    catch(NumberFormatException nfe)
		    {
		        System.out.println("Failed to parse st_num value: " + value);
		        System.out.println(nfe.toString());
		    } 
		    catch(Exception e)
		    {
		        System.out.println(e.toString());
		    }
		}
		*/
		

		//model.addAttribute("menuLists", menus);
		
		
		
		//----------------------------------------


		//----------------------------------------

		
		/*
		for (String value : values)
		{
		    try 
		    {
		    		int st_num = Integer.parseInt(value.trim());
		
					// 가게 기본 사항
					model.addAttribute("store", dao.store(st_num));
					
					
					// 가게 영업시간 + 휴무일
					model.addAttribute("openClose", dao.openClose(st_num));
					
					
					// 가게 체크박스
					ArrayList<StoreCheckDTO> stCheckList = dao.stcheck(st_num);
					if(stCheckList.size()>0)
					{
						model.addAttribute("stCheckList", stCheckList);
					}
					else
					{
						model.addAttribute("stCheckList", null);
					}
					
					
					// 가게 메뉴
					ArrayList<compareViewDTO> menuLists = dao.menuLists(st_num);
					if(menuLists.size()>0)
					{
						model.addAttribute("menuLists", menuLists);
					}
					else
						model.addAttribute("menuLists", null);
		    }
		    catch (Exception e) 
		    {
		        System.out.println(e.toString());
		    }
		}
		*/
		
		result = "/WEB-INF/view/compareView-beta.jsp";
		
		return result;
	}
	
}

