package hzk.springmvc.demo3.service;

import java.util.List;

import hzk.springmvc.demo3.dao.BrandDao;
import hzk.springmvc.demo3.dao.ShopDao;
import hzk.springmvc.demo3.model.Brand;
import hzk.springmvc.demo3.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

	  @Autowired
	  private BrandDao brandDao;
	  @Autowired
	  private ShopDao shopDao;
	
	  public Brand getBrand(int id){
		  return brandDao.getById(id);
	  }	  
	  
	  public List<Brand> getBrands(){
		  return brandDao.getAll();
	  }
	  
	  public Shop getShop(int id){
		  return shopDao.getById(id);
	  }
	  
	  public List<Shop> getShops(){
		  return shopDao.getAll();
	  }
	  
	  
	  
	  public List<Shop> getShopsByBrand(int brandId){
		  return shopDao.getAllByBrand(brandId);
	  }
}
