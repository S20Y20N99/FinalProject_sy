package com.yameokja.mc;

public class ReviewDTO
{
	private int rv_num, st_num, star_score;
	private String user_nickname, rv_content, reg_date;
	private String st_name, reply_content;
	
	
	
	public int getRv_num()
	{
		return rv_num;
	}
	public void setRv_num(int rv_num)
	{
		this.rv_num = rv_num;
	}
	
	public int getSt_num()
	{
		return st_num;
	}
	public void setSt_num(int st_num)
	{
		this.st_num = st_num;
	}
	
	public int getStar_score()
	{
		return star_score;
	}
	public void setStar_score(int star_score)
	{
		this.star_score = star_score;
	}
	
	public String getUser_nickname()
	{
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
	}
	
	public String getRv_content()
	{
		return rv_content;
	}
	public void setRv_content(String rv_content)
	{
		this.rv_content = rv_content;
	}
	
	public String getReg_date()
	{
		return reg_date;
	}
	public void setReg_date(String reg_date)
	{
		this.reg_date = reg_date;
	}
	
	public String getSt_name()
	{
		return st_name;
	}
	public void setSt_name(String st_name)
	{
		this.st_name = st_name;
	}
	public String getReply_content()
	{
		return reply_content;
	}
	public void setReply_content(String reply_content)
	{
		this.reply_content = reply_content;
	}
	
	
}
