package com.excilys.formation.java.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.projet.DTO.ComputerDTO;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.Company;
import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.service.CompanyService;
import com.excilys.formation.java.projet.service.ComputerService;
import com.excilys.formation.java.projet.validator.ComputerDTOValidator;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int idPrivate;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        ComputerDTO cpt = new ComputerDTO();
		CompanyService cs = new CompanyService();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ComputerService cs = new ComputerService();
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
			cs.updateComputer(cpn);
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
			request.getRequestDispatcher("/WEB-INF/EditComputer.jsp").forward(request , response);
		}
	}

}
