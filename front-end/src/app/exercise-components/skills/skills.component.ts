import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ExerciseSet, Exercises } from 'src/app/models/exercise.model';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent {
  @Input() skillsExercises: Exercises[] = [];
  @Output() exerciseSelected = new EventEmitter<Exercises>();
  @Input() selectedExercises: Exercises[] = [];
  selectedExercise: Exercises | null = null;
  workoutDrills: string = '';
  workoutSets: string = '';
  workoutRepeats: string = '';

  addSet() {
    // Add a set to the selected exercise
    if (this.selectedExercise && this.workoutDrills) {
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
    if (this.selectedExercise && this.selectedExercise.sets && this.workoutDrills && this.workoutRepeats) {
      // Assuming workoutDrills and workoutRepeats are the updated values from the input boxes
      const updatedSet: ExerciseSet = {
        drills: parseInt(this.workoutDrills),
        repeats: parseInt(this.workoutRepeats)
      };

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
      this.workoutDrills = ''; // Reset drills value
      this.workoutRepeats = ''; // Reset repeats value
      this.exerciseSelected.emit(exercise);
    } else {
      this.selectedExercise = null;
    }
  }
}
