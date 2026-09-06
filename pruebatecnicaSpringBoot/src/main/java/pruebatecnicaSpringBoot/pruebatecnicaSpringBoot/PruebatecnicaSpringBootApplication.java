package pruebatecnicaSpringBoot.pruebatecnicaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.Security;

@SpringBootApplication
public class PruebatecnicaSpringBootApplication {

	public static void main(String[] args) {
		// Habilita TLS 1.0/1.1 para compatibilidad con SQL Server 2014 (solo desarrollo local)
		Security.setProperty("jdk.tls.disabledAlgorithms",
				"SSLv3, RC4, DES, MD5withRSA, DH keySize < 1024, EC keySize < 224, 3DES_EDE_CBC, anon, NULL");
		System.setProperty("jdk.tls.client.protocols", "TLSv1,TLSv1.1,TLSv1.2,TLSv1.3");

		SpringApplication.run(PruebatecnicaSpringBootApplication.class, args);
	}
}