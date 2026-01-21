package io.hattrick.backend.entity;

import io.hattrick.backend.payload.data.RoleType;
import io.hattrick.backend.payload.data.FootballGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = ExerciseEntity.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {ExerciseEntity.NAME_COLUMN, UserEntity.ID_COLUMN})
        }
)
public class ExerciseEntity {
    public static final String TABLE_NAME = "exercises";
    public static final String ID_COLUMN = "exercise_id";
    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String FOOTBALL_GROUP_COLUMN = "football_group";
    public static final String ROLE_TYPE_COLUMN = "role_type";
    public static final String URL_COLUMN = "url";

    private String id;
    private String name;
    private String description;
    private FootballGroup footballGroup;
    private RoleType roleType;
    private String url;
    private UserEntity user;
    private List<WorkoutEntity> workouts;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COLUMN, updatable = false, unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = NAME_COLUMN, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = DESCRIPTION_COLUMN)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = FOOTBALL_GROUP_COLUMN, nullable = false)
    public FootballGroup getFootballGroup() {
        return footballGroup;
    }

    public void setFootballGroup(FootballGroup footballGroup) {
        this.footballGroup = footballGroup;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = ROLE_TYPE_COLUMN, nullable = false)
    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Column(name = URL_COLUMN)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserEntity.ID_COLUMN, nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToMany(mappedBy = "exercises")
    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }
}
