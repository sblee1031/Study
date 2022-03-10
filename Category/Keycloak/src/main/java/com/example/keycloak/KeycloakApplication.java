package com.example.keycloak;

import java.io.InputStream;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeycloakApplication {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(KeycloakApplication.class, args);
		
//		Keycloak keycloak = Keycloak.getInstance(
//			    "http://localhost:8180",
//			    "master",
//			    "admin",
//			    "admin",
//			    "admin-cli");
//			RealmRepresentation realm = keycloak.realm("master").toRepresentation();
			System.out.println("===============");
//			System.out.println(realm.getId());
//			System.out.println(realm.getAttributes());
//			System.out.println(realm.getRoles());

	}
/*    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
*/	
    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakConfigResolver() {

        	private KeycloakDeployment keycloakDeployment;
            @Override
            public KeycloakDeployment resolve(HttpFacade.Request facade) {
                if (keycloakDeployment != null) {
                    return keycloakDeployment;
                }

                InputStream configInputStream = getClass().getResourceAsStream("/keycloak.json");
                return KeycloakDeploymentBuilder.build(configInputStream);
            }
        };
    }
    
    
}
