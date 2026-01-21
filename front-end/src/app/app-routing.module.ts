import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './home/home.component'
import {LoginComponent} from './login/login.component'
import { RegisterComponent } from './register/register.component';
import { StartupComponent } from './startup/startup.component';
import { MainComponent } from './main/main.component';
import { CreateworkoutComponent } from './createworkout/createworkout.component';
import { ShootingComponent } from './exercise-components/shooting/shooting.component';
import { CelebrationsComponent } from './exercise-components/celebrations/celebrations.component';
import { PenaltiesComponent } from './exercise-components/penalties/penalties.component';
import { SkillsComponent } from './exercise-components/skills/skills.component';
import { DribblingComponent } from './exercise-components/dribbling/dribbling.component';
import { FkicksComponent } from './exercise-components/fkicks/fkicks.component';
import { PassingComponent } from './exercise-components/passing/passing.component';
import { DefendingComponent } from './exercise-components/defending/defending.component';
import { WorkoutListComponent } from './workout-list/workout-list.component';
import {AuthGuard} from "../services/auth-guard";
import {OutAuthGuard} from "../services/out-auth-guard";

const routes: Routes = [
  {path:'', component:StartupComponent, canActivate: [AuthGuard]},
  {path:'startup', component: StartupComponent, canActivate: [AuthGuard]},
  { path:'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path:'register', component: RegisterComponent, canActivate: [AuthGuard] },
  {path:'home', component: HomeComponent, canActivate:[OutAuthGuard]},
  {path:'main', component: MainComponent, canActivate:[OutAuthGuard]},
  {path:'create-workout', component: CreateworkoutComponent, canActivate: [OutAuthGuard]},
  {path:'workout-list', component: WorkoutListComponent, canActivate: [OutAuthGuard]},

  {path:'shooting', component: ShootingComponent, canActivate: [OutAuthGuard]},
  {path:'celebrations', component: CelebrationsComponent, canActivate: [OutAuthGuard]},
  {path:'penalties', component: PenaltiesComponent, canActivate: [OutAuthGuard]},
  {path:'skills', component: SkillsComponent, canActivate: [OutAuthGuard]},
  {path:'defending', component: DefendingComponent, canActivate: [OutAuthGuard]},
  {path:'dribbling', component: DribblingComponent, canActivate: [OutAuthGuard]},
  {path:'fkicks', component: FkicksComponent, canActivate: [OutAuthGuard]},
  {path:'passing', component: PassingComponent, canActivate: [OutAuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
