import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class UserData {
    private String name;
    private String jobTitle;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private WorkExperience[] workExperiences;
    private Education[] educations;
    private String[] skills;
    private Certification[] certifications;

    // Constructor
    public UserData(String name, String jobTitle, String email, String phone, String address, String summary, WorkExperience[] workExperiences, Education[] educations, String[] skills, Certification[] certifications) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.summary = summary;
        this.workExperiences = workExperiences;
        this.educations = educations;
        this.skills = skills;
        this.certifications = certifications;
    }

    public void generateCV() {
        try {
            // Update with the actual path to your HTML file
            String filePath = "template.html"; 
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            content = content.replace("{{Name}}", name);
            content = content.replace("{{JobTitle}}", jobTitle);
            content = content.replace("{{Email}}", email);
            content = content.replace("{{Phone}}", phone);
            content = content.replace("{{Address}}", address);
            content = content.replace("{{Summary}}", summary);

            String workExperiencesHtml = "";

            for (int i = 0; i < workExperiences.length; i++) {
                String workExperienceHtml = "<div class=\"job\">";

                workExperienceHtml += String.format("<p class=\"job-title\">%s</p>", workExperiences[i].jobTitle);
                workExperienceHtml += String.format("<p class=\"job-company\">%s</p>", workExperiences[i].company);
                workExperienceHtml += String.format("<p class=\"job-date\">%s</p>", workExperiences[i].jobDate);
                workExperienceHtml += "<ul>";
                for (String description : workExperiences[i].descriptions) {
                    workExperienceHtml += String.format("<li>%s</li>", description);
                }
                workExperienceHtml += "</ul>";
                workExperienceHtml += "</div>";

                workExperiencesHtml += workExperienceHtml;
            }

            content = content.replace("{{WorkExperiences}}", workExperiencesHtml);

            String educationsHtml = "";

            for (int i = 0; i < educations.length; i++) {
                String educationHtml = "<div class=\"education-entry\">";

                educationHtml += String.format("<p class=\"degree-name\">%s</p>", educations[i].major);
                educationHtml += String.format("<p class=\"institution-name\">%s</p>", educations[i].institutionName);
                educationHtml += String.format("<p class=\"education-date\">%s</p>", educations[i].educationDate);
                educationHtml += String.format("<p>%s</p>", educations[i].description);

                educationHtml += "</div>";

                educationsHtml += educationHtml;
            }

            content = content.replace("{{Educations}}", educationsHtml);

            String skillsHtml = "";

            for (String skill : skills) {
                skillsHtml += String.format("<li class=\"skill-item\">%s</li>", skill);
            }

            content = content.replace("{{Skills}}", skillsHtml);

            String certificationsHtml = "";

            for (int i = 0; i < certifications.length; i++) {
                certificationsHtml += String.format("<li><strong>%s</strong>, %s</li>", certifications[i].title, certifications[i].description);
            }

            content = content.replace("{{Certifications}}", certificationsHtml);

            String fileName = "CV_" + name.replaceAll(" ", "_");

            BufferedWriter writer = new BufferedWriter(new FileWriter("./" + fileName + ".html"));
            writer.write(content);
            writer.close();

            // Define the input HTML file and output PDF file
            String htmlFile = fileName + ".html";
            String pdfFile = fileName + ".pdf";

            // Command to execute
            String command = "";
            
            // Adjust page size if needed
            if (content.length() > 7200) {
                command = String.format("wkhtmltopdf --page-size A4 --orientation Portrait %s %s", htmlFile, pdfFile); 
            } else {
                command = String.format("wkhtmltopdf --page-size A5 --orientation Portrait %s %s", htmlFile, pdfFile);
            }
            
            System.err.println(content.length());

            try {
                // Execute the command
                ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
                processBuilder.redirectErrorStream(true); // Redirect error stream to output stream
                Process process = processBuilder.start();
    
                // Wait for the process to complete
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("HTML converted to PDF successfully: " + pdfFile);
                } else {
                    System.out.println("Conversion failed with exit code: " + exitCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WorkExperience {
    String jobTitle;
    String company;
    String jobDate;
    String[] descriptions;

    public WorkExperience(String jobTitle, String company, String jobDate, String[] descriptions) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobDate = jobDate;
        this.descriptions = descriptions;
    }
}

class Education {
    String major;
    String institutionName;
    String educationDate;
    String description;

    public Education(String major, String institutionName, String educationDate, String description) {
        this.major = major;
        this.institutionName = institutionName;
        this.educationDate = educationDate;
        this.description = description;
    }
}

class Certification {
    String title;
    String description;

    public Certification(String title, String description) {
        this.title = title;
        this.description = description;
    }
}