package com.cognizant.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.authorization.exceptions.UserExistsWithTheGivenCredentialsException;
import com.cognizant.authorization.model.AuthenticationRequest;
import com.cognizant.authorization.model.User;
import com.cognizant.authorization.repository.UserRepository;
import com.cognizant.authorization.service.MyUserDetailsService;
import com.cognizant.authorization.user.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class AuthorizationServiceApplicationTests {

	@Mock
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	private MockHttpServletResponse response;

	@Test
	@Order(1)
	void loadByUserNameMethodTest() {
		User user = new User();
		user.setUserName("srikanth");
		user.setPassword("srikanth7977");
		log.info("creating user in ---> loadByUserNameMethodTest");
		UserDetails userDetails = new UserPrincipal(user);
		log.info("creating userDetails in ---> loadByUserNameMethodTest ");
		when(myUserDetailsService.loadUserByUsername("srikanth")).thenReturn(userDetails);
		assertNotNull(userDetails);
		log.info("Testing  userDetails object is not null in ---> loadByUserNameMethodTest ");
		assertEquals("srikanth", myUserDetailsService.loadUserByUsername("srikanth").getUsername());
		log.info("End of Testing userDetails by userName in ---> loadByUserNameMethodTest ");

	}

	@Test
	@ValueSource(classes = {UsernameNotFoundException.class})
	@Order(2)
	void loadByUserNameMethodForInavlidUserTest() {
		log.info("Testing the method with Exception in ---> loadByUserNameMethodForInavlidUserTest");
		when(myUserDetailsService.loadUserByUsername("shivaji"))
				.thenThrow(new UsernameNotFoundException("User not found"));
		UsernameNotFoundException exception=assertThrows(UsernameNotFoundException.class, () -> {myUserDetailsService.loadUserByUsername("shivaji");});
		String message = exception.getMessage();
		
		assertEquals("User not found", message);
		
		System.err.println(message);
		log.warn("UsernameNotFoundException was raised in ----> loadByUserNameMethodForInavlidUserTest");
		log.info("End of Testing with UsernameNotFoundException in --->  loadByUserNameMethodForInavlidUserTest");

	}

	@Test
	@Order(3)
	void addingNewUserToDBTest() throws UserExistsWithTheGivenCredentialsException {
		log.info("starting to add new user to db with passsword encryption --->addingNewUserToDBTest");
		User user = new User();
		user.setUserName("shivaji");
		user.setPassword("shivaji");
		log.info("creating a new user to add to db in ---> addingNewUserToDBTest");
		when(myUserDetailsService.addUser(user)).thenReturn("Registration Successfull!!!");
		assertEquals("Registration Successfull!!!", myUserDetailsService.addUser(user));
		log.info("End of adding a new user to db in ---> addingNewUserToDBTest");
	}

	@Test//(expected = UserExistsWithTheGivenCredentialsException.class)
	@Order(4)
	@ValueSource(classes = {UserExistsWithTheGivenCredentialsException.class})
	void addingExistingUserToDBTest() throws UserExistsWithTheGivenCredentialsException {
		log.info("starting to add new user to db --->addingExistingUserToDBTest");
		User user = new User();
		user.setUserName("shivaji");
		user.setPassword("shivaji");
		log.info("creating a new user to add to db in ---> addingExistingUserToDBTest");
		when(myUserDetailsService.addUser(user)).thenThrow(new UserExistsWithTheGivenCredentialsException("User Exists please provide new Credentials !!!"));
		
		//doThrow(UserExistsWithTheGivenCredentialsException.class).when(myUserDetailsService)).;
		
		//myUserDetailsService.addUser(user);
		
		UserExistsWithTheGivenCredentialsException exception= assertThrows(UserExistsWithTheGivenCredentialsException.class, () -> {myUserDetailsService.addUser(user);});
		
		String message = exception.getMessage();
		
		assertEquals("User Exists please provide new Credentials !!!", message);
		
		log.warn("UserExistsWithTheGivenCredentialsException was raised in ----> addingExistingUserToDBTest");
		log.info("End of Testing with UserExistsWithTheGivenCredentialsException in --->  addingExistingUserToDBTest");
	}

	@Test
	@Order(5)
	// @WithUserDetails("srikanth")
	void createAuthenticationTokenTest() throws Exception {
		log.info("Testing -token generation functionality ----> createAuthenticationTokenTest");
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("srikanth", "srikanth7977");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(authenticationRequest);
		log.info("creating json out of java object {}", json);
		response = mockMvc.perform(post("/authentication/auth").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn().getResponse();
		assertNotNull(response);
		log.info("End of create Authentication ----> createAuthenticationTokenTest");

	}

	@Test
	@Order(6)
	void validateAuthenticationTokenTest() throws Exception {
		log.info("Testing -token generation functionality ----> createAuthenticationTokenTest");
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("srikanth", "srikanth7977");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(authenticationRequest);
		log.info("creating json out of java object {}", json);	
		
		response = mockMvc.perform(post("/authentication/auth").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn().getResponse();
		assertNotNull(response);
		
		String content=response.getContentAsString();
		content=content.substring(8, content.length()-2);
		//System.err.println("response   "+ content.substring(8, content.length()-2));		
		log.info("jwt Response {}", content);
		mockMvc.perform(post("/authentication/validate")
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, content))
			.andExpect(status().isOk());			
		log.info("End of create Authentication ----> createAuthenticationTokenTest");

	}
	
	
	/*
	 * @Test
	 * 
	 * @Order(9) void validateAuthenticationTokenWithExceptionTest() throws
	 * Exception { log.
	 * info("Testing -token generation functionality ----> createAuthenticationTokenTest"
	 * ); AuthenticationRequest authenticationRequest = new
	 * AuthenticationRequest("srikanth", "srikanth7977"); ObjectMapper objectMapper
	 * = new ObjectMapper(); String json =
	 * objectMapper.writeValueAsString(authenticationRequest);
	 * log.info("creating json out of java object {}", json);
	 * 
	 * response =
	 * mockMvc.perform(post("/authentication/auth").contentType(MediaType.
	 * APPLICATION_JSON).content(json)) .andReturn().getResponse();
	 * assertNotNull(response);
	 * 
	 * String content=response.getContentAsString(); content=content.substring(9,
	 * content.length()-2); //System.err.println("response   "+ content.substring(8,
	 * content.length()-2)); log.info("jwt Response {}", content);
	 * mockMvc.perform(post("/authentication/validate")
	 * .contentType(MediaType.APPLICATION_JSON) .header(HttpHeaders.AUTHORIZATION,
	 * content)) .andExpect(status().isInternalServerError());
	 * log.info("End of create Authentication ----> createAuthenticationTokenTest");
	 * 
	 * }
	 */


	@Test
	@Order(7)
	void userRegistrationTest() throws Exception {

		log.info("New User Registration ---> userRegistrationTest");
		User user = new User();
		user.setUserName("sharan");
		user.setPassword("sharan123");
		user.setAccountNonExpired(true); 
		user.setAccountNonLocked(true);
		user.setEnabled(true); 
		user.setCredentialsNonExpired(true);
		user.setRoles("ROLE_USER");
		 
		log.info("User Registration, created User {}", user);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		log.info("creating json out of java object {}", json);
		mockMvc.perform(post("/authentication/add-user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
		log.info("End of New User Registration ----> UserRegistrationTest");

	}
	
	@Test
	@Order(8)
	void userRegistrationWithExceptionTest() throws Exception {

		log.info("New User Registration ---> userRegistrationTest");
		User user = new User();
		user.setUserName("sharan");
		user.setPassword("sharan123");
		user.setAccountNonExpired(true); 
		user.setAccountNonLocked(true);
		user.setEnabled(true); 
		user.setCredentialsNonExpired(true);
		user.setRoles("ROLE_USER");
		 
		log.info("User Registration, created User {}", user);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		log.info("creating json out of java object {}", json);
		mockMvc.perform(post("/authentication/add-user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isConflict());
		//result -> assertTrue(result.getResolvedException() instanceof UserExistsWithTheGivenCredentialsException));
		userRepository.delete(user);
		log.info("End of New User Registration ----> UserRegistrationTest");

	}

}
