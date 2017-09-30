package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName;

    @Expose
    @Column(name = "middlename")
    private String middleName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Expose
    @Column(name = "nickname")
    private String nickName;

    @Expose
    @Column(name = "title")
    private String title;

    @Expose
    @Column(name = "company")
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column(name = "fax")
    @Type(type = "text")
    private String fax;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Expose
    @Column(name = "homepage")
    @Type(type = "text")
    private String webSite;

    @Expose
    @Column(name = "address2")
    @Type(type = "text")
    private String address2;

    @Expose
    @Column(name = "phone2")
    @Type(type = "text")
    private String homePhone2;

    @Expose
    @Column(name = "notes")
    @Type(type = "text")
    private String notes;

    @Expose
    @Transient
    private String group;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHomePhone2() {
        return homePhone2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public int getId() {
        return id;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withHomePhone2(String homePhone2) {
        this.homePhone2 = homePhone2;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;

    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        ContactData that = (ContactData) o;

        if (id != that.id) { return false; }
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) { return false; }
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) { return false; }
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) { return false; }
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) { return false; }
        if (title != null ? !title.equals(that.title) : that.title != null) { return false; }
        if (company != null ? !company.equals(that.company) : that.company != null) { return false; }
        if (address != null ? !address.equals(that.address) : that.address != null) { return false; }
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) { return false; }
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) { return false; }
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) { return false; }
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) { return false; }
        if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) { return false; }
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) { return false; }
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) { return false; }
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) { return false; }
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) { return false; }
        if (homePhone2 != null ? !homePhone2.equals(that.homePhone2) : that.homePhone2 != null) { return false; }
        return notes != null ? notes.equals(that.notes) : that.notes == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (homePhone2 != null ? homePhone2.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
