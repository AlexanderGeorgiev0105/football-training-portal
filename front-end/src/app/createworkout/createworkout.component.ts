import { Component } from '@angular/core';
import { Exercises, FootballGroup, Workout, shooting, skills, celebrations, penalties, defending, dribbling, passing } from '../models/exercise.model';
import {PostDataService} from "../../services/post-data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-createworkout',
  templateUrl: './createworkout.component.html',
  styleUrls: ['./createworkout.component.css']
})
export class CreateworkoutComponent {
  showCelebrationsExercise: boolean = false;
  showShootingExercise: boolean = false;
  showPenaltiesExercise: boolean = false;
  showSkillsExercise: boolean = false;
  showDefendingExercise: boolean = false;
  showDribblingExercise: boolean = false;
  showPassingExercise: boolean = false;

  constructor(private postDataService: PostDataService, private router : Router) {}
  toggleShootingExercise() {
    this.showShootingExercise = !this.showShootingExercise;
  }
  toggleCelebrationsExercise() {
    this.showCelebrationsExercise = !this.showCelebrationsExercise;
  }
  togglePenaltiesExercise() {
    this.showPenaltiesExercise = !this.showPenaltiesExercise;
  }
  toggleSkillsExercise() {
    this.showSkillsExercise = !this.showSkillsExercise;
  }
  toggleDefendingExercise() {
    this.showDefendingExercise = !this.showDefendingExercise;
  }
  toggleDribblingExercise() {
    this.showDribblingExercise = !this.showDribblingExercise;
  }
  togglePassingExercise() {
    this.showPassingExercise = !this.showPassingExercise;
  }

  shooting: FootballGroup = shooting;
  skills : FootballGroup = skills;
  celebrations: FootballGroup = celebrations;
  penalties: FootballGroup = penalties;
  defending: FootballGroup = defending;
  dribbling: FootballGroup = dribbling;
  passing: FootballGroup = passing;

  isNameProvided: boolean = false;
  selectedExercise: Exercises | null = null;
  selectedExercises: Exercises[] = [];
  workout: Workout = {name: '', exercises: [] =[]};

  onExerciseSelected(exercise: Exercises) {
    this.selectedExercise = exercise;

    const existingExerciseIndex = this.selectedExercises.findIndex(
      (selected) => selected.name === exercise.name
    );

    if (existingExerciseIndex !== -1) {
      this.selectedExercises.splice(existingExerciseIndex, 1);
      this.selectedExercises.push(exercise);
    } else if (exercise.sets && exercise.sets.every(set => set.drills === undefined)) {
      alert('Enter drills, please');
    } else {
      this.selectedExercises.push(exercise);
    }
  }

  submitWorkout() {
    this.selectedExercises = this.selectedExercises.filter(ex => ex.sets != null && ex.sets.length > 0);

    if (this.selectedExercises.length > 0) {
      const submittedWorkout: Workout = {
        name: this.workout.name,
        description: this.workout.description || '',
        exercises: this.selectedExercises,
      };

      this.postDataService.submitWorkout(submittedWorkout).subscribe(
        (response) => {
          console.log('Successfully submitted workout:', response);
          this.router.navigate(['/workout-list']);
        },
        (error) => {
          console.error('Error submitting workout:', error);
        }
      );
    } else {
      alert('Please make sure at least one exercise has valid sets');
    }
  }
  updateSubmitButtonState() {
    this.isNameProvided = this.workout.name != null && this.workout.name.trim() !== '';
  }
}
