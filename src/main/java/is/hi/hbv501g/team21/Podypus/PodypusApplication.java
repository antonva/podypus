package is.hi.hbv501g.team21.Podypus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PodypusApplication {

	public static void main(String[] args) {

		SpringApplication.run(PodypusApplication.class, args);
	}

}
