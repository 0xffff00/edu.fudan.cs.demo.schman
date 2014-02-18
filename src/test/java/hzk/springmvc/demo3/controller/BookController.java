package hzk.springmvc.demo3.controller;

import hzk.springmvc.demo3.model.Book;
import hzk.springmvc.demo3.service.BookService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Path("books")
public class BookController {

	@Autowired
	BookService service;

	@RequestMapping("h")
	public @ResponseBody
	String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		return "helloWorld";
	}

	@RequestMapping(value = "/q", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody
	Book get1() {
		return service.getByIsbn("");

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("get2")
	public Book get2() {
		return service.getByIsbn("");

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("d")
	public Response d() {

		return Response.ok(new Book()).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Path("isbn/{isbn}")
	public Book get(@PathParam("isbn") String isbn) {
		return service.getByIsbn(isbn);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("all")
	public ModelAndView viewAll() {
		return new ModelAndView("books", "books", service.getAll());

	}

}
