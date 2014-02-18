package hzk.springmvc.demo3.dao;

import java.util.List;

import hzk.springmvc.demo3.model.Shop;

public interface ShopDao extends GenericDao<Shop, Integer>{

	public List<Shop> getAllByBrand(int brandId);
	
}
