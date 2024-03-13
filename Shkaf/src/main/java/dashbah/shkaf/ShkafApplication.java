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
		Environment env = app.run(args).getEnvironment();
		System.out.println("Application is running on port: " + env.getProperty("server.port"));
	}
}
