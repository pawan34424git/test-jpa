package com.pawan.TestJpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.TestJpa.domain.Employee;
import com.pawan.TestJpa.domain.Leaves;

@Service
@Transactional
public class LeavesService {

	@Autowired
	private EntityManager em;
	
	

	public Leaves post(Leaves leaves) {

		Session session = (Session) em.getDelegate();
		leaves.setEmployee(new Employee(leaves.getEmployeeId()));
		Integer id = (Integer) session.save(leaves);
		leaves = session.find(Leaves.class, id);
		return new Leaves(leaves, true);
	}

	public List<Leaves> getAll(String employeeId) throws SQLException {

		Session session = (Session) em.getDelegate();

		 
			List<Leaves> list = new ArrayList<>();

			if (employeeId != null) {
				Connection conn = ((SessionImpl)session).connection();
				PreparedStatement stmt = conn.prepareStatement("select id,employee_id,day from jpa_3.leaves where employee_id=?"); // connect seprate database
				stmt.setInt(1, Integer.valueOf(employeeId));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					list.add(new Leaves(rs.getInt("id"), rs.getString("day"), null, rs.getInt("employee_id"), null));
				}
				if(!list.isEmpty()) return list;
			} 
		if (employeeId != null) {
			list = session.createQuery("FROM Leaves E join fetch E.employee A where A.id=:employeeId", Leaves.class)
							.setParameter("employeeId", Integer.valueOf(employeeId))
							.list();
		} else {
			list = session.createQuery("FROM Leaves E join fetch E.employee A ", Leaves.class).list(); // solve 1+n hibernate proble
//			list = session.createQuery("FROM Leaves E ", Leaves.class).list();
		}

		return list.stream().map(l -> new Leaves(l, true)).collect(Collectors.toList());

	}

	public Leaves get(Integer id) {
		Session session = em.unwrap(Session.class);
		return  new Leaves(session.find(Leaves.class, id),true);
	}

	public Leaves patch(Integer id, Leaves leaves) throws Exception {
		Session session = em.unwrap(Session.class);
		Leaves leavesExisting = session.find(Leaves.class, id);

		if (leavesExisting != null) {
			if (leaves.getDay() != null) {
				leavesExisting.setDay(leaves.getDay());
			}
			
			 
			 
			session.update(leavesExisting);
			return new Leaves(leavesExisting, true);
		} else {
			throw new Exception("not found");
		}

	}

	public Leaves put(Leaves leaves) throws Exception {
		Session session = (Session) em.getDelegate();
		Leaves leavesExisting = session.find(Leaves.class, leaves.getId());

		if (leavesExisting != null) {
			session.update(leaves);
			return new Leaves(leaves, true);
		} else {
			throw new Exception("not found");
		}

	}

	public void delete(Integer id) throws Exception {
		Session session = (Session) em.getDelegate();
		Leaves leavesExisting = get(id);

		if (leavesExisting != null) {
			session.delete(leavesExisting);
		} else {
			throw new Exception("not found");
		}
	}
}
