package jp.kuroda.jobNavi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DuplicateKeyException;

import jp.kuroda.jobNavi.service.AccountService;

@SpringBootApplication
public class JobNaviApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JobNaviApplication.class, args);
	}
	@Autowired(required=false)
	private AccountService accountService;
	@Override
	public void run(String... args) throws Exception {
		if(accountService==null) {
			return;
		}
		String username="admin";
		String password="admin";
		try {
			accountService.createAdministratorAccount(username, password, true);
		}catch(DuplicateKeyException e) {
				
		}
	}
}
