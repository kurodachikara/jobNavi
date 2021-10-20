package jp.kuroda.jobNavi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kuroda.jobNavi.model.Offer;
import jp.kuroda.jobNavi.repository.OfferRepository;


@Service
public class HomeService {
	@Autowired
	private OfferRepository offerRepository;
	
	public List<Offer> getOfferList(){
		return offerRepository.findByActiveTrue();
	}
	public List<Offer> findOfferList(String word,String prefecture){
		return offerRepository.findByActiveTrueAndTitleContainsAndPrefectureContains(word, prefecture);
	}
}
