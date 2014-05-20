package com.excilys.formation.java.projet.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.excilys.formation.java.projet.dto.ComputerDto;
import com.excilys.formation.java.projet.modele.Computer;

public class ComputerMapper {

	public ComputerMapper() {
	}

	public final static Computer fromDTO( ComputerDto dto){
		return new Computer(dto);
	}

	public final static ComputerDto toDTO( Computer cpn) {
		return new ComputerDto(cpn);
	}

	public final static List<ComputerDto> toDTOList(List<Computer> cl){
		List<ComputerDto> dto = new ArrayList<ComputerDto>();
		for(Iterator<Computer> i = cl.iterator(); i.hasNext(); ) {
			dto.add(toDTO(i.next()));
		}
		return dto;
	}

	public final static List<Computer> fromDTOList(List<ComputerDto> dto){
		List<Computer> cl = new ArrayList<Computer>();
		for(Iterator<ComputerDto> i = dto.iterator(); i.hasNext(); ) {
			cl.add(fromDTO(i.next()));
		}
		return cl;
	}
}
