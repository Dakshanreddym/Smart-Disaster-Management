package project.java.projectdisastermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 6, message = "Username must be at least 6 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{5,19}$",
            message = "Username must be 6-20 characters long, start with a letter and can include letters, numbers, dots and underscores")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    private String location;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "role")
    private String role = "USER";

    public User(){

    }

    public User(long id, String name, String email, String username, String location, String password, double latitude, double longitude, boolean enabled, LocalDateTime updatedAt, LocalDateTime createdAt, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.location = location;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
        this.enabled = enabled;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Email(message = "Invalid email address") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email address") String email) {
        this.email = email;
    }

    public @Size(min = 6, message = "Username must be at least 8 characters") @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{5,19}$",
            message = "Username must be 6-20 characters long, start with a letter and can include letters, numbers, dots and underscores") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 6, message = "Username must be at least 8 characters") @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{5,19}$",
            message = "Username must be 6-20 characters long, start with a letter and can include letters, numbers, dots and underscores") String username) {
        this.username = username;
    }

    public @Size(min = 8, message = "Password must be at least 8 characters") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, message = "Password must be at least 8 characters") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character") String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
