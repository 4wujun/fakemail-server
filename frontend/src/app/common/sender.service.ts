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
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { throwError } from 'rxjs';
import { Observable } from 'rxjs/Observable';
import { catchError } from 'rxjs/operators/catchError';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Sender } from '../common/sender';

const senderUrl = 'api/sender';

@Injectable()
export class SenderService {

    constructor( private httpClient: HttpClient ) { }

    getSenders(): Observable<Sender[]> {
        return this.httpClient.get<Sender[]>( senderUrl )
            .catch( this.handleError );
    }

    private handleError( error: HttpResponse<any>) {
        let errMsg: string;
        const body = error.body.json() || '';
        const err = JSON.stringify( body );
        errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        console.error( errMsg );
        return throwError( errMsg );
    }
}
