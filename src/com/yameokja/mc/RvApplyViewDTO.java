package com.yameokja.mc;

public class RvApplyViewDTO
{
	/*
	REP_APPLY_NUM, USER_ID, REG_DATE, ACCU_NUM, WRITE_DATE, REP_RS_NAME, ST_NAME, ST_NUM, RV_CONTENT
	*/
	
	private String reg_date, user_id, accu_num, st_name, write_date, rep_rs_name, rv_content;
	private int rep_apply_num, st_num;
	
	// getter / setter
	public int getSt_num()
	{
		return st_num;
	}

	public String getRv_content()
	{
		return rv_content;
	}

	public void setRv_content(String rv_content)
	{
		this.rv_content = rv_content;
	}

	public void setSt_num(int st_num)
	{
		this.st_num = st_num;
	}
	
	public int getRep_apply_num()
	{
		return rep_apply_num;
	}

	public void setRep_apply_num(int rep_apply_num)
	{
		this.rep_apply_num = rep_apply_num;
	}

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

	public String getAccu_num()
	{
		return accu_num;
	}

	public void setAccu_num(String accu_num)
	{
		this.accu_num = accu_num;
	}

	public String getSt_name()
	{
		return st_name;
	}

	public void setSt_name(String st_name)
	{
		this.st_name = st_name;
	}

	public String getWrite_date()
	{
		return write_date;
	}

	public void setWrite_date(String write_date)
	{
		this.write_date = write_date;
	}

	public String getRep_rs_name()
	{
		return rep_rs_name;
	}

	public void setRep_rs_name(String rep_rs_name)
	{
		this.rep_rs_name = rep_rs_name;
	}
	
	
}
