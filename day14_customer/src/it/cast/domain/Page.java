package it.cast.domain;

import java.util.List;

//封装分页页面的数据，包括客户数据和页码
public class Page {
	
	private List<Customer> list;        //封装页面客户数据
	
	private int totalPage;             //记录总页数
	
	private int totalRecord;           //记录总记录数,可以从dao层获取到
	
	private int pageSize = 10;         //页面大小,默认一页显示十条数据
	
	private int pagenum;    		   //代表用户想看的页
	
	private int startindex;            //代表用户想看的页的数据从数据库的哪个地方开始
	
	private int startpage;				//起始页
	private int endpage;				//一行中的最后一页
	
	private String url;					//记住用于处理分页的servlet
	



	public Page(int totalRecord,int pagenum){
		
		//利用构造函数分别算出 总页数 和 用户想看的页的数据从数据库的哪个地方开始
		
		this.totalRecord = totalRecord;
		if(this.totalRecord % this.pageSize == 0){
			this.totalPage = this.totalRecord / this.pageSize;
		}else{
			this.totalPage = this.totalRecord / this.pageSize + 1;
		}
		
		this.pagenum = pagenum;
		this.startindex = (this.pagenum - 1) * this.pageSize;
		
		//通过用户想看的页pagenum来计算startpage和endpage
		if(this.totalPage <= 10){
			
			this.startpage = 1;
			this.endpage = this.totalPage;
		}else{
			this.startpage = this.pagenum - 4;
			this.endpage = this.pagenum + 5;
		}
		
		//如果总页数为20 用户想看第3页
		if(this.startpage < 1){
			this.startpage = 1;
			this.endpage = 10;
		}
		if(this.endpage > this.totalPage){
			this.endpage = this.totalPage;
			this.startpage = this.startpage - 9;
		}
	}
	
	public String getUrl() {
		return url;
	}
	
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	public int getStartpage() {
		return startpage;
	}



	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}



	public int getEndpage() {
		return endpage;
	}



	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}



	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
	
	

}
