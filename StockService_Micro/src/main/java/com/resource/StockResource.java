package com.resource;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	/*@Resource(name= "restTemplate")
	private RestTemplate restTemplate;*/
	
	@Autowired private RestTemplate restTemplate;
	
	@GetMapping("/{username}")
	public List<Stock> getStock(@PathVariable("username") final String username){
		
		ResponseEntity<List<String>> quoteResponse= restTemplate.exchange("http://localhost:8300/rest/db/"+ username, HttpMethod.GET, null,
								new ParameterizedTypeReference<List<String>>() {
								});
		
		List<String> quotes= quoteResponse.getBody();
		
		return quotes.stream()
						.map(new Function<String, Stock>() {
							@Override
							public Stock apply(String quote) {
								try {
									return YahooFinance.get(quote);
								} catch (IOException e) {
									e.printStackTrace();
									return new Stock(quote);
								}
							}
						})
						.collect(Collectors.toList());
	}
	
	@GetMapping("/getName")
	public String getName() {
		return "Mayar";
	}
}
