package vn.datapower.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author canhthieu
 */
@RestController
public class DataAPI {

    private static final Logger logger = Logger.getLogger(DataAPI.class.getName());
    @Autowired
    private ResourceLoader resourceLoader;

    public Resource loadEmployeesWithResourceLoader() {
        return resourceLoader.getResource(
                "classpath:analytics.csv");
    }

    @CrossOrigin
    @GetMapping("/data/v1.0/analytic")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        logger.info("api call, uri=/data/v1.0/analytic");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=analytics.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(loadEmployeesWithResourceLoader());

    }
}
