Fakemail server aims to act as a "blackhole" mail server.

[![Build Status](https://travis-ci.org/patlenain/fakemail-server.svg?branch=master)](https://travis-ci.org/patlenain/fakemail-server)
[![SonarCloud Quality](https://sonarcloud.io/api/project_badges/measure?project=org.legurun.fakemailserver&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.legurun.fakemailserver)
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.legurun.fakemailserver&metric=coverage)](https://sonarcloud.io/dashboard?id=org.legurun.fakemailserver)
[![License](https://img.shields.io/github/license/patlenain/fakemail-server.svg)](LICENSE.txt)

Each mail send to Fakemail is stored into database and never forwarded to legitimate recipients.
Those mails can be displayed on a web interface.

The main use is for testing software which sends mail, to forbid sending to real users.
