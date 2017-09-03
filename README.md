Fakemail server aims to act as a "blackhole" mail server.

[![Build Status](https://travis-ci.org/patlenain/fakemail-server.svg?branch=master)](https://travis-ci.org/patlenain/fakemail-server)

[![SonarQube Coverage](https://img.shields.io/sonar/https/sonarcloud.io/org.legurun.fakemailserver/coverage.svg)](https://sonarcloud.io/dashboard?id=org.legurun.fakemailserver)

[![License](https://img.shields.io/github/license/patlenain/fakemail-server.svg)](https://raw.githubusercontent.com/patlenain/fakemail-server/master/LICENSE.txt)

Each mail send to Fakemail is stored into database and never forwarded to legitimate recipients.
Those mails can be displayed on a web interface.

The main use is for testing software which sends mail, to forbid sending to real users.
