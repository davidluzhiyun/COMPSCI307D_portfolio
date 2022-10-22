# Journal : Testing Reflection
#### NAME: David Lu
#### DATE: 10/22/2022


## Testing

#### Preferred ways to do testing
Using the JUnit5 assertions after finishing a class to test the public methods.

#### Incorporating automated testing into programming process
Easy: Testing as I go is less exhausting than having to write all the test when
I finish.
Hard: TDD is still hard to do for me to do because I can't seem to figure out all
the good and bad path beforehand. Also incorporated testing into the programming
process also means I sometimes need to change the test as I make change to my code.

#### Changed or improved debugging process
While sometimes I need to debug both the code and the test, testing has certainly made,
debugging easier by making problems easier to pinpoint to individual methods or smaller
units. Before, I often had to go through huge chunks of code and check line by line.

#### Changed or improved willingness to refactor code
Testing has changed my willingness to refactor code in two ways:
1. Now I am more willing to keep my code in small pieces to make testing easier.
2. Since refactoring afterwards often require updating the tests, I am now more prone to
   keep the code relatively clean in the beginning rather than having to clean and refactor
   later.

#### Assessing the quality of a test
I assess it mainly on how much of the paths or how general the cases it covers and how easy
it is to pinpoint the problem when it occurs

#### Coming up with "sad" path tests
One hard thing about coming up with "sad" path tests is that sometimes there is more than just
one single "sad" path. Instead, there might be a series of "sad" paths that can be handled with or
without throwing error, with some in the constructor and some in the methods. In these cases, it
might be hard to test all combinations of these paths.

#### Improving the testing process
I can improve my testing process by planning the tests more beforehand. The code quality of the
tests can also use some improvement. Also, I am not proficient in writing TestFX tests

#### Important of testing
It is becoming more important for me. By testing as I go, it provides reassurance to myself and
teammates as I work on larger projects.


## Google Homepage

1. Search something by clicking on the first prompt.
2. Search something by pressing enter.
3. Enter a working url and visit it by hitting return.
4. Enter a working url and click on the first prompt.
5. Enter a syntactically correct but not working url and hit return.
6. Enter a syntactically incorrect url and hit return.
7. Enter a mathematical expression and check the calculated result.
8. Place mouse over "I am feeling lucky", wait for the effect, then click.
9. Image search with working image link.
10. Image search with syntactically incorrect url.




## Ethics Regarding Using Programs and Models in Science
Upon reading https://hdsr.mitpress.mit.edu/pub/f9kuryi8/release/8, I have gained more insight into
the explainability problem. I will add two cases where black box AIs caused some problem.

One beauty filter app in China was mainly trained on Chinese. This caused lead to a biased dataset
and biased standard for beauty. As the company expands its service to the international market, the
App works poorly on black people and was accused of racism.

A separate case involves google translation. Many small media outlets in China wish to capitalize on
nationalist sentiments. One cheap method some such businesses employ to attract click is to feed
Google Translate mislabeled samples with Sinophobic messages to pollute it. They then make a news
out of the mistranslations with Sinophobic messages.

In the two examples, we can see that black box models might be vulnerable to unintentional biases
and intentional misuses. Therefore, making models more explainable is not only ethically more sound
as is described in the report, but also a practical need.