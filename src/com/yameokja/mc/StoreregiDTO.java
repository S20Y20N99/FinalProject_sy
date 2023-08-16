package com.yameokja.mc;


public class StoreregiDTO
{
	private String user_num, st_name, st_location, st_location_dt, st_tel, admin_read_date, reg_date;
	
	private long st_in_num, st_place_num;
	
	private String st_in_file, st_place_file;
	
	private String in_file_name, place_file_name;
	
	
	// getter / setter
	public String getUser_num()
	{
		return user_num;
	}
	public void setUser_num(String user_num)
	{
		this.user_num = user_num;
	}
	public String getSt_in_file()
	{
		return st_in_file;
	}
	public void setSt_in_file(String st_in_file)
	{
		this.st_in_file = st_in_file;
	}
	public String getSt_place_file()
	{
		return st_place_file;
	}
	public void setSt_place_file(String st_place_file)
	{
		this.st_place_file = st_place_file;
	}
	public String getSt_name()
	{
		return st_name;
	}
	public void setSt_name(String st_name)
	{
		this.st_name = st_name;
	}
	public String getSt_location()
	{
		return st_location;
	}
	public void setSt_location(String st_location)
	{
		this.st_location = st_location;
	}
	public String getSt_location_dt()
	{
		return st_location_dt;
	}
	public void setSt_location_dt(String st_location_dt)
	{
		this.st_location_dt = st_location_dt;
	}
	public String getSt_tel()
	{
		return st_tel;
	}
	public void setSt_tel(String st_tel)
	{
		this.st_tel = st_tel;
	}
	public String getAdmin_read_date()
	{
		return admin_read_date;
	}
	public void setAdmin_read_date(String admin_read_date)
	{
		this.admin_read_date = admin_read_date;
	}
	public String getReg_date()
	{
		return reg_date;
	}
	public void setReg_date(String reg_date)
	{
		this.reg_date = reg_date;
	}
	public long getSt_in_num()
	{
		return st_in_num;
	}
	public void setSt_in_num(long st_in_num)
	{
		this.st_in_num = st_in_num;
	}
	public long getSt_place_num()
	{
		return st_place_num;
	}
	public void setSt_place_num(long st_place_num)
	{
		this.st_place_num = st_place_num;
	}
	
	public String getIn_file_name()
	{
		return in_file_name;
	}
	public void setIn_file_name(String in_file_name)
	{
		this.in_file_name = in_file_name;
	}
	
	public String getPlace_file_name()
	{
		return place_file_name;
	}
	public void setPlace_file_name(String place_file_name)
	{
		this.place_file_name = place_file_name;
	}
	@Override
	public String toString()
	{
		
		return "st_num = " + st_in_num + "st_place_num = " + st_place_num + "st_in_file = " + st_in_file + "," + st_tel + "," + st_location + ",";
	}

	
	
}
