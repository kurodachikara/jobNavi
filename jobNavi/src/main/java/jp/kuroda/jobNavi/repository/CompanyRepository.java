package jp.kuroda.jobNavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.kuroda.jobNavi.model.Company;



public interface CompanyRepository extends JpaRepository<Company,Integer>{

}
