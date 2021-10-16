package backend.deliveryapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroControllador {

  @GetMapping("/hello") @ResponseBody
  public String hello() {
    return "hello!";
  }

}
