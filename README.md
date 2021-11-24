# Depth Charts for Sports
This program is for generating Depth Charts for player for any sports

Depth Charts for Sports Test
====================

Prerequisite
- Internet connect to download all dependencies like gradle
- Java 1.8
- Gradle (if not available then it will download)

How To Add new Sport:
============
<b>Step 1</b> : Add new Enum for all the position under ```com.sportsbet.depthchart.pojo``` and implements ```com.sportsbet.depthchart.pojo.Position```

<b>Step 2</b>: Add new Repo for data store and implements ```DepthChartsRepo```

<b>Step 3</b>: creates new object for ```DepthChartsService```
```java
//For MLBDepthChartsService
DepthCharts mlbDepthChartsService = new DepthChartsService(mlbDepthChartsRepo)

//For NFLDepthChartsService
DepthCharts nflDepthChartsService = new DepthChartsService(nflDepthChartsRepo)

//For CricketDepthChartsService
DepthCharts cricketDepthChartsService = new DepthChartsService(cricketDepthChartsRepo)
```



Assumptions
============
1) Player can be map to multiple position for same game.
2) Same player object can not be use for another type of game, 
   if one player is playing two games then it would be represented into two different object.
3) No other framework has been used (like Spring core) to avoid extra dependency for this small utility.
4) Player info and position details are stored into HashMap (memory) from repo class.
5) Only service class and data store class are provided.


Steps to run
============
1) Clone the repo
2) run ```./gradlew clean build```
3) to run junit ```./gradlew clean test```

