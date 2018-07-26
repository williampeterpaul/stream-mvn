# twitter.influx

Maven based project that consumes Twitter's streaming API to store tweets containing at least one of a given set of keywords as influxDB metrics in real-time


## Prerequisites

[Maven](https://maven.apache.org/)

[JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

[InfluxDB](https://www.influxdata.com/)


and optionally
[Grafana](https://grafana.com/)


## Setup

To start streaming tweets first the developer must provide their API token and secret within the [api properties](https://github.com/williampeterpaul/twitter.influx/blob/master/twitterAPI.dev.properties).

Following that, the the [influxdb properties](https://github.com/williampeterpaul/twitter.influx/blob/master/influx.properties) must be populated with the location of the influxDb endpoint, auth credentials, and the data retention policy.

Lastly, provide a series of key words in [keywords.txt](https://github.com/williampeterpaul/twitter.influx/blob/master/keywords.txt) to act as the identifiers of tweets you wish to begin streaming


## Visualisation

Finally, to visualise these metrics the developer can install Grafana!

![](http://docs.grafana.org/assets/img/features/dashboard_ex1.png)


## Contribute

If you have any idea for an improvement or found a bug, do not hesitate to open an issue. 
And if you have time clone this repo and submit a pull request and help me make this project kickass!
