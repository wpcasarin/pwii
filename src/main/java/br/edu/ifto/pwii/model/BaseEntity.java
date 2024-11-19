package br.edu.ifto.pwii.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        if (this.deletedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void softDelete() {
        if (this.deletedAt == null) {
            var now = LocalDateTime.now();
            this.deletedAt = now;
            this.updatedAt = now;
        }
    }
    
}
