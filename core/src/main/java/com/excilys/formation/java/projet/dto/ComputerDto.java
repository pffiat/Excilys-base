package com.excilys.formation.java.projet.dto;




import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.excilys.formation.java.projet.modele.Computer;

public class ComputerDto {

	private int id = 0;

	@NotEmpty
	private String name;

	@Pattern(regexp = "^|(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
	private String introduced;

	@Pattern(regexp = "^|(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
	private String discontinued;

	@Range(min=0)
	private int company_id = 0;
	private String company;

	public ComputerDto() {
	}

	public ComputerDto(int id, String name, String introduced,
			String discontinued, int company_id, String company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", company_id=" + company_id + ", company=" + company + "]";
	}

	private ComputerDto(Builder b) {
		this.setName(b.getName());
		this.setId(b.getId());
		this.setIntroduced(b.getIntroduced());
		this.setDiscontinued(b.getDiscontinued());
		this.setCompany_id(b.getCompany_id());
		this.setCompany(b.getCompany());
	}

	public ComputerDto(Computer cpn) {
		super();
		this.id = cpn.getId();
		this.name = cpn.getName();
		if(cpn.getIntroduced() != null){
			this.introduced = cpn.getIntroduced().toString();
		}
		if(cpn.getDiscontinued() != null) {
			this.discontinued = cpn.getDiscontinued().toString();			
		}
		this.company_id = cpn.getCompany().getId();
		this.company = cpn.getCompany().getName();

	}

	public static class Builder {

		private int id;
		private String name;
		private String introduced;
		private String discontinued;
		private int company_id;
		private String company;

		public Builder id(int id) {
			this.setId(id);
			return this;
		}

		public Builder name(String name) {
			this.setName(name);
			return this;
		}

		public Builder introduced(String introcued) {
			this.setIntroduced(introcued);
			return this;
		}

		public Builder discontinued(String discontinued) {
			this.setDiscontinued(discontinued);
			return this;
		}

		public Builder company_id(int company_id) {
			this.setCompany_id(company_id);
			return this;
		}

		public Builder company(String company) {
			this.setCompany(company);
			return this;
		}

		public Builder() {
			id = 0;
			name = "unknown";
			introduced = "unknown";
			discontinued = "unknown";
			company_id = 0;
			company = "unknonw";
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getIntroduced() {
			return introduced;
		}

		public String getDiscontinued() {
			return discontinued;
		}

		public int getCompany_id() {
			return company_id;
		}

		public String getCompany() {
			return company;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setIntroduced(String introduced) {
			this.introduced = introduced;
		}

		public void setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
		}

		public void setCompany_id(int company_id) {
			this.company_id = company_id;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public ComputerDto build() {
			return new ComputerDto(this);
		}

	}

}
