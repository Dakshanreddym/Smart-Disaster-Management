package project.java.projectdisastermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="disaster_reports")
public class Disaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotBlank(message = "Full Name is required.")
    private String fullName;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits.")
    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    @Column(name="NicNumber")
    @NotBlank(message = "NIC Number is required.")
    private String nicNumber;

    @Email(message = "Invalid email address.")
    @NotBlank(message = "Email is required.")
    private String email;

    @Column(name="username")
    private String username;

    @NotBlank(message = "Address is required.")
    private String address;
    
    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Province province = Province.None;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private District district = District.None;

    @Column(name="GnDivison")
    @NotBlank(message = "Grama Niladhari Division is required.")
    private String gnDivision;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DisasterType disasterType = DisasterType.None;

    @NotNull(message = "Date and Time of disaster is required.")
    private LocalDateTime disasterDateTime;

    @Min(value = 1, message = "At least one individual must be affected.")
    private Integer affectedIndividuals;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.Pending;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity = Severity.Low;

    private String image;

    @NotBlank(message = "Impact description is required.")
    private String impact;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false, nullable = true)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    public Disaster(){

    }

    public Disaster(long id, String fullName, String nicNumber, String phoneNumber, String email, String address, double latitude, double longitude, Province province, District district, String gnDivision, DisasterType disasterType, LocalDateTime disasterDateTime, Integer affectedIndividuals, Status status, Severity severity, String image, String impact, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.nicNumber = nicNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.province = province;
        this.district = district;
        this.gnDivision = gnDivision;
        this.disasterType = disasterType;
        this.disasterDateTime = disasterDateTime;
        this.affectedIndividuals = affectedIndividuals;
        this.status = status;
        this.severity = severity;
        this.image = image;
        this.impact = impact;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Full Name is required.") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Full Name is required.") String fullName) {
        this.fullName = fullName;
    }

    public @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits.") @NotBlank(message = "Phone number is required.") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits.") @NotBlank(message = "Phone number is required.") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "NIC Number is required.") String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(@NotBlank(message = "NIC Number is required.") String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Email(message = "Invalid email address.") @NotBlank(message = "Email is required.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email address.") @NotBlank(message = "Email is required.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required.") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required.") String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public @NotBlank(message = "Grama Niladhari Division is required.") String getGnDivision() {
        return gnDivision;
    }

    public void setGnDivision(@NotBlank(message = "Grama Niladhari Division is required.") String gnDivision) {
        this.gnDivision = gnDivision;
    }

    public @NotNull(message = "Date and Time of disaster is required.") LocalDateTime getDisasterDateTime() {
        return disasterDateTime;
    }

    public void setDisasterDateTime(@NotNull(message = "Date and Time of disaster is required.") LocalDateTime disasterDateTime) {
        this.disasterDateTime = disasterDateTime;
    }

    public DisasterType getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(DisasterType disasterType) {
        this.disasterType = disasterType;
    }

    public @Min(value = 1, message = "At least one individual must be affected.") Integer getAffectedIndividuals() {
        return affectedIndividuals;
    }

    public void setAffectedIndividuals(@Min(value = 1, message = "At least one individual must be affected.") Integer affectedIndividuals) {
        this.affectedIndividuals = affectedIndividuals;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public @NotBlank(message = "Impact description is required.") String getImpact() {
        return impact;
    }

    public void setImpact(@NotBlank(message = "Impact description is required.") String impact) {
        this.impact = impact;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum Status{
        Pending, Reviewed, In_Progress, Resolved, Cancelled, Rejected
    }

    public enum Severity{
        Low, Mild, High
    }

    public enum DisasterType{
        None, Flood, Earthquake, Landslide, Tsunami, Cyclone, Fire, Drought, Epidemic
    }

    public enum Province{
        None, Central, Eastern, Southern, Nothern, NorthCentral, NorthWestern, Sabaragamuwa, Uva, Western
    }

    public enum District {
        None,
        Colombo, Gampaha, Kalutara,
        Kandy, Matale, Nuwara_Eliya,
        Galle, Matara, Hambantota,
        Trincomalee, Batticaloa, Ampara,
        Jaffna, Kilinochchi, Mannar, Vavuniya, Mullaitivu,
        Anuradhapura, Polonnaruwa,
        Kurunegala, Puttalam,
        Badulla, Monaragala,
        Ratnapura, Kegalle
    }


}
