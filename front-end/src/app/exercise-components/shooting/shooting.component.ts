import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ExerciseSet, Exercises } from 'src/app/models/exercise.model';

@Component({
  selector: 'app-shooting',
  templateUrl: './shooting.component.html',
  styleUrls: ['./shooting.component.css']
})
export class ShootingComponent {
  @Input() shootingExercises: Exercises[] = [];
  @Output() exerciseSelected = new EventEmitter<Exercises>();
  @Input() selectedExercises: Exercises[] = [];
  selectedExercise: Exercises | null = null;
  workoutDrills: string = '';
  workoutSets: string = '';
  workoutRepeats: string = '';

  addSet() {
    // Add a set to the selected exercise
    if (this.selectedExercise && this.workoutDrills && this.workoutRepeats) {
      const newSet: ExerciseSet = {
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats)
      };

      if (!this.selectedExercise.sets) {
        this.selectedExercise.sets = [];
      }

      this.selectedExercise.sets.push(newSet);

      // Clear the drills and repeats after adding a set
      this.workoutDrills = '';
      this.workoutRepeats = '';
    }
    else{
      alert('Please select an exercise and add repeats and drills to it');
    }
  }

  editSet(set: ExerciseSet, index: number) {
    // Implement editing logic for a set
    // You may open a modal or use an inline form for editing
    if (this.selectedExercise && this.selectedExercise.sets && this.workoutDrills && this.workoutRepeats) {
      // Assuming workoutDrills and workoutRepeats are the updated values from the input boxes
      const updatedSet: ExerciseSet = {
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats)
      };

      // Replace the set at the specified index with the updated set
      this.selectedExercise.sets[index] = updatedSet;

      // Clear the drills and repeats after editing
      this.workoutDrills = '';
      this.workoutRepeats = '';
    }
  }

  deleteSet(set: ExerciseSet) {
    // Remove a set from the selected exercise
    if (this.selectedExercise && this.selectedExercise.sets) {
      this.selectedExercise.sets = this.selectedExercise.sets.filter(s => s !== set);
    }
  }

  selectExercise(exercise: Exercises | null) {
    // Handle null case or emit the event with the selected exercise
    if (exercise) {
      this.selectedExercise = exercise;
      this.exerciseSelected.emit(exercise);
    } else {
      this.selectedExercise = null;
    }
  }

  addExercise() {
    // Add the selected exercise and its drills to the array
    if (this.selectedExercise && this.workoutDrills) {
      const newExercise: Exercises = {
        name: this.selectedExercise.name,
        description: this.selectedExercise.description,
        roleType: this.selectedExercise.roleType,
        sets: this.selectedExercise.sets || [],
      };

      // Ensure that 'sets' is defined
      if (!newExercise.sets) {
        newExercise.sets = [];
      }

      // Add the new set
      newExercise.sets.push({
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats),
      });

      this.selectedExercises.push(newExercise);

      // Clear the selected exercise and drills after adding
      this.selectedExercise = null;
      this.workoutDrills = '';
      this.workoutSets = '';
    }
  }
}
