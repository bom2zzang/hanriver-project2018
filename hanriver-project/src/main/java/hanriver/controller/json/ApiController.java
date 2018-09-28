package hanriver.controller.json;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @GetMapping("weather")
    public Object doGet(HttpServletRequest request, HttpServletResponse response,
            String date,
            String time,
            String nx,
            String ny) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(
                new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=AMW5V6IKzRS6bslVChrsfXWson6%2FgDspQvrV9%2F5ZgGr97gzyr43o8FtxbNHArOiLznJqPKavjjSMNIVs0lzLYA%3D%3D&base_date="+date+"&base_time="+time+"&nx="+nx+"&ny="+ny+"&numOfRows=10&pageSize=10&pageNo=1&startPage=1&_type=json"),
                new TypeReference<Map<String,Object>>(){});
        List<Object> list = ((List<Object>)((Map)((Map)((Map)data.get("response")).get("body")).get("items")).get("item"));
        HashMap<String, Object> result = new HashMap<String, Object>();
        for (Object map : list) {
            result.put(((Map)map).get("category").toString(), ((Map)map).get("fcstValue"));
        }
        return result;
    }
}






