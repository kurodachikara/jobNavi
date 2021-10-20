package jp.kuroda.jobNavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.kuroda.jobNavi.model.Person;


public interface PersonRepository extends JpaRepository<Person,Integer>{

}
