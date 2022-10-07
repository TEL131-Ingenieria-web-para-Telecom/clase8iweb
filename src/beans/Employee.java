package beans;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobId;
    private int managerId;

    public void imprimeDatos() {
        System.out.print("emp id: " + employeeId);
        System.out.print(" | Name: " + firstName + " " + lastName);
        System.out.print(" | manager id: " + managerId);
        System.out.println(" | job id: " + jobId);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
