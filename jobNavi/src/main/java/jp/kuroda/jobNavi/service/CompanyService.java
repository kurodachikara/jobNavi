package jp.kuroda.jobNavi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kuroda.jobNavi.model.Company;
import jp.kuroda.jobNavi.model.Entry;
import jp.kuroda.jobNavi.model.Offer;
import jp.kuroda.jobNavi.repository.CompanyRepository;
import jp.kuroda.jobNavi.repository.EntryRepository;
import jp.kuroda.jobNavi.repository.OfferRepository;



@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private EntryRepository entryRepository;
	
	public Company createDefaultCompany() {
		Company company=new Company();
		company.setName("名前を設定してください");
		company.setUrl("please set url");
		company.setMail("please@set.email");
		company.setTel("000-0000-0000");
		companyRepository.save(company);
		return company;
	}
	public void updateCompany(Company company) {
		companyRepository.save(company);
	}
	
	public Offer createOffer(Offer offer) {
		return offerRepository.save(offer);
	}
	public void updateOffer(Offer offer) {
		offerRepository.save(offer);
	}
	public void processEntry(Entry entry) {
		entry.setActive(false);
		entryRepository.save(entry);
	}
	
	
}
