package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentSessionId;

    private Integer customerId;

    private String key;

    private LocalDateTime localDateTime;

    // Getter for customerId
    public Integer getCustomerId() {
        return customerId;
    }
    
    public String getKey() {
		return key;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	// Setter for key
    public void setKey(String key) {
        this.key = key;
    }
    
    // Setter for localDateTime
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    // Add setters and other getters as needed
}
