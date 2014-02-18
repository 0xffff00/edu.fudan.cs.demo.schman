package hzk.springmvc.demo3.dao;

import hzk.springmvc.demo3.model.Brand;

public interface BrandDao extends GenericDao<Brand, Integer>{
	public Brand getById(int id);
	
	public Brand getByShop(int shopId);
	
 
}
