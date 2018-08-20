---
-- #%L
-- Fakemail server
-- %%
-- Copyright (C) 2017-2018 Patrice Le Gurun
-- %%
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Lesser General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Lesser Public License for more details.
-- 
-- You should have received a copy of the GNU General Lesser Public
-- License along with this program.  If not, see
-- <http://www.gnu.org/licenses/lgpl-3.0.html>.
-- #L%
---

insert into sender (version, date_created, last_updated, address) values (0, current_timestamp, current_timestamp, 'patrice@legurun.org');
insert into sender (version, date_created, last_updated, address) values (0, current_timestamp, current_timestamp, 'root@legurun.org');
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test', rawtohex('sldifjsodifuosdiufsoidfuou'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 2', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'patrice@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 3', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'root@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 4', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'patrice@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 5', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'patrice@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 6', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'root@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 7', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 8', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 9', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 10', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 11', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 12', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 13', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 14', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 15', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 16', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 17', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 18', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 19', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 20', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 21', rawtohex('sldifzdfzerezrezrzerzzerere'));
insert into email (version, date_created, last_updated, sender_id, recipient, sent_date, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', DATEADD('SECOND',RAND()*-(15*24*60*60),current_timestamp), 'Test 22', rawtohex('sldifzdfzerezrezrzerzzerere'));
