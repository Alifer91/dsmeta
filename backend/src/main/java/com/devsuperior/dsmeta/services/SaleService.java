package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	@Autowired
	private SaleRepository repository;
	
	public Page<Sale> findSales(String minDate,String maxDate,Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());/*dois parametros, um pra indicar que é o horario de agora, e o segundo é indicando o fuso horario, no caso é o padrão do sistema*/
		
		//o trecho apos esses comentarios servem para converter esse tipo de data para localdate
		
				//minDate.equals("")? significa max date é igual a ""?
				//caso sim o valor é o valor de hoje(no min  será data de hoje menos um ano)
				//caso não apenas converta a data do localDate
				//isso serve para não acontecer erros
		LocalDate min =minDate.equals("")? today.minusDays(365) : LocalDate.parse(minDate);
		LocalDate max =maxDate.equals("")? today : LocalDate.parse(maxDate);
		/* o jpa não possui uma função que consulta duas datas ao mesmo tempo , para isso foi preciso criar uma no arquivo sale repository */
		return repository.findSales(min,max,pageable);
	}

}
