package com.yameokja.mc;

public class ReportListDTO
{
	// 가게정보수정요청리스트
	private String reg_date, st_name, state, final_date, admin_id;
	private String accu_num, cat, user_id;
	private int obj_apply_num;
	
	

	// getter / setter 
	public String getReg_date()
	{
		return reg_date;
	}
	public void setReg_date(String reg_date)
	{
		this.reg_date = reg_date;
	}

	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getSt_name()
	{
		return st_name;
	}
	public void setSt_name(String st_name)
	{
		this.st_name = st_name;
	}

	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}

	public String getFinal_date()
	{
		return final_date;
	}
	public void setFinal_date(String final_date)
	{
		this.final_date = final_date;
	}

	public String getAdmin_id()
	{
		return admin_id;
	}
	public void setAdmin_id(String admin_id)
	{
		this.admin_id = admin_id;
	}
	
	public String getAccu_num()
	{
		return accu_num;
	}
	public void setAccu_num(String accu_num)
	{
		this.accu_num = accu_num;
	}

	public int getObj_apply_num()
	{
		return obj_apply_num;
	}
	public void setObj_apply_num(int obj_apply_num)
	{
		this.obj_apply_num = obj_apply_num;
	}
	
	public String getCat()
	{
		return cat;
	}
	public void setCat(String cat)
	{
		this.cat = cat;
	}
}