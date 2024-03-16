package dashbah.shkaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootApplication
public class ShkafApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ShkafApplication.class);
		ConfigurableApplicationContext context = app.run(args);
		System.out.println("Application is running on port: " + context.getEnvironment().getProperty("server.port"));
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		System.out.println(encoder.encode("pass"));
	}
}
