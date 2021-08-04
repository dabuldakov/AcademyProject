package practice.document;

import javax.validation.constraints.NotBlank;
import java.util.Date;


public class DocumentDto {
    private int id;

    @NotBlank
    private String number;

    private Date expiryDate;

    public int getId() {
        return id;
    }

    public DocumentDto() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "DocumentDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
