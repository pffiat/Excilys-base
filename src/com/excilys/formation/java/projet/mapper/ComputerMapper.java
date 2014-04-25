package com.excilys.formation.java.projet.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.excilys.formation.java.projet.DTO.ComputerDTO;
import com.excilys.formation.java.projet.modele.Company;
import com.excilys.formation.java.projet.modele.Computer;

public class ComputerMapper {

	public ComputerMapper() {
		// TODO Auto-generated constructor stub
	}

	public final static Computer fromDTO( ComputerDTO dto){
		Company c = new Company(dto.getCompany_id(), dto.getCompany());
		return new Computer(dto.getId(), dto.getName(), stringToCalendar(dto.getIntroduced()) , stringToCalendar(dto.getDiscontinued()), c);
	}

	public final static ComputerDTO toDTO( Computer cpn) {
		return new ComputerDTO(cpn.getId(), cpn.getName(), calendarToString(cpn.getIntroduced()), calendarToString(cpn.getDiscontinued()), cpn.getCompany().getId(), cpn.getCompany().getName());
	}

	public final static List<ComputerDTO> toDTOList(List<Computer> cl){
		List<ComputerDTO> dto = new ArrayList<ComputerDTO>();
		for(Iterator<Computer> i = cl.iterator(); i.hasNext(); ) {
			dto.add(toDTO(i.next()));
		}
		return dto;
	}

	public final static List<Computer> fromDTOList(List<ComputerDTO> dto){
		List<Computer> cl = new ArrayList<Computer>();
		for(Iterator<ComputerDTO> i = dto.iterator(); i.hasNext(); ) {
			cl.add(fromDTO(i.next()));
		}
		return cl;
	}

	public final static Calendar stringToCalendar(String string){

		Calendar c = Calendar.getInstance();
		if(string!=null && !("".equals(string))) {
			try {
				c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(string));
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("stringToConvert: " + string);
			}
		}
		return c;
	}

	public final static String calendarToString(Calendar cal) {


		StringBuffer ret = new StringBuffer();
		ret.append(cal.get(Calendar.YEAR));
		ret.append("-");

		String month = null;
		int mo = cal.get(Calendar.MONTH) + 1; /* Calendar month is zero indexed, string months are not */
		if(mo < 10) {
			month = "0" + mo;
		}
		else {
			month = "" + mo;
		}
		ret.append(month);      

		ret.append("-");

		String date = null;
		int dt = cal.get(Calendar.DATE);
		if(dt < 10) {
			date = "0" + dt;
		}
		else {
			date = "" + dt;
		}
		ret.append(date);

		return ret.toString();
	}

}
