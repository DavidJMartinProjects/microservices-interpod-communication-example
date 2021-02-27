package app.goodbye;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/goodbye-service")
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getHello() {
        log.info("received GET request to /goodbye-service");
        return "success: Hello from goodbye-service";
    }

    @GetMapping("/ping")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getPing() {
        log.info("received GET request to /goodbye-service");
        return "inter pod communication success: This response is from goodbye-service";
    }

    @GetMapping("/hello")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getHelloServiceMessage() {
        log.info("received GET request to /goodbye-service/hello");
        return restTemplate.getForObject("http://hello-service.default.svc.cluster.local:9092/hello-service/ping", String.class);
    }

}
