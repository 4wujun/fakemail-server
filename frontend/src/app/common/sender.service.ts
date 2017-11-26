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
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { SelectItem } from 'primeng/primeng';

const senderUrl = 'api/sender';

@Injectable()
export class SenderService {

    constructor( private http: Http ) { }

    getSenders(): Observable<SelectItem[]> {
        return this.http.get( senderUrl )
            .map( this.extract )
            .catch( this.handleError );
    }

    private extract( res: Response ) {
        const json = res.json();
        const result: SelectItem[] = [];
        result.push( { value: null, label: '' });
        if ( json ) {
            for ( const item of ( json.data || json ) ) {
                result.push( { value: item.id, label: item.address });
            }
        }
        return result;
    }

    private handleError( error: Response | any ) {
        let errMsg: string;
        if ( error instanceof Response ) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify( body );
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error( errMsg );
        return Observable.throw( errMsg );
    }
}
