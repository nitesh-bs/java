package com.nitesh.springWebFluxVideoStreaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@RestController
public class SpringWebFluxVideoStreamingApplication {

	@Autowired
	private StreamingService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxVideoStreamingApplication.class, args);
	}
	
	@GetMapping(value = "video/{title}",produces = "video/mp4")
	public Mono<Resource> getVideos(@PathVariable String title,@RequestHeader("Range") String range){
		System.out.println("range in bytles : "+range);
		return service.getVideo(title); 
	}

	@Bean
	public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/static/index.html") final Resource indexHtml) {
	return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
	}
}
