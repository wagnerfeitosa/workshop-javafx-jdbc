package model.servicies;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	/*Responsavél por pegar todos os dados*/
	public List<Department> findAll(){

		return dao.findAll();
	}

}
