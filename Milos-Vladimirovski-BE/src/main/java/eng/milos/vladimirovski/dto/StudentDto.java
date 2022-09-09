package eng.milos.vladimirovski.dto;

import eng.milos.vladimirovski.enity.CityEntity;

import javax.persistence.Column;

import javax.validation.constraints.*;

public class StudentDto implements Dto{

    private Long id;

    @Email(message = "Invalid email address")
    @Size(min = 3, message = "Email address mast have more then 3 characters")
    private String email;

    @Column(name = "index_number")
    @Size(min = 4, max = 4)
    @NotEmpty
    private String indexNumber;

    @Column(name = "index_Year")
    //@Size(min = 2000, max = 2100, message = "Index year mast be between 2000 an 2100 ")
    @Min(value = 2000 , message = "Index of the year must be higher than 1999")
    @Max(value = 2100 , message = "Index of the year must be less than 2101")
    @NotNull
    private Long indexYear;

    @Size(min = 3, message = "The name must have more than 2 characters")
    @Column(name = "first_name")
    @NotEmpty
    private String firstName;


    @Size(min = 3, message = "The last name must have more than 2 characters")
    @Column(name = "last_name")
    @NotEmpty
    private String lastName;
    private CityEntity city;


    @Size(min = 3, message = "Address mast have 3 characters")
    private String address;



    @NotNull
    private Long currentYearOfStudy;

    public StudentDto() {
    }

    public StudentDto(Long id, String email, String indexNumber, Long indexYear, String firstName, String lastName, CityEntity city, Long currentYearOfStudy, String address) {

        this.id = id;
        this.email = email;
        this.indexNumber = indexNumber;
        this.indexYear = indexYear;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.currentYearOfStudy = currentYearOfStudy;
        this.address = address;
    }
    public StudentDto(String email, String indexNumber, Long indexYear, String firstName, String lastName, CityEntity city, Long currentYearOfStudy, String address) {


        this.email = email;
        this.indexNumber = indexNumber;
        this.indexYear = indexYear;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.currentYearOfStudy = currentYearOfStudy;
        this.address = address;
    }

    public StudentDto(String email, String indexNumber, Long indexYear, String firstName, String lastName, CityEntity city, Long currentYearOfStudy) {
        this.email = email;
        this.indexNumber = indexNumber;
        this.indexYear = indexYear;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.currentYearOfStudy = currentYearOfStudy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Long getIndexYear() {
        return indexYear;
    }

    public void setIndexYear(Long indexYear) {
        this.indexYear = indexYear;
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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Long getCurrentYearOfStudy() {
        return currentYearOfStudy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCurrentYearOfStudy(Long currentYearOfStudy) {
        this.currentYearOfStudy = currentYearOfStudy;
    }




    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", indexYear=" + indexYear +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city=" + city +
                ", currentYearOfStudy=" + currentYearOfStudy +
                '}';
    }
}
