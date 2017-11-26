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
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { MailService } from '../common/mail.service';
import { MailCriteria } from '../common/mailCriteria';
import { SenderService } from '../common/sender.service';

@Component( {
    selector: 'app-search-form',
    templateUrl: './searchForm.component.html',
    providers: [MailService, SenderService]
})
export class SearchFormComponent implements OnInit {
    senderId: number;
    recipient: string;
    sentSince: Date;
    sentBefore: Date;
    senders: SelectItem[];
    errorMessage: string;
    @Output() onSearch = new EventEmitter<MailCriteria>();
    @Output() onReset = new EventEmitter<void>();

    constructor( private mailService: MailService, private senderService: SenderService ) { }

    ngOnInit() {
        this.loadSenders();
    }

    loadSenders() {
        this.senderService.getSenders().subscribe(
            senders => this.senders = senders,
            error => this.errorMessage = <any>error );
    }

    fireSearch() {
        const criteria = new MailCriteria();
        criteria.senderId = this.senderId;
        criteria.recipient = this.recipient;
        criteria.sentSince = this.sentSince;
        criteria.sentBefore = this.sentBefore;
        this.onSearch.emit(criteria);
    }

    fireReset() {
        this.senderId = null;
        this.recipient = null;
        this.sentSince = null;
        this.sentBefore = null;
        this.onReset.emit();
    }
}
