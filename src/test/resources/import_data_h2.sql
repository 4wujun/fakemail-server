
insert into sender (version, date_created, last_updated, address) values (0, current_timestamp, current_timestamp, 'patrice@legurun.org');
insert into email (version, date_created, last_updated, sender_id, recipient, date_sent, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', current_timestamp, 'Test', rawtohex('sldifjsodifuosdiufsoidfuou'));
insert into email (version, date_created, last_updated, sender_id, recipient, date_sent, subject, message) values (0, current_timestamp, current_timestamp, (select id from sender where address = 'patrice@legurun.org'), 'toto@legurun.org', current_timestamp, 'Test 2', rawtohex('sldifzdfzerezrezrzerzzerere'));
