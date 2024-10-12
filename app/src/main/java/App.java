import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Use try-with-resources to ensure the Scanner is closed
        try (Scanner sc = new Scanner(System.in)) {
            System.err.print("Enter name: ");
            String name = sc.nextLine();

            System.err.print("Enter job title: ");
            String jobTitle = sc.nextLine();

            System.err.print("Enter email: ");
            String email = sc.nextLine();

            System.err.print("Enter phone: ");
            String phone = sc.nextLine();

            System.err.print("Enter address: ");
            String address = sc.nextLine();

            System.err.print("Enter summary: ");
            String summary = sc.nextLine();

            WorkExperience[] workExperiences = new WorkExperience[0];

            Boolean add = true;

            while (add) {
                System.err.print("\nWork experiences form\n\n");

                System.err.print("Enter job title: ");
                String workExperienceJobTitle = sc.nextLine();

                System.err.print("Enter company: ");
                String workExperienceCompany = sc.nextLine();

                System.err.print("Enter job date: ");
                String workExperienceJobDate = sc.nextLine();

                Boolean addDescription = true;
                List<String> workExperienceDescriptions = new ArrayList<>();
                while (addDescription) {
                    System.err.print("\nWork experience description\n\n");

                    System.err.print("Enter description: ");
                    String workExperienceDescription = sc.nextLine();

                    workExperienceDescriptions.add(workExperienceDescription);
                    System.err.print("Add another description? (y/n): ");
                    String addAnotherDescription = sc.nextLine();
                    if (addAnotherDescription.equalsIgnoreCase("n")) {
                        addDescription = false;
                    }
                }

                WorkExperience workExperience = new WorkExperience(workExperienceJobTitle, workExperienceCompany, workExperienceJobDate, workExperienceDescriptions.toArray(new String[0]));

                workExperiences = Arrays.copyOf(workExperiences, workExperiences.length + 1);
                workExperiences[workExperiences.length - 1] = workExperience;

                System.err.print("Add another work experience? (y/n): ");
                String addAnotherWorkExperience = sc.nextLine();
                if (addAnotherWorkExperience.equalsIgnoreCase("n")) {
                    add = false;
                }
            }

            Education[] educations = new Education[0];
            add = true;

            while (add) {
                System.err.print("\nAdd educations form\n\n");

                System.err.print("Enter major: ");
                String educationMajor = sc.nextLine();

                System.err.print("Enter institution name: ");
                String educationInstitutionName = sc.nextLine();

                System.err.print("Enter education date: ");
                String educationEducationDate = sc.nextLine();

                System.err.print("Enter education description: ");
                String educationDescription = sc.nextLine();

                Education education = new Education(educationMajor, educationInstitutionName, educationEducationDate, educationDescription);

                educations = Arrays.copyOf(educations, educations.length + 1);
                educations[educations.length - 1] = education;

                System.err.print("Add another education? (y/n): ");
                String addAnotherEducation = sc.nextLine();
                if (addAnotherEducation.equalsIgnoreCase("n")) {
                    add = false;
                }
            }

            List<String> skills = new ArrayList<>();
            add = true;

            while (add) {
                System.err.print("\nAdd skills form\n\n");

                System.err.print("Enter skill: ");
                String skill = sc.nextLine();

                skills.add(skill);

                System.err.print("Add another skill? (y/n): ");
                String addAnotherSkill = sc.nextLine();
                if (addAnotherSkill.equalsIgnoreCase("n")) {
                    add = false;
                }
            }

            Certification[] certifications = new Certification[0];
            add = true;

            while (add) {
                System.err.print("\nAdd certifications form\n\n");

                System.err.print("Enter certification title: ");
                String certificationTitle = sc.nextLine();

                System.err.print("Enter certification description: ");
                String certificationDescription = sc.nextLine();

                Certification certification = new Certification(certificationTitle, certificationDescription);

                certifications = Arrays.copyOf(certifications, certifications.length + 1);
                certifications[certifications.length - 1] = certification;

                System.err.print("Add another certification? (y/n): ");
                String addAnotherCertification = sc.nextLine();
                if (addAnotherCertification.equalsIgnoreCase("n")) {
                    add = false;
                }
            }

            UserData userData = new UserData(name, jobTitle, email, phone, address, summary, workExperiences, educations, skills.toArray(new String[0]), certifications);
            
            userData.generateCV();
        } // Scanner will be closed automatically here
    }
}