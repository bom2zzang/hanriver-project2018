package hanriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hanriver.domain.Park;
import hanriver.service.ParkService;

@Controller
@RequestMapping("/park")
public class ParkController {
    
	@Autowired ParkService parkService;

	@RequestMapping("list")
	public String list(Model model) throws Exception {
		
		model.addAttribute("list", parkService.list());
		return "park/list";
	}
	
	@RequestMapping("form")
	public String form() {
		return "park/form";
	}
	
	@RequestMapping("add")
	public String add(Park park) throws Exception {
		parkService.add(park);
		return "redirect:list";
		
	}
	
	@RequestMapping("view/{name}")
	public String view(@PathVariable String name, Model model) throws Exception {
		
		Park park = parkService.get(name);
		model.addAttribute("park", park);
		return "park/view";
	}
	
	@RequestMapping("update")
	public String update(Park park) throws Exception {
		
		if (parkService.update(park) == 0) {
			return "park/updatefail";
		} else {
			return "redirect:list";
		}
	}
	
	@RequestMapping("delete")
	public String delete(String name) throws Exception {
		
		parkService.delete(name);
		return "redirect:list";
	}
	
	
	
	
}
