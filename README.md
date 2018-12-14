Ibotta Dev Project
=========
The purpose of this API is to perform fast searches for [anagrams](https://en.wikipedia.org/wiki/Anagram). The initial dictionary of words is loaded after startup by ingesting `dictionary.txt`. 

## Getting Started

####Clone the repository
`https://github.com/dmcglock/anagrams.git`

####Install Maven (if not already installed)
[Maven install](https://maven.apache.org/install.html)

####Navigate to project directory
`<some directory>/anagrams`

####Unit tests
You can choose to run the unit tests through `mvn clean install`

####Start Spring Boot application
`mvn spring-boot:run` <br/>

####Wait for dictionary to load
After startup a log message will indicate `dictionary.txt` has been processed
Example: `Dictionary words processed: 235886 out of 235886`

####Run API Tests
Make sure Ruby is installed <br/>
Run `ruby anagram_test.rb` <br/>
NOTE: Current version of tests does delete dictionary stored after start up

## API 

#### POST /words.json
Takes a JSON array of English-language words and adds them to the corpus (data store). <br/>
Request: `localhost:3000/words.json` <br/>
Body: `{ "words": ["read", "odor", "alter", "tears"] }` <br/>
Response: `[
               {
                   "word": "read",
                   "wordAlphabetical": "ader",
                   "properNoun": false
               },
               {
                   "word": "odor",
                   "wordAlphabetical": "door",
                   "properNoun": false
               },
               {
                   "word": "alter",
                   "wordAlphabetical": "aelrt",
                   "properNoun": false
               },
               {
                   "word": "tears",
                   "wordAlphabetical": "aerst",
                   "properNoun": false
               }
           ]`
           
#### GET anagrams/{word}.json
Returns a JSON array of English-language words that are anagrams of the word passed in the URL. <br/>
Optional Query Param `limit` to indicate maximum number of results to return <br/>
Optional Query Param `includeProperNouns` to remove proper nouns from list set to false 
Request: `localhost:3000/anagrams/read.json?limit=2&includeProperNouns=true` <br/>
Response: `{
               "anagrams": [
                   "ared",
                   "daer"
               ]
           }` <br/>

#### DELETE /words/{word}.json
Deletes a single word from the data store.
Returns a 204 on completion

#### DELETE /words.json
Deletes all contents of the data store.
Returns a 204 on completion

## Optional
#### GET /count-words
Endpoint that returns a count of words in the corpus and min/max/median/average word length <br/>
Optional Query Param `includeProperNouns` that will filter proper nouns when set to false

Request: `localhost:3000/count-words?includeProperNouns=false` <br/>
Response: `{
               "wordCount": 4,
               "minWordLength": 4,
               "maxWordLength": 5,
               "medianWordLength": 4,
               "averageWordLength": 4.5
           }`
           
#### GET /anagrams/check
Endpoint that takes a set of words and returns whether or not they are all anagrams of each other <br/>
Request: `localhost:3000/anagrams/check?words=tea, ate` <br/>
Response: `{
               "words": [
                   "tea",
                   "ate"
               ],
               "anagrams": true
           }`
#### GET /anagrams/check
Endpoint to return all anagram groups of size >= *x* <br/>
Request: `localhost:3000/anagrams/groups?size=3` <br/>
Response: `{
               "adekn": [
                   "kande",
                   "knead",
                   "naked"
               ],
               "addeeglnr": [
                   "gladdener",
                   "glandered",
                   "regladden"
               ]
            }`
#### DELETE /anagrams/read
Endpoint to delete a word *and all of its anagrams <br/>
Request: `localhost:3000/anagrams/read` <br/>
Response: 204 No content


##



