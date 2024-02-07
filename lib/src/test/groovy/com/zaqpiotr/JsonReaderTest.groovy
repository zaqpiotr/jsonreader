package com.zaqpiotr

import spock.lang.Specification

class JsonReaderTest extends Specification {

    def "json to object"() {
        given:
            String jsonContainsArray = JsonReader.readJsonFileAndMergeAllLines("src/test/resources/userData.json");
        when:
            UserDataExample userDataExample = JsonReader.jsonToObject(jsonContainsArray, UserDataExample.class);
        then:
            userDataExample.name == "Piotr"
            userDataExample.age == 18
    }

    def "json to list"() {
        given:
            String jsonContainsArray = JsonReader.readJsonFileAndMergeAllLines("src/test/resources/userDataList.json");
        when:
            List<UserDataExample> userDataExampleList = JsonReader.jsonToList(jsonContainsArray, UserDataExample.class);
        then:
            userDataExampleList[0].name == "Piotr"
            userDataExampleList[0].age == 18
            userDataExampleList[1].name == "Ewa"
            userDataExampleList[1].age == 19
            userDataExampleList[2].name == "Franek"
            userDataExampleList[2].age == 17
    }

}
