package com.excilys.formation.java.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.projet.dto.ComputerDTO;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.Company;
import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.service.CompanyService;
import com.excilys.formation.java.projet.service.ComputerService;
import com.excilys.formation.java.projet.validator.ComputerDTOValidator;


@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	
	@Autowired
	private CompanyService cs;
	@Autowired
	private ComputerService cpts;
	private static final long serialVersionUID = 1L;
    private int idPrivate;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerDTO cpt = new ComputerDTO();
		List<Company> liste = cs.getAll();
		String id = request.getParameter("id");
		System.out.println("id: " + id);
		if(id != null) {
			idPrivate = Integer.parseInt(id);
		}
		cpt.setName(request.getParameter("name"));	
		cpt.setIntroduced(request.getParameter("introduced"));
		cpt.setDiscontinued(request.getParameter("discontinued"));
		Company cpn = new Company();
		System.out.println("company recue:"+request.getParameter("comp_name")+request.getParameter("comp_id"));
		if(request.getParameter("comp_id") != null) {
			cpn.setName(request.getParameter("comp_name"));
			cpn.setId(new Integer(request.getParameter("comp_id")));
		} else {
			cpn.setId(0);
		}
		request.setAttribute("computer", cpt);
		request.setAttribute("cpn", cpn);
		liste.remove(cpn);
		request.setAttribute("companies", liste);
		request.getRequestDispatcher("/WEB-INF/EditComputer.jsp").forward(request , response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ComputerDTO dto = new ComputerDTO();
        dto.setId(idPrivate);
		dto.setName(request.getParameter("name"));	
		dto.setIntroduced(request.getParameter("introduced"));
		dto.setDiscontinued(request.getParameter("discontinued"));
		if(request.getParameter("company") != null) {
			dto.setCompany_id(new Integer(request.getParameter("company")));
		} else {
			dto.setCompany_id(0);
		}
		
  		ComputerDTOValidator v = new ComputerDTOValidator();
  		int codeError = v.testComputerDTO(dto);
		if(codeError != 0) {
			Computer cpn = ComputerMapper.fromDTO(dto);
			cpts.updateComputer(cpn);
			response.sendRedirect("");
		}else{
			request.setAttribute("name", dto.getName());
			request.setAttribute("introduced", dto.getIntroduced());
			request.setAttribute("discontinued", dto.getDiscontinued());
			request.setAttribute("company_id", dto.getCompany_id());
			List<Company> liste = cs.getAll();
			request.setAttribute("company_name", liste.get(dto.getCompany_id()).getName());
			liste.remove(dto.getCompany_id());
			request.setAttribute("companies", liste);
			request.getRequestDispatcher("/WEB-INF/EditComputer.jsp").forward(request , response);
		}
	}

}
