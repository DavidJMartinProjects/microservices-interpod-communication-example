package app.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/hello-service")
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() {
        log.info("received GET request to /hello-service");
        return "hello-service is: online. ";
    }

    @GetMapping("/ping")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getPing() {
        log.info("received GET request to /hello-service");
        return "inter pod communication success: This response is from hello-service";
    }

    @GetMapping(("/hello"))
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getHelloServiceMessage() {
        log.info("received GET request to: /hello-service/hello");
        return restTemplate.getForObject("http://goodbye-service:9091/goodbye-service/ping", String.class);
    }
}
