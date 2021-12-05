# CS_151_Math_Ship_Term_Project
# How to Navigate the Game (Version 1.0, rules subject change):
The game help screen is currently outdated...please read this file for final instructions.


## How to Play: 
1. Navigate ship using mouse cursor, left, right, up and down.
1. You have **three lives** portrayed i nthe top left screen as hearts. 
1. Try not to get hit by the meteors, otherwise, you lose a life.
1. Shoot using left click or right click.
1. Every time a meteor is shot, the score increments and the inequality changes.

* There is an inequality that **must not be breached** on the top of the screen. 
* This inequality is, by defualt, set to "1 < 50." 
* When the player's inequality surpasses 25 i.e **"26 < 50,"** then a lower bound for inequality appears that must be maintained. 
  - The default setting for this condition is: **"10 < x < 50,"** where x is the player's inequality value.
* Hiting each special meteor affects the inequality in a certain way.
* The ultimate goal is to preserve the inequality's True state.

* Below are 4 types of meteors which may affect the inequality when hit:
  - **Orange meteor:** multiplies the inequality value by 2, adds 50 to the score.   
  - **Purple meteor:** divides 1 from the left side, adds 1 to the score. 
  - **Green meteor:** subtracts 1 from the left side, adds 1 to the score. 
  - **Pink meteor:** adds 1 to the left side, adds 1 to the score

For every **100 meteors hit**, the speed of the meteor falling increases.
At score 1000, the player receives a **"megabullet"** for it's projectile (increasing the bullet height.) 
This "megabullet" has a score multiplier which multiplies any meteor's potential score by 2. 
Example: If an orange meteor gets hit, normally 50 score gets added, however if it gets hit by a megabullet, the 100 score gets added.

## Ending and Game Navigation:
* After the game ends, i.e one loses, a menu will prompt to:
   - click on SPACEBAR from your keyboard to go to the scoreboard or 
   - TAB to go to menu or
   - SHIFT to go to ship select.
   - click on mouse to play again.
* You can pause the game by clicking ESCAPE on your keyboard. While in puase menu:
   - ENTER to unpause
   - click on SPACEBAR from your keyboard to go to the scoreboard 
   - TAB to go to Main Menu 
   - SHIFT to go to ship select.
 
We plan to balance the game more, add more inequalities, and math objects (meteors) in the future constituting more gamemodes. 

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Major Credit:
* Major help was provided by the youtuber "Bro Code" (for tutorials), and "gasper coding."
* Game mechanics were modeled using the help of gaspercoding video: https://www.youtube.com/watch?v=0szmaHH1hno&t=78s 
* Database help from Wittcode Youtube Tutorials: https://www.youtube.com/watch?v=ltX5AtW9v30&ab_channel=WittCode 
