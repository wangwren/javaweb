package it.cast.service.impl;

import java.util.List;

import it.cast.dao.impl.CustomerDaoImpl;
import it.cast.domain.Customer;
import it.cast.domain.Page;

public class BusinessService {


	CustomerDaoImpl dao = new CustomerDaoImpl();
	
	public void addCustomer(Customer customer){
		
		dao.add(customer);
	}
	
	public List<Customer> getAllCustomer(){
		return dao.getAll();
	}

	
	//实现分页,参数代表用户想看的页
	public Page getPageData(String pagenum,String url){
		
		int totalRecord = dao.getTotalrecord();
		
		if(pagenum == null){
			//代表用户想看第一页数据
			Page page = new Page(totalRecord, 1);
			List list = dao.getPageData(page.getStartindex(), page.getPageSize());
			page.setList(list);
			page.setUrl(url);
			return page;
		}else{
			//代表用户想看指定页数据
			Page page = new Page(totalRecord, Integer.parseInt(pagenum));
			List list = dao.getPageData(page.getStartindex(), page.getPageSize());
			page.setList(list);
			page.setUrl(url);
			return page;
		}
	}

	public Customer findCustomer(String id) {
		
		Customer c = dao.find(id);
		
		return c;
	}

	public void updateCustomer(Customer customer) {

		dao.update(customer);
		
	}

	public void deleteCustomer(String id) {
		
		dao.delete(id);
		
	}
}
