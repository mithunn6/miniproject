import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Student extends Person {
    private String className;
    private double percentage;

    @Override
    public void readInput(Scanner scanner) {
        System.out.print("Enter student name: ");
        name = scanner.nextLine();
        System.out.print("Enter class name: ");
        className = scanner.nextLine();
        System.out.print("Enter phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        email = scanner.nextLine();
        System.out.print("Enter percentage: ");
        percentage = scanner.nextDouble();
    }

    public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
    public void save(Connection connection) throws SQLException {
        String sql = "INSERT INTO student (name, class, phonenumber, email, percentage) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, className);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);
            statement.setDouble(5, percentage);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
        }
    }
	public static Student retrieve(Connection connection, int studentId) throws SQLException {
	    String sql = "SELECT * FROM student WHERE id = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, studentId);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                Student student = new Student();
	                student.setId(resultSet.getInt("id"));
	                student.setName(resultSet.getString("name"));
	                student.setClassName(resultSet.getString("class"));
	                student.setPhoneNumber(resultSet.getString("phonenumber"));
	                student.setEmail(resultSet.getString("email"));
	                student.setPercentage(resultSet.getDouble("percentage"));
	                return student;
	            }
	        }
	    }
	    return null; // Student with the given ID not found
	}
	public void update(Connection connection) throws SQLException {
	    String sql = "UPDATE student SET name = ?, class = ?, phonenumber = ?, email = ?, percentage = ? WHERE id = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, name);
	        statement.setString(2, className);
	        statement.setString(3, phoneNumber);
	        statement.setString(4, email);
	        statement.setDouble(5, percentage);
	        statement.setInt(6, id);
	        statement.executeUpdate();
	    }
	}
	public void delete(Connection connection) throws SQLException {
	    String sql = "DELETE FROM student WHERE id = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    }
	}
   
}