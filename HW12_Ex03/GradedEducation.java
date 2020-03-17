package bg.swift.HW12_Ex03;

import java.time.LocalDate;

public abstract class GradedEducation extends Education {
	private double finalGrade;

	protected GradedEducation(LocalDate enrollmentDate, LocalDate graduationDate, String institutionName,
			double finalGrade) throws FinalGradeException, NoGradeException {
		super(enrollmentDate, graduationDate, institutionName);
			if (super.isGraduated()) {
				this.finalGrade = finalGrade;
			}
	}

	protected void gotGraduated(float finalGrade) {
		this.finalGrade = finalGrade;
	}

	protected double getFinalGrade() throws FinalGradeException{
		if (this.finalGrade >= 3 && this.finalGrade <= 6) {
			return this.finalGrade;
		}
		throw new FinalGradeException();
	}
}
