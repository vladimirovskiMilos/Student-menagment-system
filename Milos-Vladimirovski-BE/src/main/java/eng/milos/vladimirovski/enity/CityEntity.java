package eng.milos.vladimirovski.enity;


import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "city")
public class CityEntity implements Serializable {


    @Id
    @Column(name = "zip_code")
    @NotNull
    @Range(min= 11000, max= 24000)
    private Long zipCode;
    @NotEmpty(message = " ")
    @Size(min = 2)
    @NotNull
    private String name;

    public CityEntity(Long zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public CityEntity() {
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity city = (CityEntity) o;
        return Objects.equals(zipCode, city.zipCode) && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, name);
    }

    @Override
    public String toString() {
        return "City{" +
                "zipCode='" + zipCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
