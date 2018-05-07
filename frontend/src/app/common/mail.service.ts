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
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { throwError } from 'rxjs';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/finally';

import { MailSearchResult, Mail } from './mail';
import { MailCriteria } from './mailCriteria';

const mailUrl = 'api/mail';

@Injectable()
export class MailService {

    constructor( private httpClient: HttpClient ) { }

    search( criteria: MailCriteria ): Observable<MailSearchResult> {
        return this.httpClient.post<MailSearchResult>( mailUrl, criteria );
    }

    private handleError( error: HttpResponse<any> | any ) {
        console.log(error);
        let errMsg: string;
        if ( error instanceof HttpResponse ) {
            const body = error.body.json() || '';
            const err = body.error || JSON.stringify( body );
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error( error );
        console.error( errMsg );
        return throwError( errMsg );
    }

    private finalize() {
    }
}
