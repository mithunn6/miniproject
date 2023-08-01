import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Add Student");
                System.out.println("2. Retrieve Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Add Teacher");
                System.out.println("6. Retrieve Teacher");
                System.out.println("7. Update Teacher");
                System.out.println("8. Delete Teacher");
                System.out.println("9. Add Attendance");
                System.out.println("10. Retrieve Attendance");
                System.out.println("11. Update Attendance");
                System.out.println("12. Delete Attendance");
                System.out.println("13. Display Attendance Percentage");
                System.out.println("0. Exit");

                int option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (option) {
                    case 1:
                        addPerson(scanner, connection, new Student());
                        break;
                    case 2:
                        retrieveStudent(scanner, connection);
                        break;
                    case 3:
                        updateStudent(scanner, connection);
                        break;
                    case 4:
                        deleteStudent(scanner, connection);
                        break;
                    case 5:
                        addPerson(scanner, connection, new Teacher());
                        break;
                    case 6:
                        retrieveTeacher(scanner, connection);
                        break;
                    case 7:
                        updateTeacher(scanner, connection);
                        break;
                    case 8:
                        deleteTeacher(scanner, connection);
                        break;
                    case 9:
                        addPerson(scanner, connection, new Attendance());
                        break;
                    case 10:
                        retrieveAttendance(scanner, connection);
                        break;
                    case 11:
                        updateAttendance(scanner, connection);
                        break;
                    case 12:
                        deleteAttendance(scanner, connection);
                        break;
                    case 13:
                        Attendance.displayAttendancePercentage(connection);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addPerson(Scanner scanner, Connection connection, Person person) throws SQLException {
        person.readInput(scanner);
        person.save(connection);
        System.out.println(person.getClass().getSimpleName() + " added successfully!");
    }

    private static void retrieveStudent(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter student ID to retrieve: ");
        int studentId = scanner.nextInt();
        Student student = Student.retrieve(connection, studentId);
        if (student != null) {
            System.out.println("Retrieved student data:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Class: " + student.getClassName());
            System.out.println("Phone Number: " + student.getPhoneNumber());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Percentage: " + student.getPercentage());
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void updateStudent(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter student ID to update: ");
        int studentId = scanner.nextInt();
        Student student = Student.retrieve(connection, studentId);
        if (student != null) {
            System.out.println("Current student data:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Class: " + student.getClassName());
            System.out.println("Phone Number: " + student.getPhoneNumber());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Percentage: " + student.getPercentage());

            System.out.println("\nEnter updated student data:");
            scanner.nextLine(); // Consume the newline character
            System.out.print("Name: ");
            student.setName(scanner.nextLine());
            System.out.print("Class: ");
            student.setClassName(scanner.nextLine());
            System.out.print("Phone Number: ");
            student.setPhoneNumber(scanner.nextLine());
            System.out.print("Email: ");
            student.setEmail(scanner.nextLine());
            System.out.print("Percentage: ");
            student.setPercentage(scanner.nextDouble());

            student.update(connection);
            System.out.println("Student data updated successfully!");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void deleteStudent(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int studentId = scanner.nextInt();
        Student student = Student.retrieve(connection, studentId);
        if (student != null) {
            student.delete(connection);
            System.out.println("Student data deleted successfully!");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void retrieveTeacher(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter teacher ID to retrieve: ");
        int teacherId = scanner.nextInt();
        Teacher teacher = Teacher.retrieve(connection, teacherId);
        if (teacher != null) {
            System.out.println("Retrieved teacher data:");
            System.out.println("ID: " + teacher.getId());
            System.out.println("Name: " + teacher.getName());
            System.out.println("Phone Number: " + teacher.getPhoneNumber());
            System.out.println("Email: " + teacher.getEmail());
            System.out.println("Salary: " + teacher.getSalary());
            System.out.println("Qualification: " + teacher.getQualification());
            System.out.println("Specialization: " + teacher.getSpecialization());
        } else {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }

    private static void updateTeacher(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter teacher ID to update: ");
        int teacherId = scanner.nextInt();
        Teacher teacher = Teacher.retrieve(connection, teacherId);
        if (teacher != null) {
            System.out.println("Current teacher data:");
            System.out.println("ID: " + teacher.getId());
            System.out.println("Name: " + teacher.getName());
            System.out.println("Phone Number: " + teacher.getPhoneNumber());
            System.out.println("Email: " + teacher.getEmail());
            System.out.println("Salary: " + teacher.getSalary());
            System.out.println("Qualification: " + teacher.getQualification());
            System.out.println("Specialization: " + teacher.getSpecialization());

            System.out.println("\nEnter updated teacher data:");
            scanner.nextLine(); // Consume the newline character
            System.out.print("Name: ");
            teacher.setName(scanner.nextLine());
            System.out.print("Phone Number: ");
            teacher.setPhoneNumber(scanner.nextLine());
            System.out.print("Email: ");
            teacher.setEmail(scanner.nextLine());
            System.out.print("Salary: ");
            teacher.setSalary(scanner.nextDouble());
            scanner.nextLine(); // Consume the newline character
            System.out.print("Qualification: ");
            teacher.setQualification(scanner.nextLine());
            System.out.print("Specialization: ");
            teacher.setSpecialization(scanner.nextLine());

            teacher.update(connection);
            System.out.println("Teacher data updated successfully!");
        } else {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }

    private static void deleteTeacher(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter teacher ID to delete: ");
        int teacherId = scanner.nextInt();
        Teacher teacher = Teacher.retrieve(connection, teacherId);
        if (teacher != null) {
            teacher.delete(connection);
            System.out.println("Teacher data deleted successfully!");
        } else {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }

    private static void retrieveAttendance(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter attendance ID to retrieve: ");
        int attendanceId = scanner.nextInt();
        Attendance attendance = Attendance.retrieve(connection, attendanceId);
        if (attendance != null) {
            System.out.println("Retrieved attendance data:");
            System.out.println("ID: " + attendance.getId());
            System.out.println("Student ID: " + attendance.getStudentId());
            System.out.println("Teacher ID: " + attendance.getTeacherId());
            System.out.println("Date: " + attendance.getDate());
            System.out.println("Status: " + attendance.getStatus());
        } else {
            System.out.println("Attendance with ID " + attendanceId + " not found.");
        }
    }

    private static void updateAttendance(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter attendance ID to update: ");
        int attendanceId = scanner.nextInt();
        Attendance attendance = Attendance.retrieve(connection, attendanceId);
        if (attendance != null) {
            System.out.println("Current attendance data:");
            System.out.println("ID: " + attendance.getId());
            System.out.println("Student ID: " + attendance.getStudentId());
            System.out.println("Teacher ID: " + attendance.getTeacherId());
            System.out.println("Date: " + attendance.getDate());
            System.out.println("Status: " + attendance.getStatus());

            System.out.println("\nEnter updated attendance data:");
            scanner.nextLine(); // Consume the newline character
            System.out.print("Student ID: ");
            attendance.setStudentId(scanner.nextInt());
            System.out.print("Teacher ID: ");
            attendance.setTeacherId(scanner.nextInt());
            scanner.nextLine(); // Consume the newline character
            System.out.print("Status (Present/Absent): ");
            attendance.setStatus(scanner.nextLine());

            attendance.update(connection);
            System.out.println("Attendance data updated successfully!");
        } else {
            System.out.println("Attendance with ID " + attendanceId + " not found.");
        }
    }

    private static void deleteAttendance(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter attendance ID to delete: ");
        int attendanceId = scanner.nextInt();
        Attendance attendance = Attendance.retrieve(connection, attendanceId);
        if (attendance != null) {
            attendance.delete(connection);
            System.out.println("Attendance data deleted successfully!");
        } else {
            System.out.println("Attendance with ID " + attendanceId + " not found.");
        }
    }
}