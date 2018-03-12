package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import edu.mum.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;

	@Autowired
	PersonService personService;

	@Autowired
	UserService userService;
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage() {
		return "redirect:/products";
	}

	@GetMapping("/products")
	public String products(Model model, HttpServletRequest request){
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			if(inputFlashMap.containsKey("message")) {
				String message = (String) inputFlashMap.get("message");
				model.addAttribute("message", message);
			}
		}
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}

	@GetMapping(path = "/login")
	public String loginPage(Principal principal) {
		if(principal != null){
			return "redirect:/products";
		}
		return "login";
	}

	@GetMapping("/register")
	public String registerPage(Model model, Principal principal){
		if(principal != null){
			return "products";
		}
		model.addAttribute("person", new Person());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid Person person, String password, String password2, BindingResult result){
		if(result.hasErrors()){
			return "register";
		}

		if(!password.equals(password2)){
			return "register";
		}

		if(personService.findByEmail(person.getEmail()).size() > 0){
			return "register";
		}

		List<GrantedAuthority> authorities = new ArrayList(){};
		authorities.add(new SimpleGrantedAuthority("USER"));
		UserDetails user = new User(person.getEmail(), password, authorities);
		userService.createUser(user, authorities);
		personService.savePerson(person);

		return "redirect:/products";
	}

}
