package hzk.springmvc.demo3.controller;

import java.util.Arrays;
import hzk.springmvc.demo3.service.ShopService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Path("/")
public class Home {

	@Autowired
	ShopService shopService;

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("brands")
	public ModelAndView viewBrands() {
		return new ModelAndView("brands", "brands", shopService.getBrands());

	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("brands/{brandId}")
	public ModelAndView viewBrand(@PathParam("brandId") int brandId) {
		ModelAndView mav=new ModelAndView("brands");
		mav.addObject("brands", Arrays.asList(shopService.getBrand(brandId)));
		return mav;

	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("shops")
	public ModelAndView viewShops() {
		return new ModelAndView("shops", "shops", shopService.getShops());

	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("shops/{shopId}")
	public ModelAndView viewShop(@PathParam("shopId") int shopId) {
		return new ModelAndView("shops", "shops", Arrays.asList(shopService.getShop(shopId)));

	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("brands/{brandId}/shops")
	public ModelAndView viewBrandShops(@PathParam("brandId") int brandId) {
		return new ModelAndView("shops", "shops", shopService.getShopsByBrand(brandId));

	}
}
