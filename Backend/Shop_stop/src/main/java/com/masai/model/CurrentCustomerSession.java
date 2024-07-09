package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentCustomerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentSessionId;

    @NotNull(message = "{CurrentCustomer.id.invalid}")
    private Integer customerId;

    @NotNull(message = "{CurrentCustomer.key.invalid}")
    @NotBlank(message = "{CurrentCustomer.key.invalid}")
    @NotEmpty(message = "{CurrentCustomer.key.invalid}")
    @Size(min = 6, max = 6, message = "{CurrentCustomer.key.invalid}")
    private String key;

    @NotNull(message = "{CurrentCustomer.date.invalid}")
    private LocalDateTime localDateTime;
    
    // Setters for customerId, key, and localDateTime
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    // Getter for customerId
    public Integer getCustomerId() {
        return customerId;
    }

    // Add setters and other getters as needed
}
