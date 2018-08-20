/**
 * @license
 * Copyright (C) 2017-2018 Patrice Le Gurun
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
import { Component, OnInit } from '@angular/core';
import { DataTable, LazyLoadEvent } from 'primeng/primeng';


import { MailSearchResult, Mail } from '../common/mail';
import { MailCriteria } from '../common/mailCriteria';
import { MailService } from '../common/mail.service';


@Component( {
    selector: 'app-search-results',
    templateUrl: './searchResults.component.html',
    providers: [MailService]
} )
export class SearchResultsComponent implements OnInit {
    loading: boolean;
    readonly maxRows = 10;
    mails: Mail[];
    firstRowDisplayed: number;
    totalRecords: number;

    private criteria: MailCriteria;

    constructor( private mailService: MailService ) { }

    ngOnInit() {
        const criteria = new MailCriteria();
        criteria.firstRow = 0;
        criteria.maxRows = this.maxRows;
        this.onSearch( criteria );
    }

    onSearch( criteria: MailCriteria ) {
        this.criteria = criteria;
        this.search();
    }

    onReset() {
    }

    onLazyLoad( event: LazyLoadEvent ) {
        this.criteria.firstRow = event.first;
        this.search();
    }

    private search() {
        this.loading = true;
        this.mailService.search( this.criteria ).
            subscribe( result => {
                this.mails = result.content;
                this.firstRowDisplayed = ( result.number * result.size );
                this.totalRecords = result.totalElements;
                setTimeout(() => {
                    this.loading = false;
                }, 200 );
            } );
    }
}
