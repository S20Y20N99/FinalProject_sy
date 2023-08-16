package com.yameokja.mc;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.javassist.compiler.ast.Stmnt;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StoreController
{
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
    private ServletContext servletContext;
	
	@RequestMapping(value="/storemain.action", method= {RequestMethod.GET, RequestMethod.POST})
	public String storeMainLoad(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String result = "";
		
		/* int st_num = Integer.parseInt(str_st_num); */ 
		
		IstDetailDAO_userView dao = sqlSession.getMapper(IstDetailDAO_userView.class);
		IStoreMainDAO smDao = sqlSession.getMapper(IStoreMainDAO.class);
		IUserDAO uDao = sqlSession.getMapper(IUserDAO.class);
		
		
		String user_num = (String)session.getAttribute("user_num");
		
		UserDTO user = uDao.searchUserInfo(user_num, "num");

		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();

		if (month < 7)
			user.setUser_grade(uDao.firstHalf(user_num).user_grade);
		else
			user.setUser_grade(uDao.secondHalf(user_num).user_grade);

		model.addAttribute("user", user);
		
		ArrayList<StoreDTO> st_list = smDao.searchStoreInfo(user_num);
		
		int st_num;
		
		if(smDao.searchRepStore(user_num) == null)
			st_num = st_list.get(0).getSt_num();
		else
			st_num = smDao.searchRepStore(user_num);
		
		
		session.setAttribute("st_num", st_num);
		model.addAttribute("st_num", st_num);
				
		ArrayList<HashMap<String, String>> hashmaplist = smDao.rv_key_sum(st_num);
		
		ArrayList<String> rv_labels = new ArrayList<String>();
		ArrayList<String> rv_data = new ArrayList<String>();
		 
		for (HashMap<String, String> hashMap : hashmaplist)
		{
			rv_labels.add("'" +hashMap.get("RV_KEY_NAME")+"'");
			rv_data.add(String.valueOf(hashMap.get("COUNT_RV_KEY")));
		}
		
		
		ArrayList<HashMap<String, String>> star_transition = smDao.star_transition(st_num);
		
		ArrayList<String> star_labels = new ArrayList<String>();
		ArrayList<String> star_data = new ArrayList<String>();
		 
		for (HashMap<String, String> star : star_transition)
		{
			star_labels.add("'" +star.get("QUARTER_START_DATE")+"'");
			star_data.add(String.valueOf(star.get("AVERAGE_STAR_SCORE")));
		}
		
		
		// 가게 리뷰목록
		ArrayList<StoreReviewDTO> reviews = dao.reviews(st_num);
		
		if(reviews.size() > 0)
		{
			model.addAttribute("reviews", reviews);
		}
		else
			model.addAttribute("reviews", null);
		
		// 가게 리뷰 사진 목록
		ArrayList<StoreRvPhotoDTO> rvPhotos = dao.rvPhoto(st_num);
		
		model.addAttribute("rvPhotos", rvPhotos);
		
		model.addAttribute("st_list", st_list);
		
		model.addAttribute("rv_labels", rv_labels.subList(0, 5));
		model.addAttribute("rv_data", rv_data.subList(0, 5));
		
		model.addAttribute("star_labels", star_labels);
		model.addAttribute("star_data", star_data);
		
		model.addAttribute("rv_key_list", smDao.rv_key_sum(st_num));
		model.addAttribute("rv_list", smDao.rv_list(st_num));
		
		ArrayList<ReviewDTO> arr = smDao.rv_list(st_num);
		for (ReviewDTO reviewDTO : arr)
		{
			System.out.println(reviewDTO.getReply_content());
		}
		/* model.addAttribute("reply_content", reply_content(rv_num)); */
		
		model.addAttribute("alarm", uDao.userAlarm(user_num));
		
		int log_num = smDao.checkfirstlogin(st_num);
		if (log_num != 0) {
		    // 메서드가 null (또는 0)을 반환했을 경우의 처리
			int inif_log = smDao.findfirstlogin(st_num);
			smDao.deletelognum(inif_log);
			session.setAttribute("log_num", 1);
		} 
		
		
		result = "/WEB-INF/view/StoreMainPage.jsp";
		
		return result;
	}
	
	/*
	 * @RequestMapping(value="/deletelognum.action", method = {RequestMethod.POST,
	 * RequestMethod.GET}) public String deletelognum(HttpServletRequest request,
	 * Model model) { String result = "";
	 * 
	 * HttpSession session = request.getSession(); IStoreMainDAO smDao =
	 * sqlSession.getMapper(IStoreMainDAO.class);
	 * 
	 * String log_num = (String) session.getAttribute("log_num");
	 * System.out.println(log_num);
	 * 
	 * 
	 * 
	 * 
	 * result = "/WEB-INF/view/StoreMainPage.jsp";
	 * 
	 * return result; }
	 */
	
	@RequestMapping(value="/Stfirstloginstdego.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String stfirstlogingo()
	{
		String result = "";
		
		result = "/WEB-INF/view/Stfirstloginstdego.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/storeinfo.action", method=RequestMethod.GET)
	public String storeInfoLoad(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String result = "";
		
		/* int st_num = Integer.parseInt(str_st_num); */ 
		
		IstDetailDAO_userView dao = sqlSession.getMapper(IstDetailDAO_userView.class);
		IStoreMainDAO smDao = sqlSession.getMapper(IStoreMainDAO.class);
		IUserDAO uDao = sqlSession.getMapper(IUserDAO.class);
		
		
		String user_num = (String)session.getAttribute("user_num");
		
		UserDTO user = uDao.searchUserInfo(user_num, "num");

		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();

		if (month < 7)
			user.setUser_grade(uDao.firstHalf(user_num).user_grade);
		else
			user.setUser_grade(uDao.secondHalf(user_num).user_grade);

		model.addAttribute("user", user);
		
		ArrayList<StoreDTO> st_list = smDao.searchStoreInfo(user_num);
		
		int st_num = Integer.parseInt(request.getParameter("stNum"));
		model.addAttribute("st_num", st_num);
				
		ArrayList<HashMap<String, String>> hashmaplist = smDao.rv_key_sum(st_num);
		
		ArrayList<String> rv_labels = new ArrayList<String>();
		ArrayList<String> rv_data = new ArrayList<String>();
		 
		for (HashMap<String, String> hashMap : hashmaplist)
		{
			rv_labels.add("'" +hashMap.get("RV_KEY_NAME")+"'");
			rv_data.add(String.valueOf(hashMap.get("COUNT_RV_KEY")));
		}
		
		
		ArrayList<HashMap<String, String>> star_transition = smDao.star_transition(st_num);
		
		ArrayList<String> star_labels = new ArrayList<String>();
		ArrayList<String> star_data = new ArrayList<String>();
		 
		for (HashMap<String, String> star : star_transition)
		{
			star_labels.add("'" +star.get("QUARTER_START_DATE")+"'");
			star_data.add(String.valueOf(star.get("AVERAGE_STAR_SCORE")));
		}
		
		
		// 가게 리뷰목록
		ArrayList<StoreReviewDTO> reviews = dao.reviews(st_num);
		
		if(reviews.size() > 0)
		{
			model.addAttribute("reviews", reviews);
		}
		else
			model.addAttribute("reviews", null);
		
		// 가게 리뷰 사진 목록
		ArrayList<StoreRvPhotoDTO> rvPhotos = dao.rvPhoto(st_num);
		
		model.addAttribute("rvPhotos", rvPhotos);
		
		model.addAttribute("st_list", st_list);
		
		model.addAttribute("rv_labels", rv_labels.subList(0, 5));
		model.addAttribute("rv_data", rv_data.subList(0, 5));
		
		model.addAttribute("star_labels", star_labels);
		model.addAttribute("star_data", star_data);
		
		model.addAttribute("rv_key_list", smDao.rv_key_sum(st_num));
		model.addAttribute("rv_list", smDao.rv_list(st_num));
		
		model.addAttribute("rvReplyNumList", smDao.rvReplyNumList(st_num));
		
		model.addAttribute("rvReplyList",smDao.rvReply(st_num));
		
		ArrayList<ReviewDTO> arr = smDao.rv_list(st_num);
		for (ReviewDTO reviewDTO : arr)
		{
			System.out.println(reviewDTO.getReply_content());
		}
		/* model.addAttribute("reply_content", reply_content(rv_num)); */
		
		model.addAttribute("alarm", uDao.userAlarm(user_num));
		
		result = "/WEB-INF/view/StoreMainPage.jsp";
		
		return result;
	}
	
	@RequestMapping(value="/stdetailmodify.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String stDetailModify(HttpServletRequest request, Model model)
	{
		String result = "";
		
		HttpSession session = request.getSession();
		
		IstDetailDAO_userView dao = sqlSession.getMapper(IstDetailDAO_userView.class);
		IStoreMainDAO sdao = sqlSession.getMapper(IStoreMainDAO.class);
		IMainDAO mdao = sqlSession.getMapper(IMainDAO.class);
		IUserDAO uDao = sqlSession.getMapper(IUserDAO.class);
		
		String user_num = (String)session.getAttribute("user_num");
		int st_num = Integer.parseInt(request.getParameter("st_num"));
		
		UserDTO user = uDao.searchUserInfo(user_num, "num");

		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();

		if (month < 7)
			user.setUser_grade(uDao.firstHalf(user_num).user_grade);
		else
			user.setUser_grade(uDao.secondHalf(user_num).user_grade);
		
		ArrayList<StoreDTO> st_list = sdao.searchStoreInfo(user_num);

		model.addAttribute("user", user);
		model.addAttribute("st_num", st_num);
		model.addAttribute("st_list", st_list);
		
		// 가게가 선택할 수 있는 카테고리
		model.addAttribute("foodLabel", sdao.foodLabel());
		model.addAttribute("payLabel", sdao.payLabel());
		model.addAttribute("weekdayLabel", sdao.weekDayLabel());
		model.addAttribute("weekWeekendDayLabel", sdao.weekWeekendDayLabel());
		model.addAttribute("chBoxLabel", sdao.chBoxLabel());
		model.addAttribute("stKeyLabel", mdao.stKeyList());
		
		// 가게 기본 사항
		model.addAttribute("store", dao.store(st_num));
		
		// 가게 키워드
		ArrayList<Integer> stKeyList =  dao.stKeysStr(st_num);
		if(stKeyList.size()>0)
		{
			model.addAttribute("stKeys", stKeyList);
		}
		else
			model.addAttribute("stKeys", null);
		System.out.println(stKeyList);
		
		// 가게 영업시간 + 휴무일
		ArrayList<StoreOpencloseDTO> openClose =  dao.openClose(st_num);
		if(openClose.size() > 0)
		{
			model.addAttribute("openClose", openClose);
		}
		else
			model.addAttribute("openClose", null);
		
		
		// 가게 브레이크 타임
		ArrayList<StoreBreaktimeDTO> breakTime = dao.breakTime(st_num);
		if(breakTime.size() > 0)
		{
			model.addAttribute("breakTime", breakTime);
		}
		else
			model.addAttribute("breakTime", null);
		
		// 가게 결제수단
		ArrayList<String> stPayList = dao.stPay(st_num);
		model.addAttribute("stPayList", stPayList);
		
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
		ArrayList<StoreMenuDTO> menuLists = dao.menuLists(st_num);
		
		if(menuLists.size()>0)
		{
			model.addAttribute("menuLists", menuLists);
		}
		else
			model.addAttribute("menuLists", null);
		
		
		
		result = "/WEB-INF/view/st_detail_form.jsp";
		
		return result;
	}
  
	@RequestMapping(value="/rvReply.action", method = {RequestMethod.GET, RequestMethod.POST})
	public String rvReply(HttpServletRequest request, Model model)
	{	
		String rvnum = request.getParameter("rv_num");
		String reply_content = request.getParameter("reply_content");
		int rv_num = 0;
		
		System.out.println("rv_num: " + rvnum);
		System.out.println("reply_content from HttpServletRequest: " + reply_content);


		
        // 점검 코드
        if (rvnum == null) 
        {
            System.out.println("경고: 'rv_num' 값이 null 입니다.");
        }
        else
        {
        	rv_num = Integer.parseInt(rvnum);
        }
        
        if (reply_content == null) 
        {
            System.out.println("경고: 'reply_content' 값이 비어있습니다.");
        } 
       
        
        if(reply_content == null || reply_content.trim().isEmpty()) 
        {
            // 에러 메시지를 반환하거나 기타 오류 처리를 수행
            return "redirect:storemain.action";  // 예제용 반환 값, 실제로 적절한 페이지나 메시지로 변경해야 합니다.
        }

		
		HttpSession session = request.getSession();
		IStoreMainDAO dao = sqlSession.getMapper(IStoreMainDAO.class);
		IUserDAO uDao = sqlSession.getMapper(IUserDAO.class);
		//StoreReviewDTO dto = new StoreReviewDTO();
		
		String user_num = (String)session.getAttribute("user_num");
		Integer stNumInteger = (Integer)session.getAttribute("st_num");
		String stnum = (stNumInteger != null) ? stNumInteger.toString() : null;
		int st_num = Integer.parseInt(stnum);
		
		UserDTO user = uDao.searchUserInfo(user_num, "num");

		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();

		if (month < 7)
			user.setUser_grade(uDao.firstHalf(user_num).user_grade);
		else
			user.setUser_grade(uDao.secondHalf(user_num).user_grade);
		

		model.addAttribute("user", user);
		session.setAttribute("st_num", st_num);
		
		
		//dto.setRv_content(reply_content);
		//dto.setRv_num(rv_num);
		
		
		//dao.reviewReply(dto);
		dao.reviewReply(rv_num, reply_content);
		
	    return "redirect:storemain.action";
	}
	
	@RequestMapping(value="/rvcompletebtn.action", method = RequestMethod.POST)
		@ResponseBody
	public String replyCheck(@RequestParam("rv_num") int rv_num, @RequestParam("reply_content") String reply_content)
	{
		String html= "";
		
		
		System.out.println(rv_num);
		System.out.println(reply_content);
		
		
		IStoreMainDAO dao = sqlSession.getMapper(IStoreMainDAO.class);
		int result = dao.reviewReply(rv_num, reply_content);
		
		html += "<div class=\"replyBox\">";
		html += "사장님 : " + reply_content +"</div>";
		
		return html; 
	}
	
	
	@RequestMapping(value="/stdetailinsert.action", method=RequestMethod.POST)
	public String stDetailInsert(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		HttpSession session = request.getSession();
		String rootPath = servletContext.getRealPath("/");
		String CHARSET = "utf-8";
		int LIMIT_SIZE_BYTES = 5 * 1024 * 1024;
		
		StoreregiDTO srdto = new StoreregiDTO();
		
		// 경로상 디렉터리가 존재하지 않으면... 만든다.
		String savePath_menuImg = rootPath + File.separator + "Store_Menu_Img";
		File menu_img_dir = new File(savePath_menuImg);
		if (!menu_img_dir.exists())
		{
			menu_img_dir.mkdirs();
		}
		
		IStoreMainDAO smdao = sqlSession.getMapper(IStoreMainDAO.class);
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(menu_img_dir);
		fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		try 
		{
			int st_num = 0;
			String breakch = "";
			
			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem item : items)
            {
                if (item.isFormField() && item.getFieldName().equals("st_num"))
                {
                	System.out.println(item.getString(CHARSET));
            		st_num = Integer.parseInt(item.getString(CHARSET));
                }
            }
			
			smdao.stOCdelete(st_num);
			smdao.holidaydelete(st_num);
			smdao.bOCdelete(st_num);
			smdao.holidaydelete(st_num);
			smdao.paysdelete(st_num);
			smdao.stKeysdelete(st_num);
			smdao.foodCatdelete(st_num);
			smdao.menudelete(st_num);
			smdao.stsearchKeydelete(st_num);
			
			
			
			
			
			HashMap<String, StoreMenuDTO> menuMap = new HashMap<String, StoreMenuDTO>();
			ArrayList<StoreOpencloseDTO> soclist = new ArrayList<StoreOpencloseDTO>();
			ArrayList<StoreBreaktimeDTO> boclist = new ArrayList<StoreBreaktimeDTO>();
			
			StoreDetailDTO sdto = new StoreDetailDTO();
			
            for (FileItem item : items)
            {
                if (!item.isFormField())
                {
                	if (item.getSize() > 0)
                    {
                		StoreMenuDTO mdto = new StoreMenuDTO();
                		
                        String separator = File.separator;
                        int index =  item.getName().lastIndexOf(separator);
                        String fileName = item.getName().substring(index  + 1);
                        File uploadFile = new File(savePath_menuImg +  separator + fileName);
                        item.write(uploadFile);
                        mdto.setImage_link(String.valueOf(uploadFile));
                        
                        menuMap.put(item.getFieldName(), mdto);
                        
                    }
                }
            }
            
            for (FileItem item : items)
            {
                if (item.isFormField())
                {
                	if(item.getFieldName().length() > 4 && item.getFieldName().substring(0, 4).equals("file"))
                	{
	                   	if(menuMap.keySet().contains(item.getFieldName().substring(0, item.getFieldName().length()-1)))
	                	{
	                		/*if (item.getFieldName().substring(item.getFieldName().length()-1).equals("m"))
	                		{
	                			if (item.getString(CHARSET) != null)
	                				menuMap.get(item.getFieldName().substring(0, 4)).setMenu_name(item.getString(CHARSET));
	                		}
	                			 
	                		else if (item.getFieldName().substring(item.getFieldName().length()-1).equals("p"))
	                		{
	                			if (item.getString(CHARSET) != null)
	                				menuMap.get(item.getFieldName().substring(0, 4)).setPrice(Integer.parseInt(item.getString(CHARSET)));
	                		}*/
	                		
	                		if (item.getFieldName().endsWith("m"))
	                		{
	                		    String fieldNamePrefix = item.getFieldName().substring(0, item.getFieldName().length() - 1);

	                		    if (item.getString(CHARSET) != null) {
	                		        String menuName = item.getString(CHARSET);
	                		        if (menuMap.containsKey(fieldNamePrefix)) {
	                		            menuMap.get(fieldNamePrefix).setMenu_name(menuName);
	                		        }
	                		    }
	                		}
	                		else if (item.getFieldName().endsWith("p"))
	                		{
	                		    String fieldNamePrefix = item.getFieldName().substring(0, item.getFieldName().length() - 1);

	                		    if (item.getString(CHARSET) != null) {
	                		        String priceStr = item.getString(CHARSET);
	                		        try {
	                		            int price = Integer.parseInt(priceStr);
	                		            if (menuMap.containsKey(fieldNamePrefix)) {
	                		                menuMap.get(fieldNamePrefix).setPrice(price);
	                		            }
	                		        } catch (NumberFormatException e) {
	                		            // 예외 처리: 숫자로 변환할 수 없는 경우
	                		        }
	                		    }
	                		}
	                			
	                	}
	                   	else
	                   	{
	                   		StoreMenuDTO menu = new StoreMenuDTO();
	                   		
	                   		if (item.getFieldName().substring(item.getFieldName().length()-1).equals("m"))
	                			menu.setMenu_name(item.getString(CHARSET)); 
	                		else if (item.getFieldName().substring(item.getFieldName().length()-1).equals("p"))
	                			menu.setPrice(Integer.parseInt(item.getString(CHARSET)));
	                   		
	                   		//menuMap.put(item.getFieldName().substring(0, item.getFieldName().length()-1), value)
	                   	}
                	}
                   	else if(item.getFieldName().substring(0, item.getFieldName().length()-1).equals("openTime"))
                   	{
                   		boolean flag = true;
                   		if (!item.getString(CHARSET).equals("nocheck"))
                   		{
                   			for (StoreOpencloseDTO socdto : soclist)
							{
								if (socdto.getDay_num() == Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)))
								{
									socdto.setOpen_time(item.getString(CHARSET));
									flag = false;
								}
							}
                   			
                   			if (flag)
                   			{
                   				StoreOpencloseDTO socdto = new StoreOpencloseDTO();
                   				
	                   			socdto.setDay_num(Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)));
	                       		socdto.setOpen_time(item.getString(CHARSET));
	                       		soclist.add(socdto);
                   			}
                   		}
                   		
                   	}
                   	else if(item.getFieldName().substring(0, item.getFieldName().length()-1).equals("closeTime"))
                   	{
                   		boolean flag = true;
                   		if (!item.getString(CHARSET).equals("nocheck"))
                   		{
                   			for (StoreOpencloseDTO socdto : soclist)
							{
								if (socdto.getDay_num() == Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)))
								{
									socdto.setClose_time(item.getString(CHARSET));
									flag = false;
								}
							}
                   			
                   			if (flag)
                   			{
                   				StoreOpencloseDTO socdto = new StoreOpencloseDTO();
                   				
                   				socdto.setDay_num(Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)));
                           		socdto.setClose_time(item.getString(CHARSET));
                           		soclist.add(socdto);
                   			}
                   			
                   		}	
                   		
                   	}
                   	else if(item.getFieldName().substring(0, item.getFieldName().length()-1).equals("rest"))
                   	{
                   		int dayNum = Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1));
                   		
                   		smdao.holidayinsert(dayNum, st_num);
                   	}
                   	else if(item.getFieldName().substring(0, item.getFieldName().length()-1).equals("breakOT"))
                   	{
                   		boolean flag = true;
                   		if (!item.getString(CHARSET).equals("nocheck"))
                   		{
                   			for (StoreBreaktimeDTO bocdto : boclist)
							{
								if (bocdto.getWeek_weekend_num() == Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)))
								{
									bocdto.setStart_breaktime(item.getString(CHARSET));
									flag = false;
								}
							}
                   			
                   			if (flag)
                   			{
                   				StoreBreaktimeDTO bocdto = new StoreBreaktimeDTO();
                   				
	                   			bocdto.setWeek_weekend_num(Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)));
	                       		bocdto.setStart_breaktime(item.getString(CHARSET));
	                       		boclist.add(bocdto);
                   			}
                   		}
                   	}
                   	else if(item.getFieldName().substring(0, item.getFieldName().length()-1).equals("breakCT"))
                   	{
                   		boolean flag = true;
                   		if (!item.getString(CHARSET).equals("nocheck"))
                   		{
                   			for (StoreBreaktimeDTO bocdto : boclist)
							{
								if (bocdto.getWeek_weekend_num() == Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)))
								{
									bocdto.setEnd_breaktime(item.getString(CHARSET));
									flag = false;
								}
							}
                   			
                   			if (flag)
                   			{
                   				StoreBreaktimeDTO bocdto = new StoreBreaktimeDTO();
                   				
                   				bocdto.setWeek_weekend_num(Integer.parseInt(item.getFieldName().substring(item.getFieldName().length()-1)));
                           		bocdto.setEnd_breaktime(item.getString(CHARSET));
                           		boclist.add(bocdto);
                   			}
                   			
                   		}
                   	}
                   	else if(item.getFieldName().equals("pays"))
                   	{
                   		for (String pay : item.getString(CHARSET).split(","))
                   		{
                   			System.out.println(pay);
                   			
                   			smdao.paysinsert(Integer.parseInt(pay), st_num);
                   		}
                   	}
                   	else if(item.getFieldName().equals("chBoxO"))
                   	{
                   		System.out.println(item.getString(CHARSET));
                   		for (String chb : item.getString(CHARSET).split(","))
                   		{
                   			System.out.println(chb);
                   			if (!chb.equals(null))
                   			{
                   				try
                   				{
                   					int chbNum = Integer.parseInt(chb);
                           			
                           			if (smdao.chBoxselect(st_num, chbNum) == 0)
                           				smdao.chBoxinsert(st_num, chbNum, 1);
                           			else
                           			{
                           				smdao.chBoxupdate(1, st_num, chbNum);
                           			}
                		        }
                   				catch (NumberFormatException e)
                   				{
                		            // 예외 처리: 숫자로 변환할 수 없는 경우
                		        }
                   				
                   			}
                   			
                   		}
                   	}
                   	else if(item.getFieldName().equals("chBoxX"))
                   	{
                   		for (String chb : item.getString(CHARSET).split(","))
                   		{
                   			System.out.println(chb);
                   			if (!chb.equals(null))
                   			{
                   				try
                   				{
                   					int chbNum = Integer.parseInt(chb);
                           			
                           			if (smdao.chBoxselect(st_num, chbNum) == 0)
                           				smdao.chBoxinsert(st_num, chbNum, 2);
                           			else
                           			{
                           				smdao.chBoxupdate(2, st_num, chbNum);
                           			} 
                		        }
                   				catch (NumberFormatException e)
                   				{
                		            // 예외 처리: 숫자로 변환할 수 없는 경우
                		        }
                   				
                   			}
                   			                			
                   		}
                   	}
                   /*	else if(item.getFieldName().equals("uncheck"))
                   	{
                   		for (String chb : item.getString(CHARSET).split(","))
                   		{
                   			System.out.println(chb);
                   			
                   			int chbNum = Integer.parseInt(chb);
                   			
                   			if (smdao.chBoxselect(st_num, chbNum) != 0)
                   				smdao.chBoxupdate(-1, st_num, chbNum);
                   			
                   		}
                   	}*/
                   	else if(item.getFieldName().equals("stKeys"))
                   	{
                   		for (String stk : item.getString(CHARSET).split(","))
                   		{
                   			System.out.println(stk);
                   			
                   			smdao.stKeysinsert(st_num, Integer.parseInt(stk));
                   		}
                   	}
                   	else if(item.getFieldName().equals("foodcats"))
                   	{
                   		for (String fc : item.getString(CHARSET).split(","))
                   		{
                   			System.out.println(fc);
                   			
                   			smdao.foodCatinsert(Integer.parseInt(fc), st_num);
                   		}
                   	}
                   	else if(item.getFieldName().equals("st_email"))
                   	{
                   		sdto.setSt_email(item.getString(CHARSET));
                   	}
                   	else if(item.getFieldName().equals("st_explain"))
                   	{
                   		sdto.setSt_explain(item.getString(CHARSET));
                   	}
                   	else if(item.getFieldName().equals("searchKey"))
                   	{
                   		if (item.getString(CHARSET).length() > 0)
                   		{
	                   		String searchkey = item.getString(CHARSET);
	                   		Integer searchNum = smdao.searchKeyselect(st_num, searchkey);
	                   		
	                   		if (searchNum == null)
	                   		{
	                   			smdao.searchKeyinsert(st_num, searchkey);
	                   			searchNum = smdao.searchKeyselect(st_num, searchkey);
	                   			smdao.stsearchKeyinsert(st_num, searchNum);
	                   		}
	                   		else
	                   		{
	                   			// 검색된 값이 있는 경우 처리
	                   		    int search_num = searchNum.intValue(); // Integer를 int로 변환
	                   		    
	                   			if (smdao.stsearchKeyselect(st_num, searchNum) == 0)
	                   			{
	                   				smdao.stsearchKeyUpdate(st_num, searchkey);
	                   				smdao.stsearchKeyinsert(st_num, searchNum);
	                   			}
	                   		}
                   		}
                   	}
                }
            }
            
			/*
			 * menuMap = new HashMap<String, StoreMenuDTO>(); ArrayList<StoreOpencloseDTO>
			 * soclist = new ArrayList<StoreOpencloseDTO>(); ArrayList<StoreBreaktimeDTO>
			 * boclist = new ArrayList<StoreBreaktimeDTO>();
			 */
			
            for (String key : menuMap.keySet()) 
            {
                String menuName = menuMap.get(key).getMenu_name();
                String image = menuMap.get(key).getImage_link();
                int price = menuMap.get(key).getPrice();
                
                smdao.menuinsert(st_num, menuName, price, image);
            }
            
            for (StoreOpencloseDTO socdto : soclist)
			{
            	int dayNum = socdto.getDay_num();
            	String openTime = socdto.getOpen_time();
            	String closeTime = socdto.getClose_time();
            	
            	smdao.stOCinsert(st_num, dayNum, openTime, closeTime);
				
			}
            
            for (StoreBreaktimeDTO sbdto : boclist)
			{
				int wNum = sbdto.getWeek_weekend_num();
				String startTime = sbdto.getStart_breaktime();
				String endTime = sbdto.getEnd_breaktime();
				
				smdao.bOCinsert(st_num, wNum, startTime, endTime);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return "redirect:storemain.action";
	}
	
	@RequestMapping(value="/StAppealRequest.action", method=RequestMethod.GET)
	public String StAppealRequest(HttpServletRequest request, Model model)
	{
	    HttpSession session = request.getSession();
	    String user_num = (String)session.getAttribute("user_num");
	    
	    LocalDate currentDate = LocalDate.now();
        int monthValue = currentDate.getMonthValue();
	    
	    String actionName = "/StAppealRequest.action";
	    
	    String result = null;
	    
	    
	    
	    
	    IStoreMainDAO smdao = sqlSession.getMapper(IStoreMainDAO.class);
	    IUserstupdaterelistDAO dao = sqlSession.getMapper(IUserstupdaterelistDAO.class);
	    IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
	    
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
	    
		/*
		 * System.out.println(actionName);
		 * 
		 * System.out.println(Paging.pageIndexList(currentPage, totalPage, actionName));
		 */
	    
	    int st_num = (int)session.getAttribute("st_num");
	    model.addAttribute("pageIndex", Paging.pageIndexList(currentPage, totalPage, actionName));
	    List<UserStupdaterelistDTO> user_stupdate_relist = dao.user_stupdate_relist(user_num, startRow, endRow);
	    System.out.println(user_stupdate_relist);
	    model.addAttribute("user", user);
	    model.addAttribute("StAppealRequest", smdao.StAppealRequest(st_num));
	    
	    result = "/WEB-INF/view/St_Appeal_Request.jsp";
	    
	    return result;
	}
	
	@RequestMapping(value="/StPenaltyre.action", method=RequestMethod.GET)
	public String StPenaltyre(HttpServletRequest request, Model model)
	{
	    HttpSession session = request.getSession();
	    String user_num = (String)session.getAttribute("user_num");
	    
	    LocalDate currentDate = LocalDate.now();
        int monthValue = currentDate.getMonthValue();
	    
	    String actionName = "/StPenaltyre.action";
	    
	    String result = null;
	    
	    
	    
	    
	    IStoreMainDAO smdao = sqlSession.getMapper(IStoreMainDAO.class);
	    IUserstupdaterelistDAO dao = sqlSession.getMapper(IUserstupdaterelistDAO.class);
	    IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
	    
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
	    
		/*
		 * System.out.println(actionName);
		 * 
		 * System.out.println(Paging.pageIndexList(currentPage, totalPage, actionName));
		 */
	    
	    int st_num = (int)session.getAttribute("st_num");
	    model.addAttribute("pageIndex", Paging.pageIndexList(currentPage, totalPage, actionName));
	    List<UserStupdaterelistDTO> user_stupdate_relist = dao.user_stupdate_relist(user_num, startRow, endRow);
	    System.out.println(user_stupdate_relist);
	    model.addAttribute("user", user);
	    model.addAttribute("StPenaltyre", smdao.StPenaltyre(st_num));
	    
	    result = "/WEB-INF/view/St_Penaltyre.jsp";
	    
	    return result;
	}
	
  
}
