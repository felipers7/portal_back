package com.elitsoft.proyectoCuestionario_backend;

import com.elitsoft.proyectoCuestionario_backend.services.FileService;
import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class ProyectoCuestionarioBackendApplication implements CommandLineRunner{
    @Resource
    FileService fileService;

    @Autowired
    private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCuestionarioBackendApplication.class, args);
	}

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    @Override
    public void run(String... args) throws Exception {

        fileService.deleteall();
        fileService.init();


        }


    @Bean
    public WebMvcConfigurer corsConfigurer (){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings (CorsRegistry registry){
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","PUT","DELETE");
            }
        };


        

    }



}
