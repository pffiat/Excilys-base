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

@WebServlet("/AjouterComputerServlet")
public class AddComputerServlet extends HttpServlet {
	
    @Autowired
    private CompanyService cpts;
    @Autowired
    private ComputerService cs;
	private static final long serialVersionUID = 1L;

	public void init() {
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> liste = cpts.getAll();
		request.setAttribute("companies", liste);
		request.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request , response);
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerDTO dto = new ComputerDTO();
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
			cs.insertComputer(cpn);
			response.sendRedirect("");
		}else{
			request.setAttribute("name", dto.getName());
			request.setAttribute("introduced", dto.getIntroduced());
			request.setAttribute("discontinued", dto.getDiscontinued());
			request.setAttribute("company_id", dto.getCompany_id());
			CompanyService cp = new CompanyService();
			List<Company> liste = cp.getAll();
			request.setAttribute("company_name", liste.get(dto.getCompany_id()).getName());
			liste.remove(dto.getCompany_id());
			request.setAttribute("companies", liste);
			request.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request , response);
		}
		
	}
	

}
