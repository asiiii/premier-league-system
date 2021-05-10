import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FindMatchComponent } from './components/find-match/find-match.component';
import { MatchTableComponent } from './components/match-table/match-table.component';
import { PremierLeagueTableComponent } from './components/premier-league-table/premier-league-table.component';


const routes: Routes = [
  {path: '', component:PremierLeagueTableComponent},
  {path: 'match-table', component:MatchTableComponent},
  {path: 'find-match', component: FindMatchComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
