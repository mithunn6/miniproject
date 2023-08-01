import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Teacher extends Person {
    private double salary;
    private String qualification;
    private String specialization;

    @Override
    public void readInput(Scanner scanner) {
        System.out.print("Enter teacher name: ");
        name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        email = scanner.nextLine();
        System.out.print("Enter salary: ");
        salary = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter qualification: ");
        qualification = scanner.nextLine();
        System.out.print("Enter specialization: ");
        specialization = scanner.nextLine();
    }

    @Override
    public void save(Connection connection) throws SQLException {
        String sql = "INSERT INTO teacher (name, phone, email, salary, qualification, specialization) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, email);
            statement.setDouble(4, salary);
            statement.setString(5, qualification);
            statement.setString(6, specialization);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
        }
    }
    public static Teacher retrieve(Connection connection, int teacherId) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(resultSet.getInt("id"));
                    teacher.setName(resultSet.getString("name"));
                    teacher.setPhoneNumber(resultSet.getString("phone"));
                    teacher.setEmail(resultSet.getString("email"));
                    teacher.setSalary(resultSet.getDouble("salary"));
                    teacher.setQualification(resultSet.getString("qualification"));
                    teacher.setSpecialization(resultSet.getString("specialization"));
                    return teacher;
                }
            }
        }
        return null; // Teacher with the given ID not found
    }
   
    public void update(Connection connection) throws SQLException {
        String sql = "UPDATE teacher SET name = ?, phone = ?, email = ?, salary = ?, qualification = ?, specialization = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, email);
            statement.setDouble(4, salary);
            statement.setString(5, qualification);
            statement.setString(6, specialization);
            statement.setInt(7, id);
            statement.executeUpdate();
        }
    }
   
    public void delete(Connection connection) throws SQLException {
        String sql = "DELETE FROM teacher WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
}