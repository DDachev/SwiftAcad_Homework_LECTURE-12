package bg.swift.HW12_Ex03;

import java.time.LocalDate;

public class SecondaryEducation extends GradedEducation{

	protected SecondaryEducation(LocalDate enrollmentDate, LocalDate graduationDate, String institutionName, double finalGrade) throws FinalGradeException, NoGradeException{
		super(enrollmentDate, graduationDate, institutionName, finalGrade);
	}

	@Override
	protected String getDegree() {
		return "Secondary";
	}

	@Override
	protected boolean gotGraduated() {
		return false;
	}
}
