package hanriver.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hanriver.domain.Park;
import hanriver.service.ParkService;

@RestController
@RequestMapping("/park")
public class ParkController {
    
	@Autowired ParkService parkService;

	@RequestMapping("form")
	public String form() {
		return "park/form";
	}
	
	@RequestMapping("add")
	public String add(Park park) throws Exception {
		parkService.add(park);
		return "redirect:list";
		
	}
	
	@RequestMapping("list")
	public Object list(
			@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="1")int size
			) throws Exception {
		if (page < 1) page = 1;
        if (size < 1 || size > 20) size = 10;
		HashMap<String, Object> data = new HashMap<>();
		data.put("list", parkService.list());
		data.put("size",size);
		data.put("page",page);
		data.put("totalPage", parkService.countAll(size));
		return data;
	}
	
	
	@RequestMapping("view/{name}")
	public Object view(@PathVariable String name) throws Exception {
		HashMap<String, Object> data = new HashMap<>();
//		data.put("park", parkService.get(name));

		Park park = parkService.get(name);
		data.put("park", park);
		
		return data;
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
