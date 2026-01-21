
export interface Workout{
  name:string;
  description?:string;
  exercises:Exercises[];
}
export interface Exercises {
  name?: string;
  description: string;
  roleType?: string;
  footballGroup?: string;
  sets?: ExerciseSet[];
}

export interface ExerciseSet {
  drills?: number;
  repeats?: number;
}
export interface FootballGroup{
  name:string;
  exercises:Exercises[];
}
export const shooting: FootballGroup = {
  name: 'shooting',
  exercises: [
    {
      name: 'Chip shot',
      description: '',
      roleType: 'attacker',
      footballGroup: 'shooting',
    },
    {
      name: 'Power shot',
      description: '',
      roleType: 'attacker',
      footballGroup: 'shooting',
    },
    {
      name: 'Finesse shot',
      description: '',
      roleType: 'attacker',
      footballGroup: 'shooting',
    },
    {
      name: 'One on one',
      description: '',
      roleType: 'attacker',
      footballGroup: 'shooting',
    },
    {
      name: 'Trivela shot',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'shooting',
    },
  ]
}
export const celebrations: FootballGroup = {
  name: 'celebrations',
  exercises: [
    {
      name: 'Cristiano Ronaldo SIUUU',
      description: '',
      roleType: 'defender',
      footballGroup: 'celebrations',
    },
    {
      name: 'Dab on em',
      description: '',
      roleType: 'defender',
      footballGroup: 'celebrations',
    },
    {
      name: 'Shut the haters',
      description: '',
      roleType: 'attacker',
      footballGroup: 'celebrations',
    },
    {
      name: 'Sonic celebration',
      description: '',
      roleType: 'attacker',
      footballGroup: 'celebrations',
    },
    {
      name: 'Point to the sky',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'celebrations',
    },
  ]
}
export const penalties: FootballGroup = {
  name: 'penalties',
  exercises: [
    {
      name: 'Panenka',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'penalties',
    },
    {
      name: 'Bottom left corner',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'penalties',
    },
    {
      name: 'Bottom right corner',
      description: '',
      roleType: 'attacker',
      footballGroup: 'penalties',
    },
    {
      name: 'Top left corner',
      description: '',
      roleType: 'attacker',
      footballGroup: 'penalties',
    },
    {
      name: 'Top right corner',
      description: '',
      roleType: 'attacker',
      footballGroup: 'penalties',
    },
  ]
}
export const skills: FootballGroup = {
  name: 'skills',
  exercises: [
    {
      name: 'Berba spin',
      description: '',
      roleType: 'defender',
      footballGroup: 'skills',
    },
    {
      name: 'Elastico',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'skills',
    },
    {
      name: 'Antony spin',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'skills',
    },
    {
      name: 'Rainbow flick',
      description: '',
      roleType: 'attacker',
      footballGroup: 'skills',
    },
    {
      name: 'Stepovers',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'skills',
    },
  ]
}
export const defending: FootballGroup = {
  name: 'defending',
  exercises: [
    {
      name: 'Standing tackle',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'defending',
    },
    {
      name: 'Sliding tackle',
      description: '',
      roleType: 'attacker',
      footballGroup: 'defending',
    },
    {
      name: 'Mark the attacker',
      description: '',
      roleType: 'attacker',
      footballGroup: 'defending',
    },
    {
      name: 'Offside trap',
      description: '',
      roleType: 'attacker',
      footballGroup: 'defending',
    },
    {
      name: 'Clear the ball',
      description: '',
      roleType: 'defender',
      footballGroup: 'defending',
    },
  ]
}
export const dribbling: FootballGroup = {
  name: 'dribbling',
  exercises: [
    {
      name: 'Elastico dribble',
      description: '',
      roleType: 'goakeepeer',
      footballGroup: 'dribbling',
    },
    {
      name: 'Stepovers dribble',
      description: '',
      roleType: 'defender',
      footballGroup: 'dribbling',
    },
    {
      name: 'Body feint dribble',
      description: '',
      roleType: 'attacker',
      footballGroup: 'dribbling',
    },
    {
      name: 'Messi dribble',
      description: '',
      roleType: 'attacker',
      footballGroup: 'dribbling',
    },
    {
      name: 'Antony dribble',
      description: '',
      roleType: 'defender',
      footballGroup: 'dribbling',
    },
  ]
}
export const passing: FootballGroup = {
  name: 'passing',
  exercises: [
    {
      name: 'Short pass',
      description: '',
      roleType: 'defender',
      footballGroup: 'passing',
    },
    {
      name: 'Long pass',
      description: '',
      roleType: 'defender',
      footballGroup: 'passing',
    },
    {
      name: 'Through pass',
      description: '',
      roleType: 'defender',
      footballGroup: 'passing',
    },
    {
      name: 'Trivela pass',
      description: '',
      roleType: 'attacker',
      footballGroup: 'passing',
    },
    {
      name: 'No look pass',
      description: 'Description for leg raises',
      roleType: 'defender',
      footballGroup: 'passing',
    },
  ]
}
