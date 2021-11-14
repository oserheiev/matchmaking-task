1. To generate players use the following command:

`java -jar player-list-generator.jar players.json 1000 0 100`

Where:
 - players.json - file name
 - 1000 - number of players
 - 0 - from skill
 - 100 - to skill

2. To split players into teams, run the following command:

`java -jar matchmaking.jar players.json results.json 10 6`

Where:
- players.json - a file with a list of players in a queue to split
- results.json - a file to save results
- 10 - skill delta (quality criteria) within which 
players will be selected for a match.
Example: a player with 75 skill will be in a match only with players whose skill between 65 and 85 if skill delta is 10
- 6 - team size. So the match will have 6 * 2 = 12 players