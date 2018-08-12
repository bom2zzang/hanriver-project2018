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
			@RequestParam(defaultValue="10")int size
			) throws Exception {
		if (page < 1) page = 1;
        if (size < 1 || size > 20) size = 10;
		HashMap<String, Object> data = new HashMap<>();
		data.put("list", parkService.list(page, size));
		data.put("size",size);
		data.put("page",page);
		data.put("totalPage", parkService.countAll(size));
		return data;
	}
	
	
	@RequestMapping("view/{no}")
	public Object view(@PathVariable String no) throws Exception {
		HashMap<String, Object> data = new HashMap<>();
//		data.put("park", parkService.get(name));

		Park park = parkService.get(no);
		data.put("park", park);
		
		return data;
	}
	
	@RequestMapping("update")
	public Object update(Park park) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		if (parkService.update(park) == 0) {
			result.put("status", "fail");
			result.put("error", "해당공원번호가 없습니다.");
		} else {
			result.put("status", "success");
		}
		return result;
	}
	
	@RequestMapping("delete")
	public Object delete(String name) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		if (parkService.delete(name) == 0) {
			result.put("status", "fail");
			result.put("error", "삭제할 공원이 없습니다.");
		} else {
			result.put("status", "success");
		}
		
		return result;
	}
	
	
	
	
}
