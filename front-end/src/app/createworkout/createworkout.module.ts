import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CreateworkoutComponent } from './createworkout.component';
import { CelebrationsComponent } from '../exercise-components/celebrations/celebrations.component';
import { ShootingComponent } from '../exercise-components/shooting/shooting.component';
import { SkillsComponent } from '../exercise-components/skills/skills.component';
import { PenaltiesComponent } from '../exercise-components/penalties/penalties.component';
import { DefendingComponent } from '../exercise-components/defending/defending.component';
import { FkicksComponent } from '../exercise-components/fkicks/fkicks.component';
import { DribblingComponent } from '../exercise-components/dribbling/dribbling.component';
import { PassingComponent } from '../exercise-components/passing/passing.component';
import { CommonModule } from '@angular/common';
@NgModule({
  declarations: [
    CreateworkoutComponent,
    CelebrationsComponent,
    ShootingComponent,
    SkillsComponent,
    PenaltiesComponent,
    DefendingComponent,
    DribblingComponent,
    FkicksComponent,
    PassingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule
  ],
  exports: [
    CreateworkoutComponent,
  ]
})
export class CreateworkoutModule { }
