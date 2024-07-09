package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAdminSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentSessionId;

    @NotNull(message = "{CurrentAdmin.id.invalid}")
    @Min(value = 1000)
    @Max(value = 1010)
    private Integer adminId;

    @NotNull(message = "{CurrentAdmin.key.invalid}")
    @NotBlank(message = "{CurrentAdmin.key.invalid}")
    @NotEmpty(message = "{CurrentAdmin.key.invalid}")
    @Size(min = 6, max = 6, message = "{CurrentAdmin.key.invalid}")
    private String key;

    @NotNull(message = "{CurrentAdmin.date.invalid}")
    private LocalDateTime localDateTime;

    // Setter for adminId
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    // Setter for key
    public void setKey(String key) {
        this.key = key;
    }

    // Setter for localDateTime
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    // Add getters if needed
    public Integer getCurrentSessionId() {
        return currentSessionId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getKey() {
        return key;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
