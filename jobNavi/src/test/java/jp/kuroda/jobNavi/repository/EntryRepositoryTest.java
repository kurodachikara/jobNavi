package jp.kuroda.jobNavi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import jp.kuroda.jobNavi.model.Entry;
import jp.kuroda.jobNavi.model.Offer;
import jp.kuroda.jobNavi.model.Person;



@DataJpaTest
public class EntryRepositoryTest {
	@Autowired
	private TestEntityManager entitymanager;
	@Autowired
	private EntryRepository entryRepository;
	
	@Test
	public void testFindByOfferAndPerson() {
		Offer offer=new Offer();
		offer.setTitle("Javaエンジニア募集");
		offer.setPrefecture("東京都");
		offer.setAddress("新宿区");
		entitymanager.persist(offer);
		
		Person person=new Person();
		person.setName("春田");
		person.setMail("test@example.com");
		person.setTel("012-3456-7890");
		person.setBirthday(LocalDate.of(1990, 1, 23));
		entitymanager.persist(person);
		
		Entry entry=new Entry();
		entry.setOffer(offer);
		entry.setPerson(person);
		entitymanager.persist(entry);
		
		Entry found=entryRepository.findByOfferAndPerson(offer, person);
		assertThat(found).isNotNull();
	}
}
