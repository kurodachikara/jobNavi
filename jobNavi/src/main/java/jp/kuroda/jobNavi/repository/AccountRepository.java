package jp.kuroda.jobNavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.kuroda.jobNavi.model.Account;


public interface AccountRepository extends JpaRepository<Account,String> {

}
