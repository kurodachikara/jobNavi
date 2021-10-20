package jp.kuroda.jobNavi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kuroda.jobNavi.model.Account;
import jp.kuroda.jobNavi.model.Company;
import jp.kuroda.jobNavi.model.Person;
import jp.kuroda.jobNavi.repository.AccountRepository;



@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private SpringUserService springUserService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PersonService personService;
	
	public List<Account> getAccountList(){
		return accountRepository.findAll();
	}
	public Account find(String username) {
		return accountRepository.findById(username).get();
	}
	@Transactional
	public Account createAdministratorAccount(String username,String password,boolean active) {
		springUserService.createSpringUser(username,password,"ADMINISTRATOR",active);
		Account account=new Account();
		account.setUsername(username);
		account.setType("administrator");
		account.setActive(active);
		accountRepository.save(account);
		return account;
	}
	@Transactional
	public Account createCompanyAccount(String username,String password,boolean active) {
		springUserService.createSpringUser(username, password, "COMPANY", active);
		Company company=companyService.createDefaultCompany();
		Account account=new Account();
		account.setUsername(username);
		account.setType("company");
		account.setActive(active);
		account.setCompany(company);
		accountRepository.save(account);
		return account;
	}
	@Transactional
	public Account createPersonAccount(String username,String password,boolean active) {
		springUserService.createSpringUser(username, password, "PERSON", active);
		Person person=personService.createDefaultValuePerson();
		Account account=new Account();
		account.setUsername(username);
		account.setType("person");
		account.setActive(active);
		account.setPerson(person);
		accountRepository.save(account);
		return account;
	}
	@Transactional
	public void updateAccount(String username,String password,boolean active) {
		springUserService.updateSpringUser(username, password, active);
		Account account=accountRepository.findById(username).get();
		account.setActive(active);
		accountRepository.save(account);
	}
}
