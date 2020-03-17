package bg.swift.HW12_Ex03;

import java.time.LocalDate;
import java.util.ArrayList;

public class Person {

	private String firstName;
	private String middleName;
	private String lastName;
	private Gender gender;
	private LocalDate dateOfBirth;
	private short height;
	private EducationDegree codeOfEducation;
	protected ArrayList<Education> ed;
	protected ArrayList<Address> pastAddresses;
	protected Address currentAddress;

	protected Person(String firstName, String middleName, String lastName, Gender gender, LocalDate dateOfBirth,
			EducationDegree codeOfEducation, short height, String country, String city, String municipality,
			int postalCode, String street, int number, int floor, int apartamentNo, LocalDate enrollmentDate,
			LocalDate graduationDate, String institutionName, double finalGrade) {
		try {
			if (checkNamesOfPerson(firstName, middleName, lastName)) {
				this.firstName = firstName;
				this.middleName = middleName;
				this.lastName = lastName;
			}
			if (gender != null) {
				this.gender = gender;
			}
			if (checkDateOfBirth(dateOfBirth)) {
				this.dateOfBirth = dateOfBirth;
			}
			if (codeOfEducation != null) {
				this.codeOfEducation = codeOfEducation;
			}
			if (checkHeight(height)) {
				this.height = height;
			}
			this.currentAddress = new Address(country, city, municipality, postalCode, street, number, floor,
					apartamentNo);
			this.pastAddresses = new ArrayList<>();
			this.pastAddresses.add(currentAddress);
			if (checkInstitutionName(institutionName)) {
				this.ed = new ArrayList<>();
				if (this.codeOfEducation == EducationDegree.PRIMARY) {
					this.ed.add(new PrimaryEducation(enrollmentDate, graduationDate, institutionName));
				} else if (this.codeOfEducation == EducationDegree.SECONDARY) {
					this.ed.add(new SecondaryEducation(enrollmentDate, graduationDate, institutionName, finalGrade));
				} else if (this.codeOfEducation == EducationDegree.BACHELOR
						|| this.codeOfEducation == EducationDegree.MASTER
						|| this.codeOfEducation == EducationDegree.DOCTORATE) {
					this.ed.add(new HigherEducation(enrollmentDate, graduationDate, institutionName, finalGrade));
				}
			}
		} catch (EmptyNameException e) {
			System.out.println("Expected non-empty <first,last,...> name.");
		} catch (DateOfBirthException e) {
			System.out.println("Date of birth is expected to be after 01.01.1900 and before now.");
		} catch (HeightException e) {
			System.out.println("Expected height is between 40 and 300 cm.");
		} catch (FinalGradeException e) {
			System.out.println("Graduation grade is expected to be between 2 and 6.");
		} catch (NoGradeException e) {
			System.out.println("No final grade can be provided before graduation");
		}
	}

	@Override
	public String toString() {
		int lastIndex = this.ed.size() - 1;
		String personalPronoun = "";
		String objectPronoun = "";

		try {
			if (this.gender == Gender.MAlE) {
				personalPronoun = "He";
				objectPronoun = "His";
			} else {
				personalPronoun = "She";
				objectPronoun = "Her";
			}
			String firstLine = String.format("%s %s %s is %d years old. %s was born in %d.%n", this.firstName,
					this.middleName, this.lastName, this.getAgeOfPerson(), personalPronoun,
					this.getDateOfBirth().getYear());
			if (!this.isUnderAge(dateOfBirth)) {
				String isUnderAged = String.format("%s %s %s is under-aged.%n", this.firstName, this.middleName,
						this.lastName);
				firstLine = firstLine + isUnderAged;
			}

			String secondLine = String.format(
					"%s " + this.currentAddress.toString() + "%s started %s degree in %s on "
							+ this.ed.get(lastIndex).getEnrollmentData() + " and ",
					personalPronoun, personalPronoun, this.getDegree().toString().toLowerCase(),
					this.ed.get(lastIndex).getInstutionName());
			firstLine = firstLine + secondLine;
			
			if (this.ed.get(lastIndex).isGraduated()) {
				String lastLine = String.format(
						"finished on " + this.ed.get(lastIndex).getGraduationDate() + ". %s grade was %.3f.",
						objectPronoun, ((GradedEducation) this.ed.get(lastIndex)).getFinalGrade());
				firstLine = firstLine + lastLine;

			} else {
				String lastLine = String
						.format("is supposed to graduate on " + this.ed.get(lastIndex).getGraduationDate() + ".");
				firstLine = firstLine + lastLine;
			}
			return firstLine;
		} catch (FinalGradeException e) {
			System.out.println("Graduation grade is expected to be between 2 and 6.");
		} catch (NoGradeException e) {
			System.out.println("No final grade can be provided before graduation");
		}
		return "Cannot create a person with this charachteristics.";
	}

	public void addEducation(char codeOfEducation, LocalDate enrollmentDate, LocalDate graduationDate,
			String institutionName, double finalGrade) throws NoGradeException, FinalGradeException {
		if (this.codeOfEducation == EducationDegree.PRIMARY) {
			this.ed.add(new PrimaryEducation(enrollmentDate, graduationDate, institutionName));
		} else if (this.codeOfEducation == EducationDegree.SECONDARY) {
			this.ed.add(new SecondaryEducation(enrollmentDate, graduationDate, institutionName, finalGrade));
		} else if (this.codeOfEducation == EducationDegree.BACHELOR || this.codeOfEducation == EducationDegree.MASTER
				|| this.codeOfEducation == EducationDegree.DOCTORATE) {
			this.ed.add(new HigherEducation(enrollmentDate, graduationDate, institutionName, finalGrade));
		}
	}

	public void deleteEducation(Education education) {
		if (education != null) {
			this.ed.remove(education);
			return;
		}
	}

	private static boolean checkDateOfBirth(LocalDate dateOfBirth) throws DateOfBirthException {
		if (dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now())) {
			throw new DateOfBirthException();
		}
		return true;
	}

	private static boolean checkInstitutionName(String name) throws EmptyNameException {
		if (name != null && !name.trim().isEmpty()) {
			return true;
		}
		throw new EmptyNameException();
	}

	private static boolean checkNamesOfPerson(String firstName, String middleName, String lastName)
			throws EmptyNameException {
		if (firstName != null && !firstName.trim().isEmpty() && middleName != null && !middleName.trim().isEmpty()
				&& lastName != null && !lastName.trim().isEmpty()) {
			return true;
		}
		throw new EmptyNameException();
	}
	
	private static boolean checkHeight(short height) throws HeightException{
		if(height >= 40 && height <= 300) {
			return true;
		}
		throw new HeightException();
	}
	
	public boolean isUnderAge(LocalDate dateOfBirth) {
		LocalDate dateNow = LocalDate.now();
		if (dateNow.minusYears(18).compareTo(dateOfBirth) >= 0) {
			return true;
		}
		return false;
	}

	public int getAgeOfPerson() {
		LocalDate dateNow = LocalDate.now();
		int ageOfPerson = dateNow.compareTo(this.dateOfBirth);
		boolean yearIsAfter = this.dateOfBirth.plusYears(dateNow.getYear() - this.dateOfBirth.getYear())
				.isAfter(dateNow);

		if (yearIsAfter) {
			return ageOfPerson - 1;
		}
		return ageOfPerson;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public short getHeight() {
		return height;
	}

	public EducationDegree getDegree() {
		return codeOfEducation;
	}
}
