package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SalesSummaryDTO> salesSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min = "".equals(minDate) ? today.minusYears(1L) : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? today : LocalDate.parse(maxDate);
		return repository.salesSummary(min, max);
	}

	public List<SalesReportDTO> salesReport(String minDate, String maxDate, String nameSeller) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min = "".equals(minDate) ? today.minusYears(1L) : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? today : LocalDate.parse(maxDate);
		String name = "".equals(nameSeller) ? null : nameSeller;
		return repository.salesReport(min, max, name);
	}
}
