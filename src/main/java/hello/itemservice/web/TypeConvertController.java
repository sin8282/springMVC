package hello.itemservice.web;

import hello.itemservice.web.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TypeConvertController {

    @ResponseBody
    @GetMapping("type-v1")
    public String typeV1(HttpServletRequest request) {
        String data = request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    @ResponseBody

    @GetMapping("type-v2")
    public String typeV2(@RequestParam Integer data) {
        System.out.println("intValue = " + data);
        return "ok";
    }
    //@RequestParam , @ModelAttribute , @PathVariable 모두 convert 가능

    @ResponseBody
    @GetMapping("ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort Ip = " + ipPort.getIp());
        System.out.println("ipPort Ip = " + ipPort.getPort());
        return "ok";
    }

    @GetMapping("/convert-view")
    public String convertView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1",8080));
        return "convert/convert-view";
    }

    @GetMapping("/convert-edit")
    public String convertForm(Model model){
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);

        model.addAttribute("form", form);
        return "convert/convert-form";
    }

    @PostMapping("/convert-edit")
    public String converterEdit(@ModelAttribute Form form, Model model){
        IpPort ipPort = form.getIpPort();

        model.addAttribute("ipPort", ipPort);
        return "convert/convert-view";
    }

    @Data
    static class Form {
        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }
}
