package br.com.gilmariosoftware.status;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gilmario
 */
@RestController
public class StatusResource {

    @RequestMapping(value = "/status")
    public Status index() {
        return new Status();
    }

}
