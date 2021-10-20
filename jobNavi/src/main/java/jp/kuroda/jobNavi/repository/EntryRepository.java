package jp.kuroda.jobNavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.kuroda.jobNavi.model.Entry;
import jp.kuroda.jobNavi.model.Offer;
import jp.kuroda.jobNavi.model.Person;



public interface EntryRepository extends JpaRepository<Entry,Integer>{
	Entry findByOfferAndPerson(Offer offer,Person person);
}
