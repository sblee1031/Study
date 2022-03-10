### keycloak.json 임포트 (main\resources\keycloak.json)
```
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
```

### keycloak.json 예시
```
{
  "realm": "coldchain4",
  "auth-server-url": "http://localhost:8180/",
  "ssl-required": "external",
  "resource": "monitoring",
  "verify-token-audience": true,
  "credentials": {
    "secret": "BcEOEgrocDTbu5xxzUYkfsNMW1gmlgDS"
  },
  "use-resource-role-mappings": false, #true일 경우 resource(client) Role 정보를 맵핑함.
  "confidential-port": 0
}
```

### Keycloak Id 객체 불러오는 방법

```
# Controller.java
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request) {
		KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakSecurityContext x = principal.getAccount().getKeycloakSecurityContext();
        
        log.info(x.getRealm());
        log.info(x.getIdTokenString());
        log.info(x.getIdToken().getPreferredUsername());
        log.info(x.getIdToken().getAuth_time().toString());
		log.info(x.getIdToken().getEmail());
		return "Create Role";
	}
```