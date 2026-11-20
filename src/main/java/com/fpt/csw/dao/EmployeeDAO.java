package com.fpt.csw.dao;

import com.fpt.csw.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** Data access for the employees table (all queries use PreparedStatement). */
public class EmployeeDAO {

    /** Returns every employee, ordered by id. */
    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT id, name, salary FROM employees ORDER BY id";
        List<Employee> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary")));
            }
        }
        return list;
    }

    /** Inserts a new employee and returns it with its generated id. */
    public Employee insert(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (name, salary) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getName());
            ps.setDouble(2, e.getSalary());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    e.setId(keys.getInt(1));
                }
            }
        }
        return e;
    }

    /** Updates an existing employee. Returns false if the id does not exist. */
    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE employees SET name = ?, salary = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setDouble(2, e.getSalary());
            ps.setInt(3, e.getId());
            return ps.executeUpdate() > 0;
        }
    }
}