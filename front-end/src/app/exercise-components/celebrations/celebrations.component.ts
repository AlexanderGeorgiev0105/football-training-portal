import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ExerciseSet, Exercises, celebrations } from '../../models/exercise.model';

@Component({
  selector: 'app-celebrations',
  templateUrl: './celebrations.component.html',
  styleUrls: ['./celebrations.component.css']
})
export class CelebrationsComponent {
  @Input() celebrationsExercises: Exercises[] = [];
  @Output() exerciseSelected = new EventEmitter<Exercises>();
  @Input() selectedExercises: Exercises[] = [];
  selectedExercise: Exercises | null = null;
  workoutDrills: string = '';
  workoutSets: string = '';
  workoutRepeats: string = '';

  addSet() {
    if (this.selectedExercise && this.workoutDrills) {
      const newSet: ExerciseSet = {
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats)
      };

      if (!this.selectedExercise.sets) {
        this.selectedExercise.sets = [];
      }

      this.selectedExercise.sets.push(newSet);

      this.workoutDrills = '';
      this.workoutRepeats = '';
    }
    else{
      alert('Please select an exercise and add repeats and drills to it');
    }
  }

  editSet(set: ExerciseSet, index: number) {
    if (this.selectedExercise && this.selectedExercise.sets && this.workoutDrills && this.workoutRepeats) {
      const updatedSet: ExerciseSet = {
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats)
      };

      this.selectedExercise.sets[index] = updatedSet;
      this.workoutDrills = '';
      this.workoutRepeats = '';
    }
  }

  deleteSet(set: ExerciseSet) {
    if (this.selectedExercise && this.selectedExercise.sets) {
      this.selectedExercise.sets = this.selectedExercise.sets.filter(s => s !== set);
    }
  }

  selectExercise(exercise: Exercises | null) {
    if (exercise) {
      this.selectedExercise = exercise;
      this.exerciseSelected.emit(exercise);
    } else {
      this.selectedExercise = null;
    }
  }

  addExercise() {
    if (this.selectedExercise && this.workoutDrills) {
      const newExercise: Exercises = {
        name: this.selectedExercise.name,
        description: this.selectedExercise.description,
        roleType: this.selectedExercise.roleType,
        sets: this.selectedExercise.sets || [],
      };

      if (!newExercise.sets) {
        newExercise.sets = [];
      }

      newExercise.sets.push({
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats),
      });

      this.selectedExercises.push(newExercise);

      this.selectedExercise = null;
      this.workoutDrills = '';
      this.workoutSets = '';
    }
  }
}
