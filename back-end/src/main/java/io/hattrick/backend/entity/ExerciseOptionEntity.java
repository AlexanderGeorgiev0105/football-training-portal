package io.hattrick.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = ExerciseOptionEntity.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {ExerciseEntity.ID_COLUMN, WorkoutEntity.ID_COLUMN})
        })
public class ExerciseOptionEntity {
    public static final String TABLE_NAME = "exercise_options";
    public static final String ID_COLUMN = "exercise_options_id";
    public static final String SETS_COLUMN = "sets";
    public static final String DRILLS_COLUMN = "drills";
    public static final String REPEATS_COLUMN = "repeats";

    private String id;
    private ExerciseEntity exercise;
    private WorkoutEntity workout;
    private Integer sets;
    private Integer drills;
    private Integer repeats;

    public ExerciseOptionEntity(ExerciseOptionEntity entity) {
        this.id = entity.getId();
        this.exercise = entity.getExercise();
        this.workout = entity.getWorkout();
        this.sets = entity.getSets();
        this.drills = entity.getDrills();
        this.repeats = entity.getRepeats();
    }

    public ExerciseOptionEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COLUMN, updatable = false, unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = ExerciseEntity.ID_COLUMN, nullable = false)
    public ExerciseEntity getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

    @ManyToOne
    @JoinColumn(name = WorkoutEntity.ID_COLUMN, nullable = false)
    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }

    @Column(name = SETS_COLUMN, nullable = false)
    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    @Column(name = DRILLS_COLUMN, nullable = false)
    public Integer getDrills() {
        return drills;
    }

    public void setDrills(Integer drills) {
        this.drills = drills;
    }

    @Column(name = REPEATS_COLUMN, nullable = false)
    public Integer getRepeats() {
        return repeats;
    }

    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }
}
