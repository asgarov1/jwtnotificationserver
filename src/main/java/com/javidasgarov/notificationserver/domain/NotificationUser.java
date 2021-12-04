package com.javidasgarov.notificationserver.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUser extends AbstractPersistable<Long> {

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date lastUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public NotificationUser(String username, String password) {
        this.username = username;
        this.password = password;
        status = Status.ACTIVE;
        lastUpdated = new Date();
        roles = List.of(Role.ofUser());
    }
}
