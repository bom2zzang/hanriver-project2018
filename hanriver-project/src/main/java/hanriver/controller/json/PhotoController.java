package hanriver.controller.json;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hanriver.domain.Photo;
import hanriver.service.PhotoService;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    
    @Autowired PhotoService photoService;
    
    @PostMapping("add")
    public Object add(HttpSession session, Photo photo) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");
        photoService.add(photo);
        return result;
    }
    
    
    @PostMapping("delete")
    public Object delete(HttpSession session, int no) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        //Member loginUser = (Member)session.getAttribute("loginUser");
        //int mno = loginUser.getNo();
        int mno = 1;
        if (photoService.delete(no, mno) == 0) {
            result.put("status", "fail");
            result.put("error", "해당하는 번호가 없습니다.");
        } else {
            result.put("status", "success");
        }
        return result;
    }
    
    @GetMapping("list")
    public Object list() throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("list", photoService.list());
        return data;
    }
}
