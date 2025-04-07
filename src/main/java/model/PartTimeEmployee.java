package model;

import java.util.Date;
import org.bson.Document;

public class PartTimeEmployee extends Employee implements Payable {
	private int hoursWorked; // Số giờ làm
	private double hourlyRate; // Lương giờ

	public PartTimeEmployee(String id, String fullName, Date startDate, String position, double baseSalary,
			int hoursWorked, double hourlyRate, double overtimeSalary) {
		super(id, fullName, startDate, position, baseSalary, overtimeSalary);
		this.hoursWorked = hoursWorked;
		this.hourlyRate = hourlyRate;
	}

	// Constructor from MongoDB Document
	public PartTimeEmployee(Document doc) {
		super(doc);
		this.hoursWorked = doc.getInteger("hoursWorked");
		this.hourlyRate = doc.getDouble("hourlyRate");
	}

	// Thêm getter
	public int getHoursWorked() {
		return hoursWorked;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	@Override
	public double calculateSalary() {
		return hoursWorked * hourlyRate + getOvertimeSalary();
	}

	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		doc.append("type", "PartTime")
				.append("hoursWorked", this.hoursWorked)
				.append("hourlyRate", this.hourlyRate);
		return doc;
	}
}
