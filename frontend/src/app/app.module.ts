/**
 * @license
 * Copyright (C) 2017 Patrice Le Gurun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { SharedModule } from 'primeng/primeng';
import { PanelModule, ButtonModule, InputTextModule } from 'primeng/primeng';
import { DropdownModule, ToolbarModule } from 'primeng/primeng';
import { CalendarModule, DataTableModule } from 'primeng/primeng';

import { AppComponent } from './app.component';
import { SearchFormComponent } from './searchForm/searchForm.component';
import { SearchResultsComponent } from './searchResults/searchResults.component';
import { requestOptionsProvider } from './common/default-http-options.service';

import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { SenderData } from './data/sender-data';

import { environment } from '../environments/environment';

@NgModule( {
    declarations: [
        AppComponent,
        SearchFormComponent,
        SearchResultsComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        BrowserAnimationsModule,
        ( !environment.production && !environment.proxyApi ) ? InMemoryWebApiModule.forRoot( SenderData ) : [],
	SharedModule,
        PanelModule,
        ButtonModule,
        InputTextModule,
        DropdownModule,
        ToolbarModule,
        CalendarModule,
        DataTableModule
    ],
    providers: [requestOptionsProvider],
    bootstrap: [AppComponent]
})
export class AppModule { }
