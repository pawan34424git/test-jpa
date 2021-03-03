package com.pawan.TestJpa;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import com.pawan.TestJpa.constants.AppConstants;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TestJpaApplication {

	@Autowired
	private Environment env;

	@PostConstruct
	public void initApplication() {
		if (env.getActiveProfiles().length == 0) {
			log.warn("No Spring profile configured, running with default configuration");
		} else {
			log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
			Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
			if (activeProfiles.contains(AppConstants.SPRING_PROFILE_DEVELOPMENT)
					&& activeProfiles.contains(AppConstants.SPRING_PROFILE_PRODUCTION)) {
				log.error("You have misconfigured your application! "
						+ "It should not run with both the 'dev' and 'prod' profiles at the same time.");
			}
		}
	}

	public static void main(String[] args) {
		 	SpringApplication app = new SpringApplication(TestJpaApplication.class);
	        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
	        addDefaultProfile(app, source);
	        Environment env = app.run(args).getEnvironment();
	        log.info("\n----------------------------------------------------------\n\t" +
	                "Application '{}' is running! Access URLs:\n\t" +
	                "Local: \t\thttp://127.0.0.1:{}\n\t",
	            env.getProperty("spring.application.name"),
	            env.getProperty("server.port"));
	}

	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")
				&& !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

			app.setAdditionalProfiles(AppConstants.SPRING_PROFILE_DEVELOPMENT);
		}
	}

}
