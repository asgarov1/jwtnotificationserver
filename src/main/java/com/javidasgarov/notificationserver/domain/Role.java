package com.javidasgarov.notificationserver.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Role extends AbstractPersistable<Long> {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<NotificationUser> users;

    public static Role ofUser() {
        Role role = new Role();
        role.setName(ROLE_USER);
        return role;
    }

    public static Role ofAdmin() {
        Role role = new Role();
        role.setName(ROLE_ADMIN);
        return role;
    }
}
