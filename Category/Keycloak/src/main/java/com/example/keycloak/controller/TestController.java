package com.example.keycloak.controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class TestController {

	public Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	 * @RequestMapping(value = "/permitAll", method = RequestMethod.GET) public
	 * ResponseEntity<String> permitAll() { return
	 * ResponseEntity.ok("누구나 접근이 가능합니다.\n"); }
	 * 
	 * @RequestMapping(value = "/authenticated", method = RequestMethod.GET) public
	 * ResponseEntity<String> authenticated(@RequestHeader String Authorization) {
	 * log.debug(Authorization); return ResponseEntity.ok("로그인한 사람 누구나 가능합니다.\n"); }
	 * 
	 * @RequestMapping(value = "/user", method = RequestMethod.GET) public
	 * ResponseEntity<String> user(@RequestHeader String Authorization) {
	 * log.debug(Authorization); return ResponseEntity.ok("user 가능합니다.\n"); }
	 * 
	 * @RequestMapping(value = "/admin", method = RequestMethod.GET) public
	 * ResponseEntity<String> admin(@RequestHeader String Authorization) {
	 * log.debug(Authorization); return ResponseEntity.ok("admin 가능합니다.\n"); }
	 */
	private @Autowired HttpServletRequest request;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String monitoring() {
//		log.info(principal.toString());
		return "Monitoring Service!!";
	}

	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request) {
		KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakSecurityContext x = principal.getAccount().getKeycloakSecurityContext();
        
        log.info(x.getRealm());
        log.info(x.getIdTokenString());
        log.info(x.getIdToken().getPreferredUsername());
        log.info(x.getIdToken().getAuth_time().toString());
		log.info(x.getIdToken().getEmail());
//		if (principal instanceof KeycloakPrincipal) {
//			KeycloakPrincipal x = (KeycloakPrincipal) principal;
//			
//			log.info(x.getKeycloakSecurityContext().getRealm());
//		}
//		log.info(principal.toString());
		return "Create Role";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Principal principal) {
		log.info(principal.toString());
		return "Read Role";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Principal principal) {
		log.info(principal.toString());
		return "Update Role";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Principal principal) {
		log.info(principal.toString());
		return "Delete Role";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String handleLogoutt() throws ServletException {
		request.logout();
		log.info("로그아웃!");
		return "로그아웃";
	}

	@RequestMapping("/app1")
	public String tracingTest() {
		return "This is permitAll!";
	}
}