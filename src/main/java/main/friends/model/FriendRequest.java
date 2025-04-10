package main.friends.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(
    name = "subs",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "sub_id"})
)
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "sub_id", nullable = false)
    private Long subId;

}
