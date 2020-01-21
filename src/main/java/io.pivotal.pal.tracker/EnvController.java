package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    String port ;
    String memLimit;
    String instanceIndex;
    String address;
    Map<String, String> env;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}")String memLimit,
                         @Value("${cf.instance.index:NOT SET}")String instanceIndex,
                         @Value("${cf.instance.addr:NOT SET}")String address ){
        this.port=port;
        this.instanceIndex=instanceIndex;
        this.memLimit=memLimit;
        this.address=address;

        this.env = new HashMap<String, String>();
        this.env.put("PORT",port);
        this.env.put("MEMORY_LIMIT",memLimit);
        this.env.put("CF_INSTANCE_INDEX",instanceIndex);
        this. env.put("CF_INSTANCE_ADDR",address);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        return this.env;
    }
}
