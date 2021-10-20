package jp.kuroda.jobNavi.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kuroda.jobNavi.model.Entry;
import jp.kuroda.jobNavi.model.Offer;
import jp.kuroda.jobNavi.model.Person;
import jp.kuroda.jobNavi.repository.EntryRepository;
import jp.kuroda.jobNavi.repository.PersonRepository;



@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private EntryRepository entryRepository;
	
	public Person createDefaultValuePerson() {
		Person person=new Person();
		person.setName("名前を設定してください");
		person.setMail("please@set.email");
		person.setTel("000-0000-0000");
		person.setBirthday(LocalDate.of(1980, 1, 1));
		personRepository.save(person);
		return person;
	}
	
	public void updatePerson(Person person) {
		personRepository.save(person);
	}
	public Entry getEntry(Offer offer,Person person) {
		Entry entry=entryRepository.findByOfferAndPerson(offer, person);
		if(entry==null) {
			entry=new Entry();
			entry.setOffer(offer);
			entry.setPerson(person);
			entry.setActive(true);
		}
		return entry;
	}
	public Entry createEntry(Entry entry) {
		return entryRepository.save(entry);
	}
}
