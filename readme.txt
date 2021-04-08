Steps to run the project

1. Go to repository: https://github.com/aalnct/ThinkMarketsSpringBoot
2. copy either https or SSH path
3. run command: git clone <path_to_project_in_git>
4. this will checkout the master branch (there is only one branch right now for this project)
5. build the project using gradle command : ./gradlew clean build or gradle clean build
6. To successfully start the project: you will need to create database in local with the same name give in application.property file
7. once project is started (check for line in log file : Started ThinkMarketsSpringBootApplication in <time_duration>)
8. Following APIs are available to test:

   1. http://localhost:8080/thinkmarkets/save
   Request Method : POST
   Request Body :

    {
        "records":[{
            "name":"Test User",
            "address":"123 Fake Street, Chicago Il 60601",
            "phone":1234567
        },{
            "name":"Test User Jr",
            "address":"123 Fake Street, Chicago Il, 60601",
            "phone":1234123
        },{
            "name":"Max Test",
            "address":"456 Fake Ave., Chicago Il, 60602",
            "phone":9878765
        }]
    }

2. http://localhost:8080/thinkmarkets/findPersonById/ {pass the ID here}
example :  http://localhost:8080/thinkmarkets/findPersonById/19
Request Method : GET
output as a sample :

{
    "name": "Test User Jr agin",
    "address": "123 Fake Street, Chicago Il, 60601",
    "phone": 1234123
}

3. http://localhost:8080/thinkmarkets/findPersonByName/{name of the person or persons you want to search for}
example : http://localhost:8080/thinkmarkets/findPersonByName/Max Test
Request Method : GET
OutPut :

[
             {
                 "name": "Max Test",
                 "address": "456 Fake Ave., Chicago Il, 60602",
                 "phone": 9878765
             },
             {
                 "name": "Max Test",
                 "address": "456 Fake Ave., Chicago Il, 60602",
                 "phone": 9878765
             }
         ]

4.http://localhost:8080/thinkmarkets/findPersonByAddress/{pass_the_address_you_want_to_search_for}
example : http://localhost:8080/thinkmarkets/findPersonByAddress/456 Fake Ave., Chicago Il, 60602
Request method : GET
Output:
[
    {
        "name": "Max Test",
        "address": "456 Fake Ave., Chicago Il, 60602",
        "phone": 9878765
    },
    {
        "name": "Max Test dadsasdasd",
        "address": "456 Fake Ave., Chicago Il, 60602",
        "phone": 9878765
    },
    {
        "name": "Max Test",
        "address": "456 Fake Ave., Chicago Il, 60602",
        "phone": 9878765
    }
]

5. http://localhost:8080/thinkmarkets/findPersonByPhone/{Phone_number_to_search_for}

Example: http://localhost:8080/thinkmarkets/findPersonByPhone/9878765
Output :

    {
        "name": "Max Test",
        "address": "456 Fake Ave., Chicago Il, 60602",
        "phone": 9878765
    },
    {
        "name": "Max Test dadsasdasd",
        "address": "456 Fake Ave., Chicago Il, 60602",
        "phone": 9878765
    },
    {
        "name": "Max Test",
        "address": "456 Fake Ave., Chicago Il, 60602",
        "phone": 9878765
    }
]