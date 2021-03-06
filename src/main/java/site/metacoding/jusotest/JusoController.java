package site.metacoding.jusotest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JusoController {

    @GetMapping("/")
    public String home(Model model) {
        String jusoUrl = "https://www.juso.go.kr/addrlink/addrLinkUrl.do?confmKey=devU01TX0FVVEgyMDIyMDUyMzEwNDgzNTExMjYwMDA=&returnUrl=http://localhost:8080/juso/callback&resultType=4";
        model.addAttribute("jusoUrl", jusoUrl);
        return "/home";
    }

    // 해당 콜백이 호출되면 push 해주는 것이 좋다. 스트림이 연결되어 있어야 한다.(ex. 웹소켓, SSE 프로토콜)
    @PostMapping("/juso/callback")
    public void jusoCallback(String roadFullAddr) {
        System.out.println(roadFullAddr);
        Store.roadFullAddr = roadFullAddr;
    }

    @GetMapping("/juso/check")
    public @ResponseBody String jusoCheck() {
        return Store.roadFullAddr;
    }

}
