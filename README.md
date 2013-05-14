# DataService

An abstraction layer (interface) for portability of NoSQL solutions across multiple datastores

Currently supported are
* [OfyDataService](https://github.com/fratuz610/OfyDataService)
* [MorphiaDataService](https://github.com/fratuz610/MorphiaDataService)
* [TinyDBDataService](https://github.com/fratuz610/TinyDB)

## Features

* Allows to code once, deploy to many/different environments
    * Quite useful for moving between Google Application Engine datastore and a MongoDB datastore with minimal issues

## Dependencies

 * None

## Usage

### 1 - Define a pojo according to the implementation requirements (they vary)

    public static class Message {
        @Id public Long messageId;
        @Indexed public String author;
        public String message;
        @Indexed public long timestamp = new Date().getTime();
    }


* A pojo **must** have
    * At least **one field** marked with the `@Id` annotation (primary key)
        * Only `String`, `Long`, `Integer`, `Float` and `Double` types are supported
    * Zero or more `@Indexed` fields
        * Same limitation as the `@Id` field: only `String`, `Long`, `Integer`, `Float` and `Double` types

### 2 - Enjoy a unified querying interface

    