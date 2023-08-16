package com.yameokja.mc;


import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreregiController
{
	@Autowired 
	private SqlSession sqlSession;
	
	@Autowired
    private ServletContext servletContext;
	
	@RequestMapping(value = "/storegiinsertform.action", method=RequestMethod.POST)
	public String SoreregiInsertForm(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String result = null;
		
		String user_num = (String)session.getAttribute("user_num");
		
		IUserDAO udao = sqlSession.getMapper(IUserDAO.class);
		
		LocalDate currentDate = LocalDate.now();
        int monthValue = currentDate.getMonthValue();
		
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
		
		model.addAttribute("user", user);
		result = "WEB-INF/view/Store_Regi.jsp";
		
		return result;
	}
	
	@RequestMapping(value = "/storeregiinsert.action", method=RequestMethod.POST)
	public String StoreregiInsert(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		String rootPath = servletContext.getRealPath("/");
		String CHARSET = "utf-8";
		int LIMIT_SIZE_BYTES = 5 * 1024 * 1024;
		
		StoreregiDTO srdto = new StoreregiDTO();
		
		// 경로상 디렉터리가 존재하지 않으면... 만든다.
		String savePath_stIn = rootPath + File.separator + "Store_In_File";
		File st_in_dir = new File(savePath_stIn);
		if (!st_in_dir.exists())
		{
			st_in_dir.mkdirs();
		}
		
		// 경로상 디렉터리가 존재하지 않으면... 만든다.
		String savePath_stPlace = rootPath + File.separator + "Store_Place_File";
		File st_place_dir = new File(savePath_stPlace);
		if (!st_place_dir.exists())
		{
			st_place_dir.mkdirs();
		}
		
		
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(st_in_dir);
		fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		try 
		{
            List<FileItem> items = fileUpload.parseRequest(request);
            for (FileItem item : items)
            {
                if (!item.isFormField())
                {
                	if (item.getFieldName().equals("st_in_file"))
                	{
	                    if (item.getSize() > 0)
	                    {
	                        String separator = File.separator;
	                        int index =  item.getName().lastIndexOf(separator);
	                        String fileName = item.getName().substring(index  + 1);
	                        File uploadFile = new File(savePath_stIn +  separator + fileName);
	                        item.write(uploadFile);
	                        srdto.setSt_in_file(String.valueOf(uploadFile));
	                        
	                    }
                	}
                	else if (item.getFieldName().equals("st_place_file"))
                	{
	                    if (item.getSize() > 0)
	                    {
	                        String separator = File.separator;
	                        int index =  item.getName().lastIndexOf(separator);
	                        String fileName = item.getName().substring(index  + 1);
	                        File uploadFile = new File(savePath_stPlace +  separator + fileName);
	                        item.write(uploadFile);
	                        srdto.setSt_place_file(String.valueOf(uploadFile));
	                    }
                	}
                }
                else
                {
                	if (item.getFieldName().equals("st_in_num"))
                	{
                		System.out.println(item.getString(CHARSET));
                		int st_in_num = Integer.parseInt(item.getString(CHARSET));
                		srdto.setSt_in_num(st_in_num);
                	}
                	else if (item.getFieldName().equals("st_place_num"))
                	{
                		String tmpStr = item.getString(CHARSET);
                		int st_place_num = Integer.parseInt(tmpStr.substring(0, tmpStr.length()));
                		srdto.setSt_place_num(st_place_num);
                	}
                	else if (item.getFieldName().equals("st_name"))
                		srdto.setSt_name(item.getString(CHARSET));
                	else if (item.getFieldName().equals("st_location"))
                		srdto.setSt_location(item.getString(CHARSET));
                	else if (item.getFieldName().equals("st_location_dt"))
                		srdto.setSt_location_dt(item.getString(CHARSET));
                	else if (item.getFieldName().equals("st_tel"))
                		srdto.setSt_tel(item.getString(CHARSET));
                } 
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		srdto.setUser_num((String)session.getAttribute("user_num"));
		
		IStoreregiDAO dao = sqlSession.getMapper(IStoreregiDAO.class);
		
		dao.St_In_Apply(srdto);
		
		return "redirect:usermain.action";
	}
	
	
}
