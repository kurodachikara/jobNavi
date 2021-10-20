package jp.kuroda.jobNavi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kuroda.jobNavi.model.Account;
import jp.kuroda.jobNavi.model.AccountForm;
import jp.kuroda.jobNavi.service.AccountService;



@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("")
	public  String index(Model model) {
		List<Account> accounts=accountService.getAccountList();
		model.addAttribute("accounts",accounts);
		return "account/index";
	}
	@GetMapping("/create")
	public String create(AccountForm accountForm) {
		return "account/create";
	}
	@PostMapping("/create")
	public String create(@Valid AccountForm accountForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return"account/create";
		}
		try {
			String username=accountForm.getUsername();
			String password=accountForm.getPassword();
			boolean active=accountForm.getActive();
			
			switch(accountForm.getType()){
			case "administrator":
				accountService.createAdministratorAccount(username, password, active);break;
			case "company":
				accountService.createCompanyAccount(username, password, active);break;
			case "person":
				accountService.createPersonAccount(username, password, active);break;
			}
			return "redirect:/account";
		}catch(DuplicateKeyException e) {
			bindingResult.addError(new FieldError("accountForm","username","既に存在するユーザーIDです"));
			return "account/create";
		}
	}
	@GetMapping("/{username}/edit")
	public String edit(@PathVariable("username") Account account,AccountForm accountForm) {
		accountForm.setType(account.getType());
		accountForm.setUsername(account.getUsername());
		accountForm.setActive(account.getActive());
		return "account/edit";
	}
	@PostMapping("/{username}/edit")
	public String edit(@PathVariable("username") Account account,@Valid AccountForm accountForm,BindingResult bindingResult) {
		if(!accountForm.getPassword().equals("")&&bindingResult.hasErrors()) {
			return"account/edit";
		}
		accountService.updateAccount(accountForm.getUsername(), accountForm.getPassword(), accountForm.getActive());
		return"redirect:/account";
	}
}
