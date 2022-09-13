package kopo.poly.repository.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "USER_INFO")
@Builder
@Entity
public class UserInfoEntity {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @NonNull
    @Column(name = "USER_NAME", length = 500, nullable = false)
    private String userName;

    @NonNull
    @Column(name = "PASSWORD", length = 1, nullable = false)
    private String password;

    @NonNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NonNull
    @Column(name = "ADDR1", nullable = false)
    private String addr1;
    @NonNull
    @Column(name = "ADDR2", nullable = false)
    private String addr2;

    @NonNull
    @Column(name = "REG_ID", updatable = false)
    private String regId;

    @NonNull
    @Column(name = "REG_DT", updatable = false)
    private String regDt;

    @NonNull
    @Column(name = "CHG_ID")
    private String chgId;

    @NonNull
    @Column(name = "CHG_DT")
    private String chgDt;
}
