package it.cast.domain;

import java.util.List;

//��װ��ҳҳ������ݣ������ͻ����ݺ�ҳ��
public class Page {
	
	private List<Customer> list;        //��װҳ��ͻ�����
	
	private int totalPage;             //��¼��ҳ��
	
	private int totalRecord;           //��¼�ܼ�¼��,���Դ�dao���ȡ��
	
	private int pageSize = 10;         //ҳ���С,Ĭ��һҳ��ʾʮ������
	
	private int pagenum;    		   //�����û��뿴��ҳ
	
	private int startindex;            //�����û��뿴��ҳ�����ݴ����ݿ���ĸ��ط���ʼ
	
	private int startpage;				//��ʼҳ
	private int endpage;				//һ���е����һҳ
	
	private String url;					//��ס���ڴ����ҳ��servlet
	



	public Page(int totalRecord,int pagenum){
		
		//���ù��캯���ֱ���� ��ҳ�� �� �û��뿴��ҳ�����ݴ����ݿ���ĸ��ط���ʼ
		
		this.totalRecord = totalRecord;
		if(this.totalRecord % this.pageSize == 0){
			this.totalPage = this.totalRecord / this.pageSize;
		}else{
			this.totalPage = this.totalRecord / this.pageSize + 1;
		}
		
		this.pagenum = pagenum;
		this.startindex = (this.pagenum - 1) * this.pageSize;
		
		//ͨ���û��뿴��ҳpagenum������startpage��endpage
		if(this.totalPage <= 10){
			
			this.startpage = 1;
			this.endpage = this.totalPage;
		}else{
			this.startpage = this.pagenum - 4;
			this.endpage = this.pagenum + 5;
		}
		
		//�����ҳ��Ϊ20 �û��뿴��3ҳ
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
