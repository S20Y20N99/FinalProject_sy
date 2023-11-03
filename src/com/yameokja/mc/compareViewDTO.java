package com.yameokja.mc;

public class compareViewDTO 
{	
	// 가게
	private String st_name; 	// 가게 이름
					
	private String food_name;	// 가게 카테고리
	
	private String st_location, photo_link;  // 주소, 가게 사진
	
	private double star_score, price_avg;   // 별점 평균
	
	private int review_count, max_customers; 	 // 리뷰 수, 최대 수용인원
	
	private String day_name, start_time, end_time; // 영업 시간 가져오는 DTO
	
	private int st_num, day_num;				   // 가게 번호, 요일 번호
	
	private String menu_name, image_link; // 가게 메뉴, 이미지 링크
	
	private int price, menu_num;					// 가게 메뉴 가격, 번호
	
	//getter /setter

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getSt_location() {
		return st_location;
	}

	public void setSt_location(String st_location) {
		this.st_location = st_location;
	}

	public String getPhoto_link() {
		return photo_link;
	}

	public void setPhoto_link(String photo_link) {
		this.photo_link = photo_link;
	}

	public double getStar_score() {
		return star_score;
	}

	public void setStar_score(double star_score) {
		this.star_score = star_score;
	}

	public double getPrice_avg() {
		return price_avg;
	}

	public void setPrice_avg(double price_avg) {
		this.price_avg = price_avg;
	}

	public int getReview_count() {
		return review_count;
	}

	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	public int getMax_customers() {
		return max_customers;
	}

	public void setMax_customers(int max_customers) {
		this.max_customers = max_customers;
	}

	public String getDay_name() {
		return day_name;
	}

	public void setDay_name(String day_name) {
		this.day_name = day_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getSt_num() {
		return st_num;
	}

	public void setSt_num(int st_num) {
		this.st_num = st_num;
	}

	public int getDay_num() {
		return day_num;
	}

	public void setDay_num(int day_num) {
		this.day_num = day_num;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMenu_num() {
		return menu_num;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}
	
	
		
	
	
}
