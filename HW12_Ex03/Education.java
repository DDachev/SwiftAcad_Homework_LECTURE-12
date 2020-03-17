package bg.swift.HW12_Ex03;

import java.time.LocalDate;

public abstract class Education {
	private boolean graduated;
	private LocalDate enrollmentDate;
	private LocalDate graduationDate;
	private String institutionName;

	protected Education(LocalDate enrollmentDate, LocalDate graduationDate, String institutionName) throws NoGradeException {
		try {
			if (checkGraduationDate(enrollmentDate, graduationDate)) {
				this.enrollmentDate = enrollmentDate;
				this.graduationDate = graduationDate;
			}
			this.institutionName = institutionName;
			if (isGraduated()) {
				this.graduated = true;
			}
		} catch (AfterGraduationDateException e) {
			System.out.println("Graduation date is expected to be after enrollment date.");
		}
	}
	
	protected abstract String getDegree();
	
	protected abstract boolean gotGraduated();
	
	private static boolean checkGraduationDate(LocalDate enrollmentDate, LocalDate graduationDate) throws AfterGraduationDateException{
		if(enrollmentDate.isAfter(graduationDate)) {
			throw new AfterGraduationDateException();
		}
		return true;
	}
	
	protected boolean isGraduated() throws NoGradeException{
		if(LocalDate.now().isAfter(graduationDate)) {
			return true;
		}
		throw new NoGradeException();
	}
	
	protected LocalDate getEnrollmentData() {
		return this.enrollmentDate;
	}
	
	protected LocalDate getGraduationDate() {
		return this.graduationDate;
	}
	
	protected String getInstutionName() {
		return this.institutionName;
	}	
}
